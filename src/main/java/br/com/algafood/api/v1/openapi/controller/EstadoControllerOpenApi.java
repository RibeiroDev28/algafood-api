package br.com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.algafood.api.v1.model.EstadoModel;
import br.com.algafood.api.v1.model.input.EstadoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados")
public interface EstadoControllerOpenApi {

	@Operation(summary = "Lista os estados")
	CollectionModel<EstadoModel> listar();

	@Operation(summary = "Busca um estado por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do estado inválido", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	EstadoModel buscar(Long estadoId);

	@Operation(summary = "Cadastra um estado", responses = {
			@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	})
	EstadoModel adicionar(EstadoInput estadoInput);

	@Operation(summary = "Atualiza um estado por ID", responses = {
			@ApiResponse(responseCode = "200", description = "Estado atualizado"),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	EstadoModel atualizar(Long estadoId, EstadoInput estadoInput);

	@Operation(summary = "Exclui um estado por ID", responses = {
			@ApiResponse(responseCode = "204", description = "Estado excluído"),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	ResponseEntity<Void> remover(Long estadoId);

}
