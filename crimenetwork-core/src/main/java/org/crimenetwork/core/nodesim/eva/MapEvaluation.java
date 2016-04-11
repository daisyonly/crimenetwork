package org.crimenetwork.core.nodesim.eva;
import java.util.*;
public class MapEvaluation {

	List<Map<Long, Integer>> correct;
	List<Map<Long, Double>> predict;
	static double getMap(List<Map<Long, Integer>> correct,
			List<Map<Long, Double>> predict){
				return getMap(correct,predict,0);
		
	}
	static double getMap(List<Map<Long, Integer>> correct,
			List<Map<Long, Double>> predict,int nn){
		double res = 0;
		if(correct == null || predict == null ||
				correct.size()!=predict.size()) {
			System.out.println("error");
			return -1;
		}
		int f = 0;
		for(int i=0;i<correct.size();i++){
			//System.out.println(correct.get(i).size()+" "+nn);
			res += getAp(correct.get(i),predict.get(i));
			//System.out.println(res);
			f++;
		}
		//System.out.println(f);
		if(f == 0) return -1;
		return res/f;
	}
	static double getAp(Map<Long, Integer> c,Map<Long, Double> p){
		int R = 0;
		p = MapUtil.sortByValue(p);
		int cnt = 0;
		double sum = 0;
		int CCC=0;
		for(Map.Entry<Long, Integer> entry : c.entrySet()){
			CCC+=entry.getValue();
		}
		for(Map.Entry<Long, Double> entry : p.entrySet()) {
        	Long id = entry.getKey();
        	int t = c.get(id)>0?1:0;
        	//System.out.println("c get id"+c.get(id)+" t"+t);
        	R+=t;
        	cnt++;
        	double pi = 1.0*R/cnt;
        	if(t >0) sum+= pi;
        	
        }
		if(R ==0) return 0;
		return sum / CCC;
	}
	public static void main(String[] args) {
		System.out.println(5>0?1:0);
	}
}

