package s10739.szpital.database.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import s10739.szpital.database.MySQLDaoBase;
import s10739.szpital.database.MySQLUnitOfWork;
import s10739.szpital.database.dao.PatientDao;
import s10739.szpital.model.Patient;

public class MySQLPatientDao extends MySQLDaoBase<Patient> implements
		PatientDao {

	public MySQLPatientDao(MySQLUnitOfWork mySQLUnitOfWork) {
		super(mySQLUnitOfWork);
	}

	public List<Patient> getAll() {

		List<Patient> patients = new ArrayList<Patient>();

		try {
			ResultSet resultSet = selectAll.executeQuery();
			while (resultSet.next()) {
				patients.add(build(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patients;
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE `" + getTableName() + "` SET" + "(`name`,`surname`,`disease_id`,`doctor_id`)=(?,?,?,?)"
				+ "WHERE `id`=?";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO `" + getTableName() + "`(`id`,`name`,`surname`,`disease_id`,`doctor_id`)"
				+ " VALUES('',?,?,?,?)";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE `" + getTableName() + "`("
				+ "`id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`name` varchar(50)"
				+ "`surname` varchar(50)"
				+ "`disease_id` int"
				+ "`doctor_id` int"
				+ ")";
	}

	@Override
	protected String getTableName() {
		return "patient";
	}

	@Override
	protected void setInsertQuery(Patient entity) throws SQLException {
		insert.setString(1, entity.getName());
		insert.setString(2, entity.getSurname());
		insert.setInt(3, entity.getDiseaseId());
		insert.setInt(4, entity.getDoctorId());
	}

	@Override
	protected void setUpdateQuery(Patient entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setString(2, entity.getSurname());
		update.setInt(3, entity.getDiseaseId());
		update.setInt(4, entity.getDoctorId());
		update.setInt(5, entity.getId());
	}

	@Override
	protected Patient build(ResultSet resultSet) throws SQLException {
		Patient patient = new Patient();
		patient.setId(resultSet.getInt("id"));
		patient.setName(resultSet.getString("name"));
		patient.setSurname(resultSet.getString("surname"));
		patient.setDiseaseId(resultSet.getInt("disease_id"));
		patient.setDoctorId(resultSet.getInt("doctor_id"));
		return patient;
	}

}
