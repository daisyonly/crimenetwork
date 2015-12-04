package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.currency.DenominationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = DenominationType.class, idClass = String.class) 
public interface DenominationTypeDao{
	public Page<DenominationType> findAll(Pageable pageable); 
	public Iterable<DenominationType> findAll();
	public long count();
	public DenominationType findByCode(String code);
	
}