package br.com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.algafood.api.v1.model.ProdutoModel;
import br.com.algafood.api.v1.model.input.ProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@Operation(summary = "Lista os produtos de um restaurante", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
	})
	CollectionModel<ProdutoModel> listar(Long restauranteId, Boolean incluirInativos);

	@Operation(summary = "Busca um produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
	})
	ProdutoModel buscar(Long restauranteId, Long produtoId);

	@Operation(summary = "Cadastra um produto de um restaurante", responses = {
			@ApiResponse(responseCode = "201", description = "Produto cadastrado"),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
	})
	ProdutoModel adicionar(Long restauranteId, ProdutoInput produtoInput);

	@Operation(summary = "Atualiza um produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", description = "Produto atualizado"),
			@ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
	})
	ProdutoModel atualizar(Long restauranteId, Long produtoId, ProdutoInput produtoInput);

}
