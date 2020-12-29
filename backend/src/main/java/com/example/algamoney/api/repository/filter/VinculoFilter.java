package com.example.algamoney.api.repository.filter;

import java.time.LocalDate;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class VinculoFilter {

	private String estabelecimentoCodigo;
	private String profissionalCodigo;


}
