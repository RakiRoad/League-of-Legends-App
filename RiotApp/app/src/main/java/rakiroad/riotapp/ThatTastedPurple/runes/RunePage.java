package rakiroad.riotapp.ThatTastedPurple.runes;


public class RunePage {
	
	private boolean current;
	
	private Rune[] runes;
	
	private int id;
	private String name;
	
	public RunePage(boolean current, Rune[] runes, int id, String name){
		this.current = current;
		this.runes = runes;
		this.id = id;
		this.name = name;
	}
	
	
	public Rune[] getRunes(){
		return this.runes;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
}
