package rakiroad.riotapp.ThatTastedPurple.lulu;

public class Game {
	private String currentPlatformId;
	private String summonerName;
	private String matchHistoryUri;
	private String platformId;
	private long currentAccountId;
	private int profileIcon;
	private long summonerId;
	private long accountId;

//Purpose is to retrieve list of summoner ID's user has played with recently

	public long getSummonerId(){
		return summonerId;
	}
	
	public String toString(){
		return this.summonerId + "";
	}
}