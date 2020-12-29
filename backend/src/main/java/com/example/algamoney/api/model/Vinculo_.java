package com.example.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vinculo.class)
public abstract class Vinculo_ {

	public static volatile SingularAttribute<Vinculo, Long> codigo;

	public static volatile SingularAttribute<Vinculo, Profissional> profissional;
	public static volatile SingularAttribute<Vinculo, Estabelecimento> estabelecimento;


}

