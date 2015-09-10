package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.CaseBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;



@RepositoryDefinition(domainClass = CaseBaseInfo.class, idClass = Long.class) 
public interface CaseBaseDao{
    public Page<CaseBaseInfo> findAll(Pageable pageable); 
}
