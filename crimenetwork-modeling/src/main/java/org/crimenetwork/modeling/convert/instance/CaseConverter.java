package org.crimenetwork.modeling.convert.instance;


import org.crimenetwork.modeling.convert.Converter;
import org.crimenetwork.modeling.service.SuspectService;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Component;

@Component
public class CaseConverter extends Converter<CrimeCase,CaseBaseInfo>{
	
	final Logger logger = LoggerFactory.getLogger(CaseConverter.class);
	
	@Autowired
	private CrimeCaseRepository crimeCaseRepository;
	
	@Autowired
	private SuspectConverter suspectConverter;
	
	@Autowired
	private CurrencyConverter currencyConverter;
	
	@Autowired
	GraphDatabase graphDatabase;
	
	public CrimeCase setAndConvert(CaseBaseInfo from,boolean convertConnectNode){
		//CrimeCase one = crimeCaseRepository.findByCId(from.getId());
		CrimeCase newOne =convert(from,convertConnectNode);
		CrimeCase oldOne = checkEntity(newOne,from);
		if(oldOne!=null) return oldOne;
		return newOne;
	}
	
	private CrimeCase checkEntity(CrimeCase newOne,CaseBaseInfo from){
		Transaction tx = graphDatabase.beginTx();
		try {
			CrimeCase oldOne = crimeCaseRepository.findByCaseId(from.getCaseId());
			if(oldOne!=null){
				if(oldOne.equals(newOne)) return oldOne;
				else{
					oldOne.updateRelation(newOne);
					return oldOne;
				}
			}else{
				oldOne = crimeCaseRepository.findByCId(from.getId());
				if(oldOne!=null){
					if(oldOne.equals(newOne)) return oldOne;
					else{
						oldOne.updateRelation(newOne);
						return oldOne;
					}
				}
			}				
			tx.success();		
		} catch(Exception e){
			logger.error("error case id "+ from.getId()+":",e);
		}finally {
			tx.close();
		}
		return null;	
	}
	
	@Override
	protected void setManualField(CaseBaseInfo from, CrimeCase to,boolean convertConnectNode) {
		if(from.getId()!=null){
			to.setcId(from.getId());
		}
	
		if(from.getCaseHappenLocation()!=null){
			to.setCaseHappenLocation(from.getCaseHappenLocation().getName());
			
		}
		
		if(convertConnectNode&&!from.getSuspects().isEmpty()){
			for(SuspectBaseInfo cbs : from.getSuspects()){
				SuspectInfo csbi=suspectConverter.setAndConvert(cbs, false);
				to.getSuspects().add(csbi);
			}
		}
			
		if(convertConnectNode&&!from.getCounterfeitMoneys().isEmpty()){
			for(JiabiBaseInfo jb : from.getCounterfeitMoneys()){
				CounterfeitMoney mjb=currencyConverter.setAndConvert(jb,false);
				to.getCounterfeitMoneys().add(mjb);
			}
		}
		
	}
	
}
