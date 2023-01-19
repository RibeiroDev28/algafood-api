package br.com.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.algafood.api.v1.model.mixin.CidadeMixin;
import br.com.algafood.api.v1.model.mixin.CozinhaMixin;
import br.com.algafood.api.v1.model.mixin.RestauranteMixin;
import br.com.algafood.domain.model.Cidade;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.model.Restaurante;

@Component
public class JacksonMixinModel extends SimpleModule{
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModel() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	    setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}

}
