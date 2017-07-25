package rakiroad.riotapp.ThatTastedPurple.summoner;


import rakiroad.riotapp.ThatTastedPurple.lulu.Game;
import rakiroad.riotapp.ThatTastedPurple.lulu.League;
import rakiroad.riotapp.ThatTastedPurple.lulu.Match;

/**
 * Summoner class that represents a summoner in LOL. This will act as a summoner's profile.
 * @author steve
 * @author ralph
 */
public class Summoner {

	private long id;
	private String name;
	private int profileIconId;
	private String revisionDate;
	private int summonerLevel;
	private int accountId;
	
	private int championMasteryScore;
	
	private League league;
	private Match match;
	private Game game;

	public Summoner(long id, String name, int profileIconId, String revisionDate, int summonerLevel){
		this.id = id;
		this.name = name;
		this.profileIconId = profileIconId;
		this.revisionDate = revisionDate;
		this.summonerLevel = summonerLevel;
	}
	
	
	//---------- Getters ----------//
	
	public long getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getProfileIconId(){
		return this.profileIconId;
	}
	
	public String getRevisionDate(){
		return this.revisionDate;
	}
	
	public int getSummonerLevel(){
		return this.summonerLevel;
	}
	
	public int getAccountId(){
		return this.accountId;
	}
	
	public int getChampionMasteryScore(){
		return this.championMasteryScore;
	}
	
	public League getLeague(){
		return this.league;
	}
	
	public Match getMatch(){
		return this.match;
	}
	
	public Game getGame(){
		return this.game;
	}
	
	
	//---------- Setters ----------//
	//public void setId(long summonerID) { this.id = summonerID; }

	public void setChampionMasteryScore(int score){
		this.championMasteryScore = score;
	}
	
	public void setLeague(League res1){
		this.league = res1;
	}
	
	public void setMatch(Match res2){
		this.match =res2;
	}
	
	public void setGame(Game res3){
		this.game = res3;
	}
	
	/**
	 * Summoner toString
	 */
	public String toString(){
		return this.name;
	}
	
}
