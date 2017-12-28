package data;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ChineseNum {
	/**
	 *
	 * %digits = ("０", 0, "0", 0, "零", 0, "〇", 0,
	 "１", 1, "1", 1, "一", 1, "壹", 1,
	 "２", 2, "2", 2, "二", 2, "貳", 2, "贰", 2, "兩", 2, "两", 2,
	 "３", 3, "3", 3, "三", 3, "參", 3, "叄", 3, "叁", 3,
	 "４", 4, "4", 4, "四", 4, "肆", 4,
	 "５", 5, "5", 5, "五", 5, "伍", 5,
	 "６", 6, "6", 6, "六", 6, "陸", 6, "陆", 6,
	 "７", 7, "7", 7, "七", 7, "柒", 7,
	 "８", 8, "8", 8, "八", 8, "捌", 8,
	 "９", 9, "9", 9, "九", 9, "玖", 9);
	 */
	public static HashMap<String ,Integer> digits = new HashMap<String, Integer>(){{
		put("０", 0);put("0", 0);put("零", 0);put("〇", 0);
		put("１", 1);put("1", 1);put("一", 1);put("壹", 1);
		put("２", 2);put("2", 2);put("二", 2);put("貳", 2);put("贰", 2);put("兩", 2);put("两", 2);
		put("３", 3);put("3", 3);put("三", 3);put("參", 3);put("叄", 3);put("叁", 3);
		put("４", 4);put("4", 4);put("四", 4);put("肆", 4);
		put("５", 5);put("5", 5);put("五", 5);put("伍", 5);
		put("６", 6);put("6", 6);put("六", 6);put("陸", 6);put("陆", 6);
		put("７", 7);put("7", 7);put("七", 7);put("柒", 7);
		put("８", 8);put("8", 8);put("八", 8);put("捌", 8);
		put("９", 9);put("9", 9);put("九", 9);put("玖", 9);

	}};

	public static double ChineseToEnglishFull(String s) {
		int cnumlength;
		int negative = 0;
		boolean afterdecimal = false;
		double power = 0;
		double total = 0,leveltotal = 0;

		s = s.replaceAll("万亿","兆");
		s = s.replaceAll("萬億","兆");
		s = s.replaceAll("亿万","兆");
		s = s.replaceAll("億萬","兆");
		s = s.replaceAll("個","");
		s = s.replaceAll("个","");
		s = s.replaceAll("廿","二十");
		s = s.replaceAll("卄","二十");
		s = s.replaceAll("卅","三十");
		s = s.replaceAll("卌","四十");
		cnumlength = s.length();
		for(int i = 0; i<cnumlength; i++) {
			char cchar = s.charAt(i);
			if(i == 0 && (cchar == '负' ||cchar == '負' ||cchar == '-' )) {
				 negative = 1;
			} else if (i == 0 && cchar =='第') { //ordinal
				// Do nothing, handled elsewhere
			} else if (cchar == '點' ||cchar == '点' ||cchar == '.' ||cchar == '．'||cchar == '。' ) {
				afterdecimal = true;
				power = -1;

			} else if (cchar == '兆') {
				power = 12;
				if(leveltotal == 0){
					leveltotal =1;
				}
				total += leveltotal * Math.pow(10,power);
				leveltotal = 0;
				power -= 4;
			} else if (cchar == '億' || cchar == '亿') {
				power = 8;
				if(leveltotal == 0){
					leveltotal =1;
				}
				total += leveltotal * Math.pow(10,power);
				leveltotal = 0;
				power -= 4;
			} else if (cchar == '万' || cchar == '萬') {
				power = 4;
				if(leveltotal == 0){
					leveltotal =1;
				}
				total += leveltotal * Math.pow(10,power);
				leveltotal = 0;
				power -= 4;
			} else if (cchar == '千' || cchar == '仟') {
				leveltotal += 1000;
			} else if (cchar == '百' || cchar == '佰') {
				leveltotal += 100;
			} else if (cchar == '十' || cchar == '拾') {
				leveltotal += 10;
			} else if (cchar == '零' || cchar == '0'||
					cchar == '〇'|| cchar == '０') {
				power = 0;
			} else if (digits.containsKey(String.valueOf(cchar))) {
				int digitval = digits.get(String.valueOf(cchar));
				if(afterdecimal) {
					leveltotal += digitval * Math.pow(10,power);
					power--;
					while(i+1 < cnumlength && digits.containsKey(String.valueOf(s.charAt(i+1)))) {
						leveltotal += digits.get(String.valueOf(s.charAt(i+1))) * Math.pow(10,power);
						power--;
						i++;
					}
				}else if(i+1 < cnumlength) {
					char nextcchar = s.charAt(i+1);
					if (nextcchar == '十' || nextcchar == '拾') {
						leveltotal += digitval * 10;
						i++;
					} else if (nextcchar == '百' || nextcchar == '佰') {
						leveltotal += digitval * 100;
						i++;
					} else if (nextcchar == '千' || nextcchar == '仟') {
						leveltotal += digitval * 1000;
						i++;
					} else if(digits.containsKey(nextcchar)) {
						leveltotal *= 10;
						leveltotal += digitval;

						while(i+1 < cnumlength && digits.containsKey(String.valueOf(s.charAt(i+1)))) {
							leveltotal *= 10;
							leveltotal += digits.get(String.valueOf(s.charAt(i+1)));
							i++;
						}
					}else {
						leveltotal += digitval;
					}
				} else {
					if(i+1 == cnumlength && i > 0) {
						char prechar = s.charAt(i-1);
						if (prechar == '兆') {
							leveltotal += digitval * Math.pow(10,11);
						} else if (prechar == '億' || prechar == '亿') {
							leveltotal += digitval * Math.pow(10,7);
						} else if (prechar == '万' || prechar == '萬') {
							leveltotal += digitval * Math.pow(10,3);
						} else if (prechar == '千' || prechar == '仟') {
							leveltotal += digitval * 100;
						} else if (prechar == '百' || prechar == '佰') {
							leveltotal += digitval * 10;
						} else {
							leveltotal += digitval;
						}
					}
				}
			} else {
				System.out.println("Seems to be an error in the number.");
				return 0;
			}
		}
		total += leveltotal;
		if (negative == 1)
			total = -total;
		return total;
	}
	public static void main(String[]  args) {

		String s = "10亿两千三百卄一";
		String s1 = "三点14一伍92陆53589793238462643";
		String s2 = "1千两百3十六万5千1百二";
		String ss2 = "两百3十六万5千1百二";
		String s3 = "1千两百3十六万5千1百二。五5七陆";
		String s4 = "1千两百30六万5千100二";

		BigDecimal bigDecimal1 = new BigDecimal(ChineseToEnglishFull(s));
		BigDecimal bigDecimal2 = new BigDecimal(ChineseToEnglishFull(s1));
		BigDecimal bigDecimal3 = new BigDecimal(ChineseToEnglishFull(s2));
		BigDecimal bigDecimal4 = new BigDecimal(ChineseToEnglishFull(s3));
		BigDecimal bigDecimal5 = new BigDecimal(ChineseToEnglishFull(s4));

		System.out.println(ChineseToEnglishFull(s));
		System.out.println(ChineseToEnglishFull(s1));
		System.out.println(ChineseToEnglishFull(ss2));
		System.out.println(bigDecimal1.toString());
		System.out.println(bigDecimal2.toString());
		System.out.println(bigDecimal3.toString());
		System.out.println(bigDecimal4.toString());
		System.out.println(bigDecimal5.toString());

	}
}
