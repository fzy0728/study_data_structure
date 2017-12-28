package data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternScore {
//	public static float patternMatchScore(String regex, String matchedStr) {
//		// preprocess
//		regex = groupRegexWildcard(regex);
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(matchedStr);
//
//		// count wildcard and exact match length
//		int wildcardCount = 0;
//		if (matcher.find()) {
//			for (int i = 1; i <= matcher.groupCount(); i++)
//				wildcardCount += matcher.group(i).length();
//		}
//		int exactMatchCount = matchedStr.length() - wildcardCount;
//
////		return sigmoid(exactMatchCount - wildcardCount - PATTERN_SHIFT_SIZE);
//	}
	/**
	 * Group wildcard {@code .*} with {@code ()}, so it can be extracted conveniently.
	 * NOTICE: original group in regex should be convert to non-capturing
	 * group using {@code ?:}.
	 *
	 * @param regex input regex string
	 * @return grouped regex string
	 */
	public static String groupRegexWildcard(String regex) {
		return regex.replaceAll("\\(", "(?:")
				.replaceAll("\\.\\*", "(.*)");
	}
	public static String getChinese(String str) {
		String chineseStr;

		String reg = "[^\u4e00-\u9fa5]";
		chineseStr = str.replaceAll(reg, "");

		return chineseStr;
	}
	public static void main(String []args) {
		 String regex = "我想看(.*)电影";
		 String str = "我想看刘德华电影";
		 regex = groupRegexWildcard(regex);
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(str);
		 int wildcardCount = 0;
		 if (matcher.find()){
		 	for (int i = 1; i<=matcher.groupCount(); i++) {
				System.out.println(matcher.group(i));
				wildcardCount += matcher.group(i).length();
			}
		 }
		 System.out.println(wildcardCount);
		 str =  "java怎么把字符串中的的汉字取出来";
		 System.out.println(getChinese(str));



		System.out.println(str.matches(regex));
	}
}
