package enums;

public enum ExceptionShiftCategoryEnum {
	addShift("addShift", 1),
	removeShift("removeShift", 2),
	changeTime("changeTime", 3);
	
	private String label;
	private int id;

	ExceptionShiftCategoryEnum(String label, int id) {
		this.label = label;
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public int getId() {
		return id;
	}
	
	public static ExceptionShiftCategoryEnum getByLabel(String label) {
		System.out.println(label);
		for (ExceptionShiftCategoryEnum category : ExceptionShiftCategoryEnum.values()) {
			if (category.getLabel().equals(label)) {
				return category;
			}
		}
		return null;
	}
}
