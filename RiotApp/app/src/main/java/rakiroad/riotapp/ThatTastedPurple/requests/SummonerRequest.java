package rakiroad.riotapp.ThatTastedPurple.requests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rakiroad.riotapp.ThatTastedPurple.summoner.Summoner;

/**
 * SummonerRequests class that creates requests related to summoners and sends these requests to APIRequests
 * @author steve
 * @author ralph
 */
public class SummonerRequest {
	
	private static Gson gson = new Gson();
	
	/**
	 * Gets the summoner info id, name, profileIconId, revisionDate, and summonerLevel
	 * @param id Summoner's id
	 * @param region Summoner's region
	 * @return Summoner object with the parsed data
	 * @throws Exception
	 */
	public static Summoner getSummoner(int id, String region) throws Exception{
		String request = "https://" + region + ".api.riotgames.com/lol/summoner/v3/summoners/" + id + "?api_key=" + APIRequest.API_KEY;
		APIRequest api = new APIRequest();
		api.execute(request).get();
		String response = api.resultString;
		System.out.println("HOW DO YOU DO");
		System.out.println(response);
		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
		
		return gson.fromJson(jsonObj, Summoner.class);
	}
	
	/**
	 * Gets the summoner info id, name, profileIconId, revisionDate, summonerLevel, and accountId
	 * @param summonerName Summoner's IGN
	 * @param region Summoner's region
	 * @return Summoner object with the parsed data
	 * @throws Exception
	 */
	public static Summoner getSummoner(String summonerName, String region) throws Exception{
		String request = summonerName.contains(" ") ? "https://" + region + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + replaceSpaces(summonerName) + "?api_key=" + APIRequest.API_KEY :
														"https://" + region + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summonerName + "?api_key=" + APIRequest.API_KEY;
		APIRequest api = new APIRequest();
		api.execute(request).get();
		String response = api.resultString;
		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
		
		return gson.fromJson(jsonObj, Summoner.class);
	}
	
	/**
	 * Gets the total champion mastery score for a given summoner
	 * @param id Summoner's ID
	 * @param region Summoner's region
	 * @return int value holding the mastery score
	 * @throws Exception
	 */
	public static int getChampionMasteryScore(int id, String region) throws Exception{
		String request = "https://" + region + ".api.riotgames.com/lol/champion-mastery/v3/scores/by-summoner/" + id + "?api_key=" + APIRequest.API_KEY;
		APIRequest api = new APIRequest();
		api.execute(request).get();
		String response = api.resultString;
		
		return Integer.parseInt(response); 
	}
	
	public static String replaceSpaces(String str) {
	    String[] words = str.split(" ");
	    StringBuilder sentence = new StringBuilder(words[0]);

	    for (int i = 1; i < words.length; ++i) {
	        sentence.append("%20");
	        sentence.append(words[i]);
	    }

	    return sentence.toString();
	}
	
    
	
}
