package org.crimenetwork.mongodb.repository.core;

import org.crimenetwork.mongodb.repository.DBQuery;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;

public interface DAO<T>{
	/** 
	 * 获取查询全部记录 
	 * <p />记得使用完后，要关闭DBCursor
	 * @return cursor数据流
	 */
	public DBCursor findByAll();
	/**
	 * 获取查询记录数量
	 * <p />并自动关闭DBCursor
	 * @param DBCursor 数据流
	 * @return cursor大小
	 */
	public int count(DBCursor cursor);
	/**
	 * 根据<code> DBObject query</code> 查询数据库
	 * <p />记得使用完后，要关闭DBCursor
	 * @param query 最原始的查询语句
	 * @return 
	 * @see DBQuery
	 * @see QueryBuilder
	 */
	public DBCursor findQuery(DBObject query);
	/**
	 * 根据<code> DBQuery query</code> 查询数据库
	 * <p />记得使用完后，要关闭DBCursor
	 * @param query 查询语句，利用封装好的类<code> DBQuery query</code> 
	 * @return 
	 * @see DBQuery
	 */
	public DBCursor findQuery(DBQuery query);
	/**
	 * 根据<code> QueryBuilder query</code> 查询数据库
	 * <p />记得使用完后，要关闭DBCursor
	 * @param query 查询语句,利用mongo自带的<code> QueryBuilder</code> 
	 * @return 
	 * @see QueryBuilder
	 */
	public DBCursor findQuery(QueryBuilder query);

	/**
	 * 删除该表所有记录
	 */
	public void dropAll();
	/**
	 * 更新数据
	 * @param obj 变动后的<code>DBObjet</code>的完整信息
	 * @param keyNames 需要变动的字段名，多个字段之间用,隔开<p/>
	 * Example
	 * <pre>
	 * keyName = "time,age,firendCount"
	 * </pre>
	 * 
	 */
	public void update(DBObject obj,String keyNames);
	/**
	 * @see DAO#update(DBObject, String)
	 */
	public void update(T obj,String keyNames);
	/**
	 * 根据id,如果不存在就save，否则update
	 * @param obj
	 * @return 如果保存成功return true,否则false
	 */
	public boolean saveAndUpdate(T obj);
	/**
	 * 根据id,如果不存在就save，否则update
	 * @param obj
	 * @return 如果保存成功return true,否则false
	 */
	public boolean saveAndUpdate(DBObject obj);

	/**
	 * 从cursor中获取第一个实体，一般用于查找仅有一个的时候
	 * <p />并自动关闭DBCursor
	 * @param cursor 
	 * @return if Cusor.size>0,返回第一个元素，or 返回 null
	 */
	public T findOne(DBCursor cursor);
	/**
	 * 查找通过主键Id
	 * <p />并自动关闭DBCursor
	 * @param id 主键值
	 * @return 对应的值,or Null if no exist
	 */
	public T findById(Object id);
	public WriteResult dropById(Object id);
	public T findOneByAttributeName(String attributeName,Object value);
}
