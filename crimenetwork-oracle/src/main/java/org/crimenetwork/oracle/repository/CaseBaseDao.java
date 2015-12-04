package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = CaseBaseInfo.class, idClass = Long.class) 
public interface CaseBaseDao{
	public Page<CaseBaseInfo> findAll(Pageable pageable); 
	public Iterable<CaseBaseInfo> findAll();
	public long count();
	public CaseBaseInfo findById(Long id);
	
}
