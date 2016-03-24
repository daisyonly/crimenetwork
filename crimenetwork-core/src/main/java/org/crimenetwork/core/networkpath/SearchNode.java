package org.crimenetwork.core.networkpath;

import org.crimenetwork.core.metapath.TrieNode;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;

public class SearchNode {
	TrieNode paths;
	String prePath;
	CounterfeitMoney counterfeitMoney;
	CrimeCase crimeCase;
	SuspectInfo suspectInfo;
	String flag;
    public SearchNode(String prePath,TrieNode paths,String flag) {
		this.prePath = prePath;
		this.paths = paths;
		this.flag = flag;
	}
}
