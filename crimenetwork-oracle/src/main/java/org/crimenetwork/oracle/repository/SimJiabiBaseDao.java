package org.crimenetwork.oracle.repository;

import org.crimenetwork.oracle.entity.jiabisim.SimJiabiBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;



@RepositoryDefinition(domainClass = SimJiabiBaseInfo.class, idClass = Long.class) 
public interface SimJiabiBaseDao{
	public Page<SimJiabiBaseInfo> findAll(Pageable pageable); 
	public Iterable<SimJiabiBaseInfo> findAll();
	public long count(); 
	public SimJiabiBaseInfo findByFmid(Long fmid);
}
