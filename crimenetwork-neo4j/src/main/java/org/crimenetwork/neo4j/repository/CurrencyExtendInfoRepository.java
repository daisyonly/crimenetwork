package org.crimenetwork.neo4j.repository;


import org.crimenetwork.neo4j.entity.CurrencyExtendInfo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CurrencyExtendInfoRepository extends GraphRepository<CurrencyExtendInfo> {

	CurrencyExtendInfo findByJeiid(Long jeiid);
}
