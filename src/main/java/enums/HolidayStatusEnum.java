package enums;

public enum HolidayStatusEnum {
	paid("有給", 1),
	unpaid("無給", 2);
	
	private String label;
	private int id;

	HolidayStatusEnum(String label, int id) {
		this.label = label;
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public int getId() {
		return id;
	}
	
	public static HolidayStatusEnum getById(int id) {
		for (HolidayStatusEnum holidayStatus : HolidayStatusEnum.values()) {
			if (holidayStatus.getId() == id) {
				return holidayStatus;
			}
		}
		return null;
	}
}
