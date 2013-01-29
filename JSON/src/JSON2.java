import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;


public class JSON2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws IOException, JSONException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("Prova.json"));
		
		StringBuffer sb = new StringBuffer();
		
		String s;
		
		while ((s=br.readLine())!= null) {
			
			sb.append(s);
			
		}
		
		JSONObject obj = new JSONObject(sb.toString());
		
		System.out.println(obj);
		
		System.out.println(obj.get("Nome"));
		
		
	}

}
