package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.jiabisim.SimDenominationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = SimDenominationType.class, idClass = String.class) 
public interface SimDenominationTypeDao{
	public Page<SimDenominationType> findAll(Pageable pageable); 
	public Iterable<SimDenominationType> findAll();
	public long count();
	public SimDenominationType findByCode(String code);
	
}