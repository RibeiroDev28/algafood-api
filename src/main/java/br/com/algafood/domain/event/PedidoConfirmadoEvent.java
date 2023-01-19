package br.com.algafood.domain.event;

import br.com.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PedidoConfirmadoEvent {

	private Pedido pedido;
}
