package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.cases.CaseSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;

public class CaseSuspectConverter extends Converter<CaseSuspectBaseInfo, SuspectBaseInfo>{

	@Override
	protected void setManualField(SuspectBaseInfo from, CaseSuspectBaseInfo to) {
		
		if(from.getId()!= null) to.setsId(from.getId());
		
		if(from.getGender()!=null)to.setGender(from.getGender().getName());
		if(from.getNation()!=null)to.setNation(from.getNation().getName());
		if(from.getNativeLocation()!=null){
			LocationConverter locationConverter=new LocationConverter();
			MLocation mm=locationConverter.convert(from.getNativeLocation());
			to.setNativeLocation(mm);			
		}
	}

}
