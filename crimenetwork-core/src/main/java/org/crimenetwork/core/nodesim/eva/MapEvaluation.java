package org.crimenetwork.core.nodesim.eva;
import java.util.*;
public class MapEvaluation {

	List<Map<Long, Integer>> correct;
	List<Map<Long, Double>> predict;

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
			if(correct.get(i).size() <nn) continue;
			res += getAp(correct.get(i),predict.get(i),nn);
			f=1;
		}
		//System.out.println(f);
		if(f == 0) return -1;
		return res/correct.size();
	}
	static double getAp(Map<Long, Integer> c,Map<Long, Double> p,int nn){
		int R = 0;
		p = MapUtil.sortByValue(p);
		int cnt = 0;
		double sum = 0;
		for(Map.Entry<Long, Double> entry : p.entrySet()) {
        	Long id = entry.getKey();
        	int t = c.get(id)>0?1:0;
        	R+=t;
        	cnt++;
        	double pi = 1.0*R/cnt;
        	sum += pi * t;
        	if(cnt == nn) break;
        }
		if(R ==0) return 0;
		return sum / R;
	}
	public static void main(String[] args) {
		System.out.println(5>0?1:0);
	}
}

