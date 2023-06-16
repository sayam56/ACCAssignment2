package algorithmDesign;

import java.util.Random;

public class Sequences {

	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
	}
	
	public static void task5( int LEN) {
		long totaltime=0;
		int pairCount = 1000;
		long totalDist=0;
		
		Random r = new Random();
		String str = new String();
		
		//generate 1000 pair of random strings
		String s1r = "";
		String s2r = "";
		
		for ( int c =0; c<pairCount; c++){
	        s1r="";
	        s2r="";
//	        System.out.println("Pair Count: "+ (c+1));
	        
	        for( int j = 0; j < LEN; j++ ){
	            s1r += (char) ( 'a' + r.nextInt(26));
	            s2r += (char) ( 'a' + r.nextInt(26));
	        }
			long t1= System.nanoTime();
			int dr = editDistance(s1r, s2r);
			long t2 = System.nanoTime();
			totaltime += (t2-t1);  
			totalDist += dr;
//			System.out.println("Dist between '" + s1r + "' and '" + s2r + "' = " + dr);
//			System.out.println("avg time in nanos: "+totaltime/2);
//			System.out.println('\n');
	}//1000 loop
		System.out.println("avg time required (in nanos) for " +pairCount+" pairs of random strings of length "+LEN+" : " + totaltime/pairCount);
		System.out.println("avg distance between each random pairs of strings: " + totalDist/pairCount);

	}
	
	public static void main(String[] args) {
	int [] wordLength = {10, 20, 50, 100};
	
	for (int i=0; i<wordLength.length; i++) {
//		System.out.println("For Word Length: "+wordLength[i]);
		task5(wordLength[i]);
		System.out.println('\n');
	}
}	

}//class