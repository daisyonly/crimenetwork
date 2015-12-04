package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;



@RepositoryDefinition(domainClass = JiabiBaseInfo.class, idClass = Long.class) 
public interface JiabiBaseDao{
	public Page<JiabiBaseInfo> findAll(Pageable pageable); 
	public Iterable<JiabiBaseInfo> findAll();
	public long count(); 
	public JiabiBaseInfo findByFmid(Long fmid);
}
