package org.crimenetwork.dataextraction.nameDisambiguation.eva;

import java.util.*;

public class ClusterEvaluationLong extends ClusterEvaluation<Long>{

	public ClusterEvaluationLong(List<Set<Long>> fact, List<Set<Long>> predict) throws Exception {
		super(fact, predict);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean equal(Long a, Long b) {
		// TODO Auto-generated method stub
		return a.equals(b);
	}
	public static void addSetElement(Set<Long> st,Long[] ls){
		for(Long s:ls) st.add(s);
	}
	
}
