package s10739.szpital.database.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import s10739.szpital.database.MySQLDaoBase;
import s10739.szpital.database.MySQLUnitOfWork;
import s10739.szpital.database.dao.DoctorDao;
import s10739.szpital.model.Doctor;

public class MySQLDoctorDao extends MySQLDaoBase<Doctor> implements DoctorDao {

	public MySQLDoctorDao(MySQLUnitOfWork mySQLUnitOfWork) {
		super(mySQLUnitOfWork);
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE `" + getTableName() + "` SET" + "(`name`,`surname`)=(?,?)"
				+ "WHERE `id`=?";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO `" + getTableName() + "`(`id`,`name`,`surname`)"
				+ " VALUES('',?,?)";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE `" + getTableName() + "`("
				+ "`id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`name` varchar(50),"
				+ "`surname` varchar(50)"
				+ ")";
	}

	@Override
	protected String getTableName() {
		return "doctor";
	}

	@Override
	protected void setInsertQuery(Doctor entity) throws SQLException {
		insert.setString(1, entity.getName());
		insert.setString(2, entity.getSurname());
	}

	@Override
	protected void setUpdateQuery(Doctor entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setString(2, entity.getSurname());
		update.setInt(3, entity.getId());
	}

	@Override
	protected Doctor build(ResultSet resultSet) throws SQLException {
		Doctor doctor = new Doctor();
		doctor.setId(resultSet.getInt("id"));
		doctor.setName(resultSet.getString("name"));
		doctor.setSurname(resultSet.getString("surname"));
		return doctor;
	}
	
}
