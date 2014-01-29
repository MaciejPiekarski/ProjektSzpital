package s10739.szpital.model;

import s10739.szpital.database.EntityBase;

public class Medicine extends EntityBase {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
