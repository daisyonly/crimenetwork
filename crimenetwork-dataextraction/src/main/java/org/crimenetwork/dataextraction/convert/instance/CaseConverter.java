package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.cases.CaseSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.cases.MCaseBaseInfo;
import org.crimenetwork.mongodb.entity.currency.BaseJiabiInfo;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.hibernate.loader.custom.CustomLoader.ScalarResultColumnProcessor;



public class CaseConverter extends Converter<MCaseBaseInfo,CaseBaseInfo>{

	@Override
	protected void setManualField(CaseBaseInfo from, MCaseBaseInfo to) {
	
		if(from.getCaseHappenLocation()!=null){
			LocationConverter locationConverter=new LocationConverter();
			MLocation mm=locationConverter.convert(from.getCaseHappenLocation());
			to.setCaseHappenLocation(mm);
			
		}
		CaseSuspectConverter cs=new CaseSuspectConverter();
		if(!from.getSuspects().isEmpty()){
			for(SuspectBaseInfo cbs : from.getSuspects()){
				CaseSuspectBaseInfo csbi=cs.convert(cbs);
				to.getSuspects().add(csbi);
			}
		}
		
		JiabiBaseInfoConverter jbc=new JiabiBaseInfoConverter();
		if(!from.getSuspects().isEmpty()){
			for(JiabiBaseInfo jb : from.getCounterfeitMoneys()){
				BaseJiabiInfo mjb=jbc.convert(jb);
				to.getCounterfeitMoneys().add(mjb);
			}
		}
		
	}
	
}
