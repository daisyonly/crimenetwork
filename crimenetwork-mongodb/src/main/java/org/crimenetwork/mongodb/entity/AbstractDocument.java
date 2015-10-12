package org.crimenetwork.mongodb.entity;

import java.math.BigInteger;

import org.bson.types.ObjectId;
import org.crimenetwork.mongodb.utils.ManualField;
import org.springframework.data.annotation.Id;

/**
 * Base class for document classes.
 * 
 * @author v11
 */
public abstract class AbstractDocument {
	@Id
	@ManualField
	protected ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
}
