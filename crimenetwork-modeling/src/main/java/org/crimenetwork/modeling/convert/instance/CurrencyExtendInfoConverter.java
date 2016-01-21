package org.crimenetwork.modeling.convert.instance;

import org.crimenetwork.modeling.convert.Converter;
import org.crimenetwork.neo4j.entity.CurrencyExtendInfo;
import org.crimenetwork.neo4j.repository.CurrencyExtendInfoRepository;
import org.crimenetwork.oracle.entity.currency.JiabiExtendInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExtendInfoConverter extends Converter<CurrencyExtendInfo, JiabiExtendInfo>{

	@Autowired
	private CurrencyExtendInfoRepository currencyDenominationTypeRepository;
	
	
	public CurrencyExtendInfo checkBeforeConvert(JiabiExtendInfo from){
		CurrencyExtendInfo one=currencyDenominationTypeRepository.findByJeiid(from.getJeiid());
		if(one!=null) return one;
		return convert(from,false);
	}
	
	
	@Override
	protected void setManualField(JiabiExtendInfo from, CurrencyExtendInfo to,boolean convertConnectNode) {
		// TODO Auto-generated method stub
		
	}

}
