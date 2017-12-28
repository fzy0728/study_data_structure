package study.Algorithms;

import java.util.Arrays;

public class Edit {
	public static int min(int a, int b, int c) {
		int temp = a<b? a:b;
		temp = temp<c? temp:c;
		return temp;
	}
	public static void levenshtein(String str1, String str2) {
		// 计算两个字符串的长度。
		int len1 = str1.length();
		int len2 = str2.length();
		// 建立上面说的数组，比字符长度大一个空间
		int[][] dif = new int[len1 + 1][len2 + 1];
		// 赋初值，步骤B。
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		// 计算两个字符是否一样，计算左上的值
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {

				System.out.println("i = " + i + " j = " + j + " str1 = "
						+ str1.charAt(i - 1) + " str2 = " + str2.charAt(j - 1));
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 取三个值中最小的
				dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
						dif[i - 1][j] + 1);

				System.out.println("i = " + i + ", j = " + j + ", dif[i][j] = "
						+ dif[i][j]);
			}
		}
		System.out.println("字符串\"" + str1 + "\"与\"" + str2 + "\"的比较");
		// 取数组右下角的值，同样不同位置代表不同字符串的比较
		System.out.println("差异步骤：" + dif[len1][len2]);
		// 计算相似度
		float similarity = 1 - (float) dif[len1][len2]
				/ Math.max(str1.length(), str2.length());
		System.out.println("相似度：" + similarity);
	}

	public static int clsDistance(String str1, String str2) {

		int j;
		int i;

		int mCharALen, mCharBLen;

		mCharALen = str1.length();
		mCharBLen = str2.length();

		int tp1 = -1;
		int tp2 = -1;

		j = Math.min(mCharALen, mCharBLen) - 1;

		for (i = 0; i <= j; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				tp1 = i;
				break;
			}

		}

		if (tp1 == -1) {
			return Math.abs(mCharBLen - mCharALen);
		}

		for (i = 0; i <= j - tp1; i++) {

			if (str1.charAt(mCharALen - i - 1) != str2.charAt(mCharBLen - i
					- 1)) {
				tp2 = i;
				break;
			}
		}

		if (tp2 == -1) {
			return Math.abs(mCharALen - mCharBLen);
		}
		int taBound = mCharALen - tp1 - tp2;

		int tA[] = new int[taBound + 1];

		for (i = 0; i < tA.length; i++) {
			tA[i] = i;

		}
		System.out.println(Arrays.toString(tA));
		int tN1, tN2, tN3;

		for (i = 0; i < mCharBLen - tp1 - tp2; i++) {
			tN1 = tA[0];
			tN2 = tN1 + 1;

			System.out.println("\n" + i + " " + str2.charAt(mCharBLen
					- tp2 - i - 1));

			for (j = 1; j < tA.length; j++) {

				System.out.print(str1.charAt(mCharALen - tp2 - j) + "   ");

				if (str1.charAt(mCharALen - tp2 - j) == str2.charAt(mCharBLen
						- tp2 - i - 1)) {

					tN3 = tN1;
				} else {
					tN3 = Math.min(tA[j], Math.min(tN1, tN2)) + 1;

				}

				tA[j - 1] = tN2;
				tN2 = tN3;
				tN1 = tA[j];

				System.out.println("\ntN1 = " + tN1);
				System.out.println("tN2 = " + tN2);
				System.out.println("tN3 = " + tN3);
			}

			tA[tA.length - 1] = tN2;
			System.out.println("\n" + tA[tA.length - 1]);
		}

		System.out.println("\n" + Arrays.toString(tA));
		return tA[tA.length - 1];
	}
	public static void main(String[] args ) {
		String a = "我先看刘德华点是";
		String b = "我想看刘德华电视";
		levenshtein(a,b);
		clsDistance(a,b);
	}
}
