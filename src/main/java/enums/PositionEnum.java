package enums;

public enum PositionEnum {
	fullTime("正社員", 1),
	partTime("アルバイト", 2);
	
	private String label;
	private int id;

	PositionEnum(String label, int id) {
		this.label = label;
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public int getId() {
		return id;
	}
	
	public static PositionEnum getById(int id) {
		for (PositionEnum holidayStatus : PositionEnum.values()) {
			if (holidayStatus.getId() == id) {
				return holidayStatus;
			}
		}
		return null;
	}
}
