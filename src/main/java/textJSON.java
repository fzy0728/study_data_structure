import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class textJSON {
	public static void main(String[] args){

		JSONObject container1 = new JSONObject();
		container1.put("ClassName", "高三一班");
		System.out.println(container1.toString());

		JSONArray className = new JSONArray();
		className.add("高三一班");
		className.add("高三二	班");
		container1.put("className", className);
		System.out.println(container1.toString());
	}
}
