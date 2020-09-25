package businessgame.enums;

public enum CellType {

	J("Jail", 150),
	L("Lottery", 200),
	H("Hotel", 0),
	E("Empty", 0);

	String name;
	int value;

	private CellType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

}
