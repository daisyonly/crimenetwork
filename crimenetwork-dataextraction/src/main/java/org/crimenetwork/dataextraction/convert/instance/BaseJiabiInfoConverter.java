package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.currency.MClassificationNumber;
import org.crimenetwork.mongodb.entity.currency.MDenominationType;
import org.crimenetwork.mongodb.entity.currency.BaseJiabiInfo;
import org.crimenetwork.mongodb.entity.currency.MJiabiBasePic;
import org.crimenetwork.mongodb.entity.currency.MJiabiExtendInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBasePic;
import org.crimenetwork.oracle.entity.currency.JiabiExtendInfo;

public class BaseJiabiInfoConverter extends Converter<BaseJiabiInfo, JiabiBaseInfo>{

	@Override
	protected void setManualField(JiabiBaseInfo from, BaseJiabiInfo to) {
		// TODO Auto-generated method stub
		to.setCurrencyType(from.getCurrencyType().getName());
		to.setCategoryType(from.getCategoryType().getName());
		to.setVersionType(from.getVersionType().getName());
		
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
	}

}
