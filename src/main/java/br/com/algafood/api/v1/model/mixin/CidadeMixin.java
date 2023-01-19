package br.com.algafood.api.v1.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.algafood.domain.model.Estado;

public abstract class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
