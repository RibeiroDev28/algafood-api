package br.com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.algafood.api.v1.model.input.RestauranteInput;
import br.com.algafood.domain.model.Cidade;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {


	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
    }
    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
    	restaurante.setCozinha(new Cozinha());
    	
    	if(restaurante.getEndereco() != null) {
    		restaurante.getEndereco().setCidade(new Cidade());
    	}
    	
    	modelMapper.map(restauranteInput, restaurante);
    }
}
