package org.crimenetwork.core.nodesim.eva;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DCGUtil {
	//List<Map<Long, Integer>> correct;
	
	static List<Integer> buildDoubleList(Map<Long, Integer> correct,
			Map<Long, Double> predict){
		predict = MapUtil.sortByValue(predict);
		List<Integer> res = new ArrayList<Integer>();
		for(Map.Entry<Long, Double> entry : predict.entrySet()) {
        	Long id = entry.getKey();
        	res.add(correct.get(id));
        }
		return res;
		
	}
	public static double getNNDCG(List<Map<Long, Integer>> correct,
			List<Map<Long, Double>> predict,int nn){
		//System.out.println(predict.size()+" "+nn);
		if(correct == null || predict == null ||
				correct.size()!=predict.size()) {
			System.out.println("error");
			return -1;
		}
		double sum = 0;
		int f = 0;
		for(int i=0;i<correct.size();i++){
			List<Integer> tmp = buildDoubleList(correct.get(i),predict.get(i));
			if(tmp.size() <nn) continue;
			sum += getNDCG(tmp,nn);
			f=1;
		}
		if(f == 0) return -1;
		return sum / predict.size();
		
	}
	public static double getNDCG(List<Integer> predict,int nn){
		double s1 = getDCG(predict,nn);
		Collections.sort(predict,new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if(Math.abs(o1-o2) < 1e-6) return 0;
				return o2>o1?1:-1;
			}
		});
		//predict.sort();
		//System.out.println(predict);
		double s2 = getDCG(predict,nn);
		//System.out.println(s1+" "+s2);
		return s1/(s2+1e-6);
		
	}
	public static double getDCG(List<Integer> predict,int nn){
		double res = 0;
		for(int i=1;i<=predict.size();i++){
			double re = predict.get(i-1)*1.0;
			res += 1.0*(Math.pow(2, re)-1) / Math.log(i+1);
			if(nn == i) break;
		}
		return res /nn;
		
	}
	public static void main(String[] args) {
		List<Integer> predict = new ArrayList<>();
		predict.add(2);
		predict.add(4);
		predict.add(1);
		DCGUtil.getNDCG(predict,predict.size());
		
		//test data
		List<Map<Long, Integer>> correct = new ArrayList<Map<Long,Integer>>();
		Map<Long, Integer> cm = new HashMap<Long, Integer>();
		cm.put(1l,0);
		cm.put(2l,2);
		cm.put(3l,3);
		correct.add(cm);
		
		List<Map<Long, Double>> p = new ArrayList<Map<Long,Double>>();
		Map<Long, Double> pm = new HashMap<Long, Double>();
		pm.put(1l,0.8);
		pm.put(2l,0.6);
		pm.put(3l,0.3);
		p.add(pm);
		System.out.println(DCGUtil.getNNDCG(correct, p, 3));
		System.out.println(MapEvaluation.getMap(correct, p, 3));
	}
}
