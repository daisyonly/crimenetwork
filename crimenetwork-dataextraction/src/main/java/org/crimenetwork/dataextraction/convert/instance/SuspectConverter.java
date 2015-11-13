package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.cases.CaseSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.mongodb.entity.suspect.MSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.suspect.SuspectCaseBaseInfo;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;

public class SuspectConverter extends Converter<MSuspectBaseInfo, SuspectBaseInfo>{

	@Override
	protected void setManualField(SuspectBaseInfo from, MSuspectBaseInfo to) {
		if(from.getNation()!=null)to.setNation(from.getNation().getName());		
		if(from.getGender()!=null) to.setGender(from.getGender().getName());
		if(from.getNativeLocation()!=null){
			LocationConverter locationConverter=new LocationConverter();
			MLocation mm=locationConverter.convert(from.getNativeLocation());
			to.setNativeLocation(mm);		
		}
		SuspectCaseConverter cs=new SuspectCaseConverter();
		if(!from.getCases().isEmpty()){
			for(CaseBaseInfo cbs : from.getCases()){
				SuspectCaseBaseInfo csbi=cs.convert(cbs);
				to.getCases().add(csbi);
			}
		}
		
	}
	
}
