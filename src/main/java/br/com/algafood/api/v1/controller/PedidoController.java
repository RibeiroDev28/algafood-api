package br.com.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

import br.com.algafood.api.v1.assembler.PedidoInputDisassembler;
import br.com.algafood.api.v1.assembler.PedidoModelAssembler;
import br.com.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import br.com.algafood.api.v1.model.PedidoModel;
import br.com.algafood.api.v1.model.PedidoResumoModel;
import br.com.algafood.api.v1.model.input.PedidoInput;
import br.com.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import br.com.algafood.core.data.PageWrapper;
import br.com.algafood.core.data.PageableTranslator;
import br.com.algafood.core.security.AlgaSecurity;
import br.com.algafood.core.security.CheckSecurity;
import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.exception.NegocioException;
import br.com.algafood.domain.filter.PedidoFilter;
import br.com.algafood.domain.model.Pedido;
import br.com.algafood.domain.model.Usuario;
import br.com.algafood.domain.repository.PedidoRepository;
import br.com.algafood.domain.service.EmissaoPedidoService;
import br.com.algafood.infrastructure.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/v1/pedidos")
public class PedidoController implements PedidoControllerOpenApi{

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private EmissaoPedidoService emissaoPedido;
    
    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;
    
    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;
    
    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;
    
    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
    
    @Autowired
    private AlgaSecurity algaSecurity;
    
    @CheckSecurity.Pedidos.PodePesquisar
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, 
            @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);
        
        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
        
        pedidosPage = new PageWrapper<>(pedidosPage, pageable);
        
        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
    }
    
    @CheckSecurity.Pedidos.PodeBuscar
    @GetMapping(value = "/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
    
    @CheckSecurity.Pedidos.PodeCriar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
            
            
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(algaSecurity.getUsuarioId());

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
    
    private Pageable traduzirPageable(Pageable apiPageable) {
    	var mapeamento = ImmutableMap.of(
    			"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"restaurante.nome", "restaurante.nome",
				"restaurante.id", "restaurante.id",
				"cliente.id", "cliente.id",
				"cliente.nome", "cliente.nome"
    		); 
    	return PageableTranslator.translate(apiPageable, mapeamento);
    }
}        
