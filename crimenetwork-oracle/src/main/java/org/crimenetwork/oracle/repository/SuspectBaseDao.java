package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = SuspectBaseInfo.class, idClass = Long.class) 
public interface SuspectBaseDao {
	public Page<SuspectBaseInfo> findAll(Pageable pageable); 
	public Iterable<SuspectBaseInfo> findAll();
	public long count();

}
