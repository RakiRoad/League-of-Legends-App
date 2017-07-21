package rakiroad.riotapp.ThatTastedPurple.requests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rakiroad.riotapp.ThatTastedPurple.lulu.Game;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class GameRequest {
	
	 private static Gson gson = new Gson();
	 
	 public static Game getGameInfo(String matchID, String region, int playerNum ) throws Exception{
	 
			String request = "https://" + region + ".api.riotgames.com/lol/match/v3/matches/" + matchID + "?api_key=" + APIRequest.API_KEY;
		 	APIRequest api = new APIRequest();
		 	api.execute(request).get();
		 	String response = api.resultString;
			JsonObject jsonObj1 = new JsonParser().parse(response).getAsJsonObject();
			
			//JsonArray matches1 = jsonObj.get("matches").getAsJsonArray();
			JsonArray Jarray1 = jsonObj1.getAsJsonArray("participantIdentities"); //retrieve participantIdentities Array
			
			String json = gson.toJson(Jarray1); //converted to string for some reason
			JsonArray Jarray2 = new JsonParser().parse(json).getAsJsonArray(); //??? parsed and put back to JSON array

			JsonObject  jsonObj2 = Jarray2.get(playerNum).getAsJsonObject(); //sets the object to "player" and "participantID"
			JsonElement  jsonObj3 = jsonObj2.getAsJsonObject().get("player");
			//System.out.println(jsonObj3);
			return gson.fromJson(jsonObj3, Game.class);
	 }
}
