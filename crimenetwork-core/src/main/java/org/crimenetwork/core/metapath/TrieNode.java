package org.crimenetwork.core.metapath;

import java.util.Arrays;

public class TrieNode {
	public int val;
	public TrieNode nodes[];
	public TrieNode(int val) {
		this.nodes=new TrieNode[3];
		Arrays.fill(this.nodes, null);
		this.val=val;
	}

}
