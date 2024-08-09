package enums;

public enum StoreNameEnum {
	shibuya("渋谷店", 1),
	shinsaibashi("心斎橋店", 2);
	
	private String label;
	private int id;
	
	private StoreNameEnum(String label, int id) {
		this.label = label;
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public int getId() {
		return id;
	}
	public static StoreNameEnum getById(int id) {
		for (StoreNameEnum storeName : StoreNameEnum.values()) {
			if (storeName.getId() == id) {
				return storeName;
			}
		}
		return null;
	}
}