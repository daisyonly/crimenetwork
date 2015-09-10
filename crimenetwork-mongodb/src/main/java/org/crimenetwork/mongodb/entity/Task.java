package org.crimenetwork.mongodb.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 用户每月消费计划
 * 
 * @author v11
 */
@Document
public class Task extends AbstractDocument{
	private Double limit;//消费计划额度
	private Double payment;//已支出
	private Date start;
	private Date end;
	private ObjectId uid;
	private String uniMonth;//201405,unique.range:20140501-20140531
	public Double getLimit() {
		return limit;
	}
	public void setLimit(Double limit) {
		this.limit = limit;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public ObjectId getUid() {
		return uid;
	}
	public void setUid(ObjectId uid) {
		this.uid = uid;
	}
	public String getUniMonth() {
		return uniMonth;
	}
	public void setUniMonth(String uniMonth) {
		this.uniMonth = uniMonth;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
