import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSON {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JSONException, IOException {
		// TODO Auto-generated method stub

		JSONObject obj = new JSONObject();
		
		obj.put("Nome" , "Pierpaolo").put("eta", new Integer(39));
		
		JSONArray arr = new JSONArray();
		
		arr.put("A").put("B").put("C");
		
		obj.put("array", arr);
		
		FileWriter f = new FileWriter("Prova.json");
		f.write(obj.toString());
		f.flush();
		f.close();
		
	}

}
