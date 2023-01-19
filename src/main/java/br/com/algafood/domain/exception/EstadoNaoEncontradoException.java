package br.com.algafood.domain.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) // , reason ="Entidade não Encontrada")
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException	 {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de estado com código %d", estadoId));
	}
	
}
