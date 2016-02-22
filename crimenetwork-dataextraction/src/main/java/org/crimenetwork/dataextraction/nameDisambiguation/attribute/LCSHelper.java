package org.crimenetwork.dataextraction.nameDisambiguation.attribute;

public class LCSHelper {
	
	public static int LCS(String str1, String str2) {
		int[][] opt = new int[str2.length() + 1][str1.length() + 1];
		
		for (int i = 0; i <= str2.length(); i++) {
			opt[i][0] = 0;
		}
		
		for (int j = 0; j <= str1.length(); j++) {
			opt[0][j] = 0;
		}
		
		int max=0;
		for (int j = 1; j <= str1.length(); j++) {
			for (int i = 1; i <= str2.length(); i++) {
				if (str2.charAt(i-1) == str1.charAt(j-1)) {
					opt[i][j] = opt[i-1][j-1] + 1;
				} else {
					opt[i][j] = ( opt[i-1][j] >= opt[i][j-1] ? opt[i-1][j] : opt[i][j-1]);
				}
				if(max<opt[i][j]) max=opt[i][j];
			}
		}	
		return max;
	}

}
