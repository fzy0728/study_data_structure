package study.Algorithms;

public class ReverseString {
	public static void Reverse(char[] a, int from, int to) {
		while (from < to) {
			char s = a[from];
			a[from++] = a[to];
			a[to--] = s;
		}
	}
	public static char[] setS(char[] a, int m ){
		Reverse(a,0,m);
		Reverse(a,m+1,a.length-1);
		Reverse(a,0,a.length-1);
		return a;
	}

	public static void main(String[] args) {
		String s = "abcdefg";
		System.out.println(setS(s.toCharArray(),0));
	}
}

