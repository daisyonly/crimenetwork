package org.crimenetwork.neo4j.entity.relationship;

import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="SIMILAR")
public class CurrencySim {
	@GraphId
    Long id;
	
	@StartNode CounterfeitMoney from;
    @EndNode CounterfeitMoney to;
    
    double simliar;

}
