package org.crimenetwork.oracle.entity.sharenew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location_code",schema="system")
public class Location {
	public static String PROVINCE_SUFFIX="0000";
	public static String CITY_SUFFIX="00";
	public static String prefixOf(String value) {
		if(value.endsWith(PROVINCE_SUFFIX)){
			return value.substring(0, value.length()-PROVINCE_SUFFIX.length());
		}
		if(value.endsWith(CITY_SUFFIX)){
			return value.substring(0, value.length()-CITY_SUFFIX.length()); 
		}
		return value;
	}
	public static boolean isValid(String code){
		return code.length()==6;
	}
	@Id
	@Column(name="location_code")
	private String code;
	@Column(name="name")
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvinceCodePrefix(){
		return getCode().substring(0,2);
	}
	public String getProvinceCode(){
		return new StringBuilder(getCode()).replace(2, getCode().length(), PROVINCE_SUFFIX).toString();
	}
	public boolean isProvince(){
		return getCode().endsWith(PROVINCE_SUFFIX);
	}
	public boolean isCity(){
		return (!isProvince())&&getCode().endsWith(CITY_SUFFIX);
	}
	public String getCityCodePrefix(){
		if(isProvince()) return null;
		return getCode().substring(0,4);
	}
	public String getCityCode(){
		if(isProvince()) return null;
		return new StringBuilder(getCode()).replace(4, getCode().length(), CITY_SUFFIX).toString();
	}
}
