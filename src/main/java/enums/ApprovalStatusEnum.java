package enums;

public enum ApprovalStatusEnum {
	unapproved("未承認", 0),
	approved("承認済", 1),
	rejected("拒否済", 2);
	
	private String label;
	private int id;

	ApprovalStatusEnum(String label, int id) {
		this.label = label;
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public int getId() {
		return id;
	}
	
	public static ApprovalStatusEnum getById(int id) {
		for (ApprovalStatusEnum approvalStatus : ApprovalStatusEnum.values()) {
			if (approvalStatus.getId() == id) {
				return approvalStatus;
			}
		}
		return null;
	}
}
