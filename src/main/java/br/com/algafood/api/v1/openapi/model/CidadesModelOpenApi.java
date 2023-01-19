package br.com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import br.com.algafood.api.v1.model.CidadeModel;
import lombok.Data;

//@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {


	private CidadeEmbeddedModelOpenApi _embedded;
	private Links _links;
	
//	@ApiModel("CidadesEmbeddedModel")
	@Data
	private class CidadeEmbeddedModelOpenApi{
	
		private List<CidadeModel> cidades;
	}
}
