import enums.HolidayStatusEnum;
import enums.PositionEnum;
import enums.StoreNameEnum;

public class sample {
	public static void main(String[] args) {
		PositionEnum position = PositionEnum.getById(2);
		System.out.println(position.getLabel());
		StoreNameEnum storeName = StoreNameEnum.getById(2);
		System.out.println(storeName.getLabel());
		HolidayStatusEnum holidayStatus = HolidayStatusEnum.getById(2);
		System.out.println(holidayStatus.getLabel());
	}
}