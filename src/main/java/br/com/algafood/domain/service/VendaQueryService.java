package br.com.algafood.domain.service;

import java.util.List;

import br.com.algafood.domain.filter.VendaDiariaFilter;
import br.com.algafood.domain.model.dto.VendaDiaria;


public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
