package rakiroad.riotapp.ThatTastedPurple.requests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rakiroad.riotapp.ThatTastedPurple.lulu.League;

public class LeagueRequest{

    private static Gson gson = new Gson();

//    public static int getLeague(int id, String region) throws Exception{
//		String request = "https://" + region + ".api.riotgames.com/lol/league/v3/leagues/by-summoner/" + id + "?api_key=94753ea6-ae18-4f15-bff6-7ecc63e766a5";
//		String response = APIRequest.getResponse(request);
//		System.out.println(response);
//		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
//		
//		//return gson.fromJson(jsonObj, Summoner.class);
//		return 0;
//	}
    
    public static League getPosition(int id, String region) throws Exception{
    	
		String request = "https://" + region + ".api.riotgames.com/lol/league/v3/positions/by-summoner/" + id + "?api_key=" + APIRequest.API_KEY;
		APIRequest api = new APIRequest();
		api.execute(request).get();
		String response = api.resultString;
		JsonArray arr = new JsonParser().parse(response).getAsJsonArray();
		JsonObject jobj = arr.get(0).getAsJsonObject();
		
    	return gson.fromJson(jobj, League.class);
    }
}