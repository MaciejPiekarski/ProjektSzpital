package s10739.szpital.database.dao;

import java.util.List;

import s10739.szpital.model.Patient;

public interface PatientDao extends Dao<Patient> {
	public List<Patient> getAll();
}
