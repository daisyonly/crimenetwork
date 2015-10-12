package org.crimenetwork.dataextraction.convert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.crimenetwork.mongodb.utils.ManualField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public abstract class Converter <T,F>{
	private T instance;
	protected static Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();
	protected Logger getLogger(){
		Class<?> c = this.getClass();
		if(!loggers.containsKey(c)){
			Logger log = LoggerFactory.getLogger(c);
			loggers.put(c, log);
		}
		return loggers.get(c);
	}
	public T convert(F from){
		T to = get();
		setManualField(from,to);
		setAuto(from,to);
		return to;
	}
	protected  abstract void setManualField(F from,T to);
	protected void setAuto(F from,T to){
		for(Field f : to.getClass().getDeclaredFields()){
			if(null == f.getAnnotation(ManualField.class)){
				try {
					
					Object obj = PropertyUtils.getProperty(from, f.getName());
					//System.out.println("属性1 "+f.getName()+":"+obj);
					if(obj == null) continue ;
					//System.out.println("属性2 "+f.getName()+":"+obj);
					PropertyUtils.setProperty(to, f.getName(), obj);
				} catch (IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) {
					// TODO Auto-generated catch block
					getLogger().error("converter内部错误："+e+" from:"+from+" to:"+to+" 属性："+f.getName());
				}catch (Exception e) {
					getLogger().error("converter外部错误："+e+" from:"+from+" to:"+to+" 属性："+f.getName());
				     e.printStackTrace();
				}
			}
		}
		
	}
	@SuppressWarnings("unchecked")
	protected T get(){
		try {
			instance = (T) ((Class)((ParameterizedType)this.getClass().
				       getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
}
