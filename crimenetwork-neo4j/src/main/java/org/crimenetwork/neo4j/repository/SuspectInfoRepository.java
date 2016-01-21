package org.crimenetwork.neo4j.repository;

import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.springframework.data.neo4j.repository.GraphRepository;


public interface SuspectInfoRepository extends GraphRepository<SuspectInfo> {

	SuspectInfo findBySuspectId(String suspectId);
	SuspectInfo findBySId(Long sId);

    //Iterable<CrimeCase> findByTeammatesName(String name);

}