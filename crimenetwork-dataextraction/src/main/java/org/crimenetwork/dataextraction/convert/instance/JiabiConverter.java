package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.currency.JiabiCaseBaseInfo;
import org.crimenetwork.mongodb.entity.currency.MClassificationNumber;
import org.crimenetwork.mongodb.entity.currency.MDenominationType;
import org.crimenetwork.mongodb.entity.currency.MJiabiBaseInfo;
import org.crimenetwork.mongodb.entity.currency.MJiabiBasePic;
import org.crimenetwork.mongodb.entity.currency.MJiabiExtendInfo;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBasePic;
import org.crimenetwork.oracle.entity.currency.JiabiExtendInfo;

public class JiabiConverter extends Converter<MJiabiBaseInfo, JiabiBaseInfo>{

	@Override
	protected void setManualField(JiabiBaseInfo from, MJiabiBaseInfo to) {
		if(from.getCurrencyType()!=null) to.setCurrencyType(from.getCurrencyType().getName());
		if(from.getCategoryType()!=null) to.setCategoryType(from.getCategoryType().getName());
		if(from.getVersionType()!=null) to.setVersionType(from.getVersionType().getName());
		
		JiabiExtendInfoConverter jbc=new JiabiExtendInfoConverter();
		if(!from.getJiabiExtendInfos().isEmpty()){
			for(JiabiExtendInfo jb : from.getJiabiExtendInfos()){
				MJiabiExtendInfo mjb=jbc.convert(jb);
				to.getJiabiExtendInfos().add(mjb);
			}
		}
		
		JiabiBasePicConverter jbc2=new JiabiBasePicConverter();
		if(!from.getJiabiBasePics().isEmpty()){
			for(JiabiBasePic jb : from.getJiabiBasePics()){
				MJiabiBasePic mjb=jbc2.convert(jb);
				to.getJiabiBasePics().add(mjb);
			}
		}
		
		ClassificationNumberConverter cnc=new ClassificationNumberConverter();
		if(from.getIdentifyId2()!=null){		
			MClassificationNumber mjb=cnc.convert(from.getIdentifyId2());
			to.setIdentifyId2(mjb);			
		}
		
		DenominationTypeConverter dtc=new DenominationTypeConverter();
		if(from.getDenominationType()!=null){		
			MDenominationType mjb=dtc.convert(from.getDenominationType());
			to.setDenominationType(mjb);			
		}
		
		JiabiCaseConverter jcc=new JiabiCaseConverter();
		if(from.getCaseInfos()!=null){
			for(CaseBaseInfo cbi:from.getCaseInfos()){
				JiabiCaseBaseInfo jcbi=jcc.convert(cbi);
				to.getCaseInfos().add(jcbi);
			}
		}
	}

}
