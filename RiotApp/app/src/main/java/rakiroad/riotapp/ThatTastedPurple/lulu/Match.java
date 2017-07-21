package rakiroad.riotapp.ThatTastedPurple.lulu;

public class Match{
	private String lane;
	private long gameId;
	private int champion;
	private String platformId;
	private int season;
	private int queue;
	private String role;
	private long timestamp;
	
	public long getGameId(){
		return gameId;
	}
	
	public String toString(){
		return this.gameId + "";
	}
}
