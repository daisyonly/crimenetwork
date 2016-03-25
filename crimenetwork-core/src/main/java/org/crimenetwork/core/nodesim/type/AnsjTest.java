package org.crimenetwork.core.nodesim.type;


public class AnsjTest {

	public static void main(String[] args) {
		
		 //List<Term> baseparse = BaseAnalysis.parse("2014年5月3日上午，犯罪嫌疑人苏怀利伙同牛俊林在佳县白云山庙会期间非法持有4000多元假币，并假币买东西被受害人彭学梅发现，随后被我局民警发现抓获。");
		   // System.out.println(baseparse);
		
		// TODO Auto-generated method stub
		// List<Term> parse = ToAnalysis.parse("2014年5月3日上午，犯罪嫌疑人苏怀利伙同牛俊林在佳县白云山庙会期间非法持有4000多元假币，并假币买东西被受害人彭学梅发现，随后被我局民警发现抓获。");
		 /*
		 for(Term one: parse){
			 System.out.println(one.getName()+"  "+one.getNatureStr()+"  "
					 +one.getOffe()+ "   "+ one.getRealName()+ "  "+
					 one.getSubTerm());
		 }*/
		 //System.out.println(parse);
		 String text1="2014年5月3日上午，犯罪嫌疑人苏怀利伙同牛俊林佳县白云山庙会期间非法持有4000多元假币，并假币买东西被受害人彭学梅发现，随后被我局民警发现抓获。";
		 String text2="2014年5月3日上午，犯罪嫌疑人苏怀利伙同牛俊林在佳县白云山庙会期间非法持有4000多元假币，并假币买东西被受害人彭学梅发现，随后被我局民警发现抓获。";
		 TextSimCalculator.calculate(text1, text2);
		 System.out.println(TextSimCalculator.calculate(text1, text2));
	}

}
