package org.crimenetwork.neo4j.repository;

import org.crimenetwork.neo4j.entity.CrimeCase;
import org.springframework.data.neo4j.repository.GraphRepository;


public interface CrimeCaseRepository extends GraphRepository<CrimeCase> {

	CrimeCase findByCId(Long cId);
	
	CrimeCase findByCaseId(String caseId);
    //Iterable<CrimeCase> findByTeammatesName(String name);

}