package org.crimenetwork.neo4j.repository;


import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.springframework.data.neo4j.repository.GraphRepository;


public interface CounterfeitMoneyRepository extends GraphRepository<CounterfeitMoney> {

	//String cfmid
	CounterfeitMoney findByCfmid(String cfmid);
	
	CounterfeitMoney findByPiaoyangNumber(String piaoyangNumber);
	CounterfeitMoney findByFmid(Long fmid);
    //Iterable<CrimeCase> findByTeammatesName(String name);

}