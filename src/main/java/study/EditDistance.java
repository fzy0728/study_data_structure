package study;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditDistance {

	public static int min(int a,int b ,int c){
		int min = a<b?a:b;
		min = min<c?min:c;
		return min;
	}

	public static int distance(String stra,String strb) {
		int alen = stra.length();
		int blen = strb.length();
		int[][] pd = new int[alen + 1][blen + 1];
		for (int i = 0; i <= alen; i++) {
			for (int j = 0; j <= blen; j++) {
				int sub = 1;
				if (i == 0) {
					pd[i][j] = j;
				} else if (j == 0) {
					pd[i][j] = i;
				} else {
					if (stra.charAt(i - 1) == strb.charAt(j - 1)) {
						sub = 0;
					}
					pd[i][j] = min(pd[i - 1][j] + 1,
							pd[i][j - 1] + 1,
							pd[i - 1][j - 1] + sub);
				}
			}
		}

//		for (int i=0 ; i<=alen ; i++) {
//			for (int j=0 ; j<=blen ; j++) {
//				System.out.print(pd[i][j]);
//				System.out.print(' ');
//			}
//			System.out.println();
//		}
		return pd[alen][blen];
	}
	public static int distance(List<SegToken>stra,List<SegToken> strb) {
		int alen = stra.size();
		int blen = strb.size();
		int[][] pd = new int[alen+1][blen+1];
		for (int i=0 ; i<=alen ; i++) {
			for (int j=0 ; j<=blen ; j++) {
				int sub = 1;
				if(i==0) {
					pd[i][j] = j;
				}
				else if(j==0) {
					pd[i][j] = i;
				}
				else {
					if(stra.get(i-1).word.equals(strb.get(j-1).word)) {
						sub = 0;
					}
					pd[i][j] = min(pd[i-1][j]+1,
							pd[i][j-1]+1,
							pd[i-1][j-1]+sub);
				}
			}
		}
		for (int i=0 ; i<=alen ; i++) {
			for (int j=0 ; j<=blen ; j++) {
				System.out.print(pd[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
		return pd[alen][blen];
	}

	public static List<SegToken> jiebacut(String value) {
		JiebaSegmenter segmenter = new JiebaSegmenter();

		return segmenter.process(value, JiebaSegmenter.SegMode.INDEX);

	}

	public static void main(String[] args) {

		String stra;
		String strb;
		Scanner in = new Scanner(System.in);
		System.out.println("先来几组？");
		int a = in.nextInt();
		while (a-- != 0) {
			System.out.println("输入第一个字符串");
			stra = in.next();
			System.out.println("输入第二个字符串");
			strb = in.next();
			int distance = distance(stra, strb);
			System.out.println(distance);
			System.out.println(distance(jiebacut(stra), jiebacut(strb)));
		}
	}
}
