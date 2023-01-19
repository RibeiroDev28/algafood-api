package br.com.algafood.core.sprigdoc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
		in = ParameterIn.QUERY,
		name = "page",
		description = "Número da pagina (0..N )",
		schema = @Schema(type = "integer", defaultValue = "0")
)
@Parameter(
		in = ParameterIn.QUERY,
		name = "size",
		description = "Quantidades de elemento por pagina",
		schema = @Schema(type = "integer", defaultValue = "10")
)
@Parameter(
		in = ParameterIn.QUERY,
		name = "sort",
		description = "Critério de ordenação (asc|desc)",
		examples = {
				@ExampleObject("nome"),
				@ExampleObject("nome,asc"),
				@ExampleObject("nome,desc"),
				
		}
)
public @interface PageableParameter {

}
	
