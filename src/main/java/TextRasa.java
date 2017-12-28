import net.sf.json.JSONObject;
import util.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class TextRasa {

	public static void main(String[] args){

		File filexlsx = new File("model/text2.xlsx");
		ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(filexlsx);
		int size = result.size();
		int[][] a = new int[9][9] ;

		for (int i = 0;i<a.length;i++){
			for (int j = 0;j<a[i].length;j++){
				a[i][j] = 0;
			}
		}

		String[] intentList = {"TV/CHANGE_CHANNEL","APP/OPEN","APP/APPservice"
				,"TV/CHANGE_SOURCE","VIDEO/RECOMMEND","VIDEO/QUERY",
				"MUSIC/QUERY","MUSIC/RADIO","PLAYER/SET"};

		ArrayList<String> intentArray = new ArrayList<String>();

		for (String temp:intentList){
			intentArray.add(temp);
		}



		for (int i = 0 ; i<size ; i++) {
			JSONObject textjson = JSONObject.fromObject(result.get(i).get(1));

			String realintent = result.get(i).get(2).toString();

			String serveranswer = JSONObject.fromObject(textjson.get("intent")).get("name").toString();

			a[intentArray.indexOf(realintent)][intentArray.indexOf(serveranswer)]++;
		}

		int num = 0;
		int sum = 0;
		for (int i = 0;i<a.length;i++){
			System.out.printf("%20s",intentArray.get(i)+"\t\t");
			for (int j = 0;j<a[i].length;j++){
				System.out.print(a[i][j]+"\t");
				sum += a[i][j];
				if(i==j){
					num += a[i][j];
				}
			}
			System.out.println();
		}
		System.out.println("accuracy = "+(float)num/sum);
	}
}
