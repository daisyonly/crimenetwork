package org.crimenetwork.core.jiabi;

public class JiabiSimPair extends Object{
	public final String key1;
	public final String key2;
	
	public JiabiSimPair(String key1,String key2){
		this.key1=key1;
		this.key2=key2;
	} 
	
	@Override
	public int hashCode() {
		return key1.hashCode()*31+key2.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JiabiSimPair))
            return false;
        if (obj == this)
            return true;

        JiabiSimPair rhs = (JiabiSimPair) obj;
        if((rhs.key1.equals(this.key1)&&rhs.key2.equals(this.key2))
        		||(rhs.key2.equals(this.key1)&&rhs.key1.equals(this.key2)))
        	return true;
        return false;
	}

}
