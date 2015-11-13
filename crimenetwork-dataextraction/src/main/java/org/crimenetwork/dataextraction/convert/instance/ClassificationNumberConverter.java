package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.currency.MClassificationNumber;
import org.crimenetwork.oracle.entity.currency.ClassificationNumber;

public class ClassificationNumberConverter extends Converter<MClassificationNumber, ClassificationNumber>{

	@Override
	protected void setManualField(ClassificationNumber from,
			MClassificationNumber to) {
		DenominationTypeConverter dtc=new DenominationTypeConverter();
		to.setDenominationType(dtc.convert(from.getDenominationType()));
	}

}
