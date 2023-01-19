package br.com.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInput {

	@Schema(example = "São Paulo", required = true)
    @NotBlank
    private String nome;
    
	@Schema(required = true)
    @Valid
    @NotNull
    private EstadoIdInput estado;
    
}