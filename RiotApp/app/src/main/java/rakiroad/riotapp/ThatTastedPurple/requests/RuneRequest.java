package rakiroad.riotapp.ThatTastedPurple.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rakiroad.riotapp.ThatTastedPurple.runes.Rune;
import rakiroad.riotapp.ThatTastedPurple.runes.RunePage;


public class RuneRequest {
	
	
	private static Gson gson = new Gson();
	
	/**
	 * Creates a Rune object from the given id
	 * @param id Rune id
	 * @return Rune object
	 * @throws Exception
	 */
	public static Rune getRune(int id) throws Exception{
		String request = "https://na1.api.riotgames.com/lol/static-data/v3/runes/" + id + "?locale=en_US&api_key=" + APIRequest.API_KEY;
		APIRequest api = new APIRequest();
		api.execute(request).get();
		String response = api.resultString;
		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
		
		return gson.fromJson(jsonObj, Rune.class);
	}
	
	/**
	 * Get all runes in league's collection
	 * @return
	 * @throws Exception
	 */
	
	//Currently doesn't work because "data" is an object of runes and not an array for some reason
	public static Rune[] getRuneCollection() throws Exception{
		String request = "https://na1.api.riotgames.com/lol/static-data/v3/runes?locale=en_US&api_key=" + APIRequest.API_KEY;
		APIRequest api = new APIRequest();
		api.execute(request).get();
		String response = api.resultString;
		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
		
		JsonArray jArray = jsonObj.getAsJsonArray("data");
		String json = gson.toJson(jArray);
		
		return gson.fromJson(jArray, Rune[].class);
	}
	
	public static RunePage getRunePage(int summonerID) throws Exception{
		String request = "https://na1.api.riotgames.com/lol/platform/v3/runes/by-summoner/" + summonerID + "?api_key=" + APIRequest.API_KEY;
		APIRequest api = new APIRequest();
		api.execute(request).get();
		String response = api.resultString;
		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
		
		JsonArray jArray = jsonObj.getAsJsonArray("pages");
		JsonObject jsonObj2 = jArray.get(0).getAsJsonObject();
		JsonArray jArray2 = jsonObj2.getAsJsonArray("slots");
		System.out.println(jArray2.size());
		ArrayList<Integer> runeids = new ArrayList<Integer>();
		for(JsonElement j : jArray2){
			//System.out.println(j.getAsJsonObject().get("runeId"));
			runeids.add(j.getAsJsonObject().get("runeId").getAsInt());
		}
		
//		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//		for(int i : runeids){
//			map.compute(i, (k, v) -> (v == null) ? 1 : v + 1);
//		}
//		
//		map.entrySet().stream().filter(i -> i.getValue() > 1)
//								.forEach(i -> System.out.println(i.getKey() + " " + i.getValue()));
		
		return null;
	}
	
}
