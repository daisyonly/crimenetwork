package org.crimenetwork.core.networkpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.crimenetwork.core.metapath.TrieNode;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.entity.relationship.CurrencySim;



public class PathSearchHelper {
     public static List<SearchNode> search(CounterfeitMoney searchNode, String searchType,
    		 String prePath,TrieNode paths,ArrayList<String> preNodeIdSet){
    	 List<SearchNode> res=new ArrayList<SearchNode>();
    	 if(searchType.equals("J")){
    		 for(CurrencySim cs:searchNode.getSimilarCM()){
    			 String key="C"+"J"+cs.getTo().getFmid();
    			 if(preNodeIdSet.contains(key)) continue;
    			 SearchNode newNode=new SearchNode(prePath,paths,"J");
    			 newNode.counterfeitMoney=cs.getTo();
    			 newNode.preNodeIdSet=new ArrayList<String>(preNodeIdSet);
    			 newNode.preNodeIdSet.add("J"+newNode.counterfeitMoney.getFmid());
    			 res.add(newNode);
    		 }
    	 }else{
    		 for(CrimeCase cs:searchNode.getCaseInfos()){
    			 String key="C"+cs.getcId();
    			 if(preNodeIdSet.contains(key)) continue;
    			 SearchNode newNode=new SearchNode(prePath,paths,"C");
    			 newNode.crimeCase=cs;
    			 newNode.preNodeIdSet=new ArrayList<String>(preNodeIdSet);
    			 newNode.preNodeIdSet.add("C"+cs.getcId());
    			 res.add(newNode);
    		 }
    	 }
    	 
    	 return res;
     }
     
     public static List<SearchNode> search(CrimeCase searchNode, String searchType,
    		 String prePath,TrieNode paths,ArrayList<String> preNodeIdSet){
    	 List<SearchNode> res=new ArrayList<SearchNode>();
    	 if(searchType.equals("J")){
    		 for(CounterfeitMoney cs:searchNode.getCounterfeitMoneys()){
    			 String key="J"+cs.getFmid();
    			 if(preNodeIdSet.contains(key)) continue;
    			 SearchNode newNode=new SearchNode(prePath,paths,"J");
    			 newNode.counterfeitMoney=cs;
    			 newNode.preNodeIdSet=new ArrayList<String>(preNodeIdSet);
    			 newNode.preNodeIdSet.add("J"+cs.getFmid());
    			 res.add(newNode);
    		 }
    	 }else{
    		 for(SuspectInfo cs:searchNode.getSuspects()){
    			 String key="S"+cs.getsId();
    			 if(preNodeIdSet.contains(key)) continue;
    			 SearchNode newNode=new SearchNode(prePath,paths,"S");
    			 newNode.suspectInfo=cs;
    			 newNode.preNodeIdSet=new ArrayList<String>(preNodeIdSet);
    			 newNode.preNodeIdSet.add("S"+cs.getsId());
    			 res.add(newNode);
    		 }
    	 }
    	 
    	 return res;
     }
     
     public static List<SearchNode> search(SuspectInfo searchNode, String searchType,
    		 String prePath,TrieNode paths,ArrayList<String> preNodeIdSet){
    	 List<SearchNode> res=new ArrayList<SearchNode>();
    	 if(searchType.equals("C")){
    		 for(CrimeCase cc:searchNode.getCases()){
    			 String key="C"+cc.getcId();
    			 if(preNodeIdSet.contains(key)) continue;
    			 SearchNode newNode=new SearchNode(prePath,paths,"C");
    			 newNode.crimeCase=cc;
    			 newNode.preNodeIdSet=new ArrayList<String>(preNodeIdSet);
    			 newNode.preNodeIdSet.add("C"+cc.getcId());
    			 res.add(newNode);
    		 }
    	 }else{
    		 for(SuspectInfo sui:searchNode.getIdenticalSuspects()){
    			 String key="S"+sui.getsId();
    			 if(preNodeIdSet.contains(key)) continue;
    			 SearchNode newNode=new SearchNode(prePath,paths,"S");
    			 newNode.suspectInfo=sui;
    			 newNode.preNodeIdSet=new ArrayList<String>(preNodeIdSet);
    			 newNode.preNodeIdSet.add("S"+sui.getsId());
    			 res.add(newNode);
    		 }
    	 }
    	 
    	 return res;
     }
     
    
}
