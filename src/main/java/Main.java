import util.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONFunction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Main {

	public static void main(String[] args) {
		File file = new File("model/2.xlsx");
		ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
		int positive = 0;
		int negative = 0;
		int truePositive = 0;
		int falsePositive ;
		int trueNegative = 0;
		int falseNegative ;
		float precision;
		float recall;
		float accuracy;
		ArrayList<String> strlist = new ArrayList<String>();
		for(int i = 1 ;i < 301 ; i++){
			String domain = result.get(i).get(1).toString();
			String intent = result.get(i).get(2).toString();

			String jsondemo = result.get(i).get(3).toString();
			JSONObject jsonObject = JSONObject.fromObject(jsondemo);
			System.out.println(jsonObject.get("domain"));
			System.out.println(jsonObject.get("intent"));
			if(domain.equals("UNKNOW")){
				negative++;
				if("UNKNOWN".equals(jsonObject.get("domain").toString())){
					trueNegative++;
				}
			}
			else{
				positive++;
				if(domain.equals(jsonObject.get("domain").toString())&&
						intent.equals(jsonObject.get("intent").toString()))
				{
					truePositive++;}
			}
		}
		falseNegative = negative-trueNegative;
		falsePositive = positive-truePositive;
		System.out.println("positive: "+positive);
		System.out.println("negative: "+negative);
		System.out.println("truePositive: "+truePositive);
		System.out.println("falsePositive: "+falsePositive);
		System.out.println("trueNegative: "+trueNegative);
		System.out.println("falseNegative: "+falseNegative);

		precision = (float) truePositive/positive;
		recall = (float) truePositive/(trueNegative+truePositive);
		accuracy = (float) (trueNegative+truePositive)/(positive+negative);

		System.out.println("precision: "+precision);
		System.out.println("recall: "+recall);
		System.out.println("accuracy: "+accuracy);
	}
}