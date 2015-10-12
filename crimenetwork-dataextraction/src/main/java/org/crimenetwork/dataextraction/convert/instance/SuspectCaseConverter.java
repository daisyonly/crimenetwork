package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.currency.MJiabiBaseInfo;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.mongodb.entity.suspect.SuspectCaseBaseInfo;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;

public class SuspectCaseConverter extends Converter<SuspectCaseBaseInfo, CaseBaseInfo>{

	@Override
	protected void setManualField(CaseBaseInfo from, SuspectCaseBaseInfo to) {
		// TODO Auto-generated method stub
		if(from.getCaseHappenLocation()!=null){
			LocationConverter locationConverter=new LocationConverter();
			MLocation mm=locationConverter.convert(from.getCaseHappenLocation());
			to.setCaseHappenLocation(mm);		
		}
		
		JiabiBaseInfoConverter jbc=new JiabiBaseInfoConverter();
		if(!from.getSuspects().isEmpty()){
			for(JiabiBaseInfo jb : from.getCounterfeitMoneys()){
				MJiabiBaseInfo mjb=jbc.convert(jb);
				to.getCounterfeitMoneys().add(mjb);
			}
		}
	}

}
