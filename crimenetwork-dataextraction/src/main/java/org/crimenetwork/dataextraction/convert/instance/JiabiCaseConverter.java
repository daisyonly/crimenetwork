package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.cases.CaseSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.currency.JiabiCaseBaseInfo;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;

public class JiabiCaseConverter extends Converter<JiabiCaseBaseInfo, CaseBaseInfo>{

	@Override
	protected void setManualField(CaseBaseInfo from, JiabiCaseBaseInfo to) {
		if(from.getId()!=null){
			to.setcId(from.getId());
		}
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
	}

}
