package org.crimenetwork.modeling.convert.instance;


import org.crimenetwork.modeling.convert.Converter;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Component;

@Component
public class SuspectConverter extends Converter<SuspectInfo, SuspectBaseInfo>{
	final Logger logger = LoggerFactory.getLogger(SuspectConverter.class);
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	private CaseConverter caseConverter;
	
	@Autowired
	GraphDatabase graphDatabase;
	
	public SuspectInfo setAndConvert(SuspectBaseInfo from,boolean convertConnectNode){
		//SuspectInfo one = suspectInfoRepository.findBySId(from.getId());
		SuspectInfo newOne=convert(from,convertConnectNode);
		SuspectInfo oldOne = checkEntity(newOne,from);
		if(oldOne!=null) return oldOne;
		return newOne;
	}
	
	private SuspectInfo checkEntity(SuspectInfo newOne,SuspectBaseInfo from){
		Transaction tx = graphDatabase.beginTx();
		try {
			SuspectInfo oldOne = suspectInfoRepository.findBySuspectId(from.getSuspectId());
			if(oldOne!=null){
				if(oldOne.equals(newOne)) return oldOne;
				else{
					oldOne.updateRelation(newOne);
					return oldOne;
				}
			}else{
				oldOne = suspectInfoRepository.findBySId(from.getId());
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
			
			logger.error("error suspect id "+ from.getId()+":",e);
		} finally {
			tx.close();
		}
		return null;	
	}
	
	@Override
	protected void setManualField(SuspectBaseInfo from, SuspectInfo to,boolean convertConnectNode) {
		if(from.getId()!= null) to.setsId(from.getId());
		if(from.getNation()!=null)to.setNation(from.getNation().getName());		
		if(from.getGender()!=null) to.setGender(from.getGender().getName());
		if(from.getAccent()!=null) to.setAccent(from.getAccent().getName());
		if(from.getOccupation()!=null) to.setOccupation(from.getOccupation().getName());
		if(from.getOccupation()!=null) to.setOccupation(from.getOccupation().getName());
		if(from.getIsLc()!=null) to.setIsLc(from.getIsLc().getName());
		if(from.getIsHabitualOffender()!=null) to.setIsHabitualOffender(from.getIsHabitualOffender().getName());
		if(from.getIsReoffender()!=null) to.setIsReoffender(from.getIsReoffender().getName());
		
		if(from.getNativeLocation()!=null){		
			to.setNativeLocation(from.getNativeLocation().getName());		
		}
			
		if(convertConnectNode&&!from.getCases().isEmpty()){
			for(CaseBaseInfo cbs : from.getCases()){
				CrimeCase csbi=caseConverter.setAndConvert(cbs,false);
				to.getCases().add(csbi);
			}
		}
		
	}

	
	
}
