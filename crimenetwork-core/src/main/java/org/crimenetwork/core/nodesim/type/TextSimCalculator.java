package org.crimenetwork.core.nodesim.type;

import java.util.HashSet;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class TextSimCalculator {
	
	public static double calculate(String text1,String text2){
		if(text1==null||text2==null) return 0;
		List<Term> parse1 = ToAnalysis.parse(text1);
		List<Term> parse2 = ToAnalysis.parse(text2);
		HashSet<String> text1Set=new HashSet<String>();
		HashSet<String> text2Set=new HashSet<String>();
		HashSet<String> unionSet=new HashSet<String>();
		HashSet<String> intersection=new HashSet<String>();
		for(Term word:parse1){
			if(checkWordType(word.getNatureStr())){
				if(!text1Set.contains(word.getName())){
					text1Set.add(word.getName());
				}
			}
		}
		
		for(Term word:parse2){
			if(checkWordType(word.getNatureStr())){
				if(!text2Set.contains(word.getName())){
					text2Set.add(word.getName());
				}
			}
		}
		for(String str:text1Set){
			if(text2Set.contains(str)) intersection.add(str);
			unionSet.add(str);
		}
		for(String str:text2Set){
			if(!unionSet.contains(str)) unionSet.add(str);
		}
		double sim=intersection.size()*1.0/unionSet.size();
		return sim;
		
	}

	private static boolean checkWordType(String natureStr) {
		char c=natureStr.charAt(0);
		if(c=='n'||c=='t'||c=='s'||c=='f'||c=='v'||c=='b'||c=='a'
				||c=='m'||c=='d')
			return true;
		return false;
	}
	

}
