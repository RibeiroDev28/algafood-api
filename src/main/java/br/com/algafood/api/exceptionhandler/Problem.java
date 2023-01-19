package br.com.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Builder
@Getter
@Schema(name="Problema")
public class Problem {
	
	@Schema(example = "400")
	private Integer status;
	
	@Schema(example = "2022-07-20T18:09:02.70844Z")
	private OffsetDateTime timestamp;

	@Schema(example = "https://algafood.com.br/dados-invalidos")
	private String type;
	
	@Schema(example = "Dados inválidos")
	private String title;
	
	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String detail;
	
	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String userMessage;
	
	@Schema(description = "Lista de objetos ou campos que geraram o erro (opcional)")
	private List<Object> objects;
	
	@Schema(name = "Objeto Problema")
	@Getter
	@Builder
	public static class Object{
		
		@Schema(example = "preço")
		private String name;
		
		@Schema(example = "O preço é obrigatório")
		private String userMessage;
	}
}
