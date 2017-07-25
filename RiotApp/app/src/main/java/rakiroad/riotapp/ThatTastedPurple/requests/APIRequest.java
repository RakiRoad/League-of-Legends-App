package rakiroad.riotapp.ThatTastedPurple.requests;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * APIRequests class that sends the requests to Riot's API
 * @author steve
 * @author ralph
 */
public class APIRequest extends AsyncTask<String, Void, String> {
	
	/**
	 * API Key to send requests with
	 */
	public static final String API_KEY = "RGAPI-7f82d451-afa9-4fda-9fcc-d010ca245424";
	public String resultString;
	
	/**
	 * Send a request to Riot's API and get a JSON response
	 * @param urlToRead URL to connect to
	 * @return Parsed data as one String
	 * @throws Exception
	 */
//	public static String getResponse(String urlToRead) throws Exception{
//		StringBuilder result = new StringBuilder();
//		URL url = new URL(urlToRead);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		String line = "";
//		while((line = rd.readLine()) != null){
//			result.append(line);
//		}
//		rd.close();
//		return result.toString();
//	}

	protected String doInBackground(String... urlToRead){
		StringBuilder result = new StringBuilder();
		URL url = null;
		HttpURLConnection conn = null;

		try{
			url = new URL(urlToRead[0]);
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		try{
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			while((line = rd.readLine()) != null){
				result.append(line);
			}
			rd.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		resultString = result.toString();
		return result.toString();
	}

	protected void onPostExecute(String result){
		System.out.println("Response is: \n" + result);
		resultString = result;
	}
	
}
