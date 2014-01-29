package s10739.szpital.model;

import s10739.szpital.database.EntityBase;

public class Disease extends EntityBase {
	private String name;
	private int medicineId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
}
