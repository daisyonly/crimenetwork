package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.cases.CaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;



@RepositoryDefinition(domainClass = CaseInfo.class, idClass = Long.class) 
public interface CaseBaseDao{
    public Page<CaseInfo> findAll(Pageable pageable); 
}
