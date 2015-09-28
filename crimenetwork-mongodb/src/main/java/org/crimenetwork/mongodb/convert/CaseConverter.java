package org.crimenetwork.mongodb.convert;

import org.crimenetwork.mongodb.entity.Case;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;



public class CaseConverter extends Converter<Case,CaseBaseInfo>{

	@Override
	protected void setManualField(CaseBaseInfo from, Case to) {
		// TODO Auto-generated method stub
		// 手动转换的属性，在无法一一对应的情况下
//		UserConverter userConverter = new UserConverter();
//		to.setUser(userConverter.convert(from.getUser()));
//		to.setWeiboId(from.getStatus().getId());
	}

}
