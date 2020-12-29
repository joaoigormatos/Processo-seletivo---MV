package com.example.algamoney.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.algamoney.api.model.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumoVinculo {


	private String estabelecimento;
	private String profissional;


}
