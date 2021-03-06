package org.crimenetwork.mongodb.repository;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
/**
 * 查询构造类，用于构造带条件的查询
 * <pre>
 * DBQuery query = new DBQuery();
 * query.lessThan("key1", "value1");
 * query.greaterThan("key2", "v2");
 * query.equalsOperation("key3","v3");
 * query.getQuery()
 * </pre>
 * 你将会得到的查询如下:
 * <pre>
 * { "key1" : { "$lt" : "value1"} , "key2" : { "$gt" : "v2"} , "key3" : "v3"}
 * </pre>
 * 
 * @author v11
 */
public class DBQuery {
	private QueryBuilder query;
	public DBQuery(){
		query = QueryBuilder.start();
	}
	/**
	 * Equivalent to the $gte operator and return this DBQuery instance
     * @param key MongoDB document key 字段名
	 * @param value Value to query 查询值
	 * @return <code>DBQuery</code>
	 */
	public DBQuery greaterThanEquals(String key , Object value){
		query.put(key).greaterThanEquals(value);
		return this;
	}
	/**
	 * Equivalent of the find({key:value}) and return this DBQuery instance
     * @param key MongoDB document key 字段名
	 * @param value Value to query 查询值
	 * @return <code>DBQuery</code>
	 */
	public DBQuery equalsOperation(String key , Object value){
		query.put(key).is(value);
		return this;
	}
	/**
	 * Equivalent to the $gt operator and return this DBQuery instance
     * @param key MongoDB document key 字段名
	 * @param value Value to query 查询值
	 * @return <code>DBQuery</code>
	 */
	public DBQuery greaterThan(String key , Object value){
		query.put(key).greaterThan(value);
		return this;
	}
	/**
     * Equivalent to the $lt operand and return this DBQuery instance
     * @param key MongoDB document key 字段名
	 * @param value Value to query 查询值
	 * @return <code>DBQuery</code>
	 */
    public DBQuery lessThan(String key , Object value) {
    	query.put(key).lessThan(value);
    	return this;
    }
	
    /**
     * Equivalent to the $lte operand and return this DBQuery instance
     * @param key MongoDB document key 字段名
	 * @param value Value to query 查询值
	 * @return <code>DBQuery</code>
	 */
    public DBQuery lessThanEquals(String key , Object value){
    	query.put(key).lessThanEquals(value);
        return this;
    }
    /**
     * clear and create a new <code>DBQuery</code>
     */
    public void clear(){
    	query = QueryBuilder.start();
    }
    /**
     * Creates a DBObject query to be used for the driver's find operations
     * @return a DBObject query instance
     */
	public DBObject getQuery(){
		return query.get();
	}
	
}
