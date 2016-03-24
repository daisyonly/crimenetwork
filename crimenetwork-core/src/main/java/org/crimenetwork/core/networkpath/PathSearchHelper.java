package org.crimenetwork.core.networkpath;

import java.util.ArrayList;
import java.util.List;

import org.crimenetwork.core.metapath.TrieNode;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.entity.relationship.CurrencySim;



public class PathSearchHelper {
     public static List<SearchNode> search(CounterfeitMoney searchNode, String searchType,String prePath,TrieNode paths){
    	 List<SearchNode> res=new ArrayList<SearchNode>();
    	 if(searchType.equals("J")){
    		 for(CurrencySim cs:searchNode.getSimilarCM()){
    			 SearchNode newNode=new SearchNode(prePath,paths,"J");
    			 newNode.counterfeitMoney=cs.getTo();
    			 res.add(newNode);
    		 }
    	 }else{
    		 for(CrimeCase cs:searchNode.getCaseInfos()){
    			 SearchNode newNode=new SearchNode(prePath,paths,"C");
    			 newNode.crimeCase=cs;
    			 res.add(newNode);
    		 }
    	 }
    	 
    	 return res;
     }
     
     public static List<SearchNode> search(CrimeCase searchNode, String searchType,String prePath,TrieNode paths){
    	 List<SearchNode> res=new ArrayList<SearchNode>();
    	 if(searchType.equals("J")){
    		 for(CounterfeitMoney cs:searchNode.getCounterfeitMoneys()){
    			 SearchNode newNode=new SearchNode(prePath,paths,"J");
    			 newNode.counterfeitMoney=cs;
    			 res.add(newNode);
    		 }
    	 }else{
    		 for(SuspectInfo cs:searchNode.getSuspects()){
    			 SearchNode newNode=new SearchNode(prePath,paths,"S");
    			 newNode.suspectInfo=cs;
    			 res.add(newNode);
    		 }
    	 }
    	 
    	 return res;
     }
     
     public static List<SearchNode> search(SuspectInfo searchNode, String searchType,String prePath,TrieNode paths){
    	 List<SearchNode> res=new ArrayList<SearchNode>();
    	 if(searchType.equals("J")){
    		 for(CrimeCase cc:searchNode.getCases()){
    			 SearchNode newNode=new SearchNode(prePath,paths,"C");
    			 newNode.crimeCase=cc;
    			 res.add(newNode);
    		 }
    	 }else{
    		 for(SuspectInfo sui:searchNode.getIdenticalSuspects()){
    			 SearchNode newNode=new SearchNode(prePath,paths,"S");
    			 newNode.suspectInfo=sui;
    			 res.add(newNode);
    		 }
    	 }
    	 
    	 return res;
     }
}
