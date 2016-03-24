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
    
    public CurrencySim(){}
    
    public CurrencySim(CounterfeitMoney from,CounterfeitMoney to,boolean sameGuanzihao){
    	this.from=from;
    	this.to=to;
    	this.sameGuanzihao=sameGuanzihao;
    }
    double simliar;
    boolean sameGuanzihao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CounterfeitMoney getFrom() {
		return from;
	}

	public void setFrom(CounterfeitMoney from) {
		this.from = from;
	}

	public CounterfeitMoney getTo() {
		return to;
	}

	public void setTo(CounterfeitMoney to) {
		this.to = to;
	}

	public double getSimliar() {
		return simliar;
	}

	public void setSimliar(double simliar) {
		this.simliar = simliar;
	}

	public boolean isSameGuanzihao() {
		return sameGuanzihao;
	}

	public void setSameGuanzihao(boolean sameGuanzihao) {
		this.sameGuanzihao = sameGuanzihao;
	}
    
    

}
