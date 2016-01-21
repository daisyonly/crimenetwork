package org.crimenetwork.modeling.convert.instance;


import org.crimenetwork.modeling.convert.Converter;
import org.crimenetwork.neo4j.entity.CurrencyDenominationType;
import org.crimenetwork.neo4j.repository.CurrencyDenominationTypeRepository;
import org.crimenetwork.oracle.entity.currency.DenominationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DenominationTypeConverter extends Converter<CurrencyDenominationType, DenominationType>{
	
	@Autowired
	private CurrencyDenominationTypeRepository currencyDenominationTypeRepository;
	
	public CurrencyDenominationType checkBeforeConvert(DenominationType from){

		CurrencyDenominationType one=currencyDenominationTypeRepository.findByCode(from.getCode());
		if(one!=null) return one;
		return convert(from,false);
	}
	
	@Override
	protected void setManualField(DenominationType from, CurrencyDenominationType to,boolean convertConnectNode) {
		
		
	}

}
