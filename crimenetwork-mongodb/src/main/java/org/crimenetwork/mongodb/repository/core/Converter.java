package org.crimenetwork.mongodb.repository.core;
/**
 * 转换类 POJP <--> DBObject
 * @param <DB> mongodb的基本类型DBObject
 * @param <POJO> 系统实体类entity
 * @author v11
 */
public interface Converter<DB,POJO> {

	DB convertToDB(POJO obj);
	POJO convertToPojo(DB obj);
}
