package org.crimenetwork.modeling.convert.instance;


import org.crimenetwork.modeling.convert.Converter;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.CurrencyExtendInfo;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiExtendInfo;
import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter extends Converter<CounterfeitMoney, JiabiBaseInfo>{

	final Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);
	
	@Autowired
	private CurrencyExtendInfoConverter currencyExtendInfoConverter;
	
	@Autowired
	private CaseConverter caseConverter;
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;
	
	@Autowired
	GraphDatabase graphDatabase;
	
	public CounterfeitMoney setAndConvert(JiabiBaseInfo from,boolean convertConnectNode){
		//CounterfeitMoney one = counterfeitMoneyRepository.findByFmid(from.getFmid());
		CounterfeitMoney newOne=convert(from,convertConnectNode);
		CounterfeitMoney oldOne = checkEntity(newOne,from);
		if(oldOne!=null) return oldOne;
		return newOne;
	}
	
	private CounterfeitMoney checkEntity(CounterfeitMoney newOne,JiabiBaseInfo from){
		Transaction tx = graphDatabase.beginTx();
		try {
			CounterfeitMoney oldOne = counterfeitMoneyRepository.findByPiaoyangNumber(from.getPiaoyangNumber());
			if(oldOne!=null){
				if(oldOne.equals(newOne)) return oldOne;
				else{
					oldOne.updateRelation(newOne);
					return oldOne;
				}
			}else {
				oldOne = counterfeitMoneyRepository.findByFmid(from.getFmid());
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
			logger.error("error currency id "+ from.getFmid()+":",e);
		}finally {
			tx.close();
		}
		return null;	
	}
	
	

	@Override
	protected void setManualField(JiabiBaseInfo from, CounterfeitMoney to,boolean convertConnectNode) {
		if(from.getCurrencyType()!=null) to.setCurrencyType(from.getCurrencyType().getName());
		if(from.getCategoryType()!=null) to.setCategoryType(from.getCategoryType().getName());
		if(from.getVersionType()!=null) to.setVersionType(from.getVersionType().getName());	
		
		if(!from.getJiabiExtendInfos().isEmpty()){
			for(JiabiExtendInfo jb : from.getJiabiExtendInfos()){
				CurrencyExtendInfo mjb=currencyExtendInfoConverter.checkBeforeConvert(jb);
				to.setCurrencyExtendInfo(mjb);
				break;
			}
		}	
				
		if(from.getDenominationType()!=null){		
			//CurrencyDenominationType mjb=denominationTypeConverter.checkBeforeConvert(from.getDenominationType());
			to.setDenomination(from.getDenominationType().getCode());			
		}	
		
		if(convertConnectNode&&from.getCaseInfos()!=null){
			for(CaseBaseInfo cbi:from.getCaseInfos()){
				CrimeCase jcbi=caseConverter.setAndConvert(cbi,false);
				to.getCaseInfos().add(jcbi);
			}
		}
	}

}
