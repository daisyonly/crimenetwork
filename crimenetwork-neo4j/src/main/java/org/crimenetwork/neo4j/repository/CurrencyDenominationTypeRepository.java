package org.crimenetwork.neo4j.repository;

import org.crimenetwork.neo4j.entity.CurrencyDenominationType;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CurrencyDenominationTypeRepository 
	extends GraphRepository<CurrencyDenominationType>{
	
	CurrencyDenominationType findByCode(String code);
}
