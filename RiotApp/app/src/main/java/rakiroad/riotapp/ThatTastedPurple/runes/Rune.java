package rakiroad.riotapp.ThatTastedPurple.runes;

public class Rune {
	
	private class MetaRune{
		private int tier;
		private String type;
		private boolean isRune;
	}
	
	private int id;
	private MetaRune rune;
	private String name;
	private String description;
	
	public int getId(){
		return this.id;
	}
	
	public int getTier(){
		return this.rune.tier;
	}
	
	public String getType(){
		return this.rune.type;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String toString(){
		return this.name;
	}
	
}
