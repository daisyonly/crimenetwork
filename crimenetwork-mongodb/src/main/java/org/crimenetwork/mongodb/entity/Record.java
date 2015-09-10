package org.crimenetwork.mongodb.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 消费记录
 * 
 * @author v11
 */
@Document
public class Record extends AbstractDocument{
	private Double cost;
	private Date createAt;
	private ObjectId uid;//用户id
	private Integer type;//消费类型
	private ObjectId tid;//如果消费属于team中的记录，则有tid，否则为null(后续拓展)
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public ObjectId getUid() {
		return uid;
	}
	public void setUid(ObjectId uid) {
		this.uid = uid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public ObjectId getTid() {
		return tid;
	}
	public void setTid(ObjectId tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
	
}
