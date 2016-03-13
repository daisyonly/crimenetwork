package org.crimenetwork.core.metapath;

import java.util.Arrays;

public class TrieNode {
	int val;
	TrieNode nodes[];
	public TrieNode(int val) {
		this.nodes=new TrieNode[3];
		Arrays.fill(this.nodes, null);
		this.val=val;
	}

}
