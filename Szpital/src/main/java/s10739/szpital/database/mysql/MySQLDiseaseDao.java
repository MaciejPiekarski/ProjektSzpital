package s10739.szpital.database.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import s10739.szpital.database.MySQLDaoBase;
import s10739.szpital.database.MySQLUnitOfWork;
import s10739.szpital.database.dao.DiseaseDao;
import s10739.szpital.model.Disease;

public class MySQLDiseaseDao extends MySQLDaoBase<Disease> implements DiseaseDao {

	public MySQLDiseaseDao(MySQLUnitOfWork mySQLUnitOfWork) {
		super(mySQLUnitOfWork);
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE `" + getTableName() + "` SET" + "(`name`,`medicine_id`)=(?,?)"
				+ "WHERE `id`=?";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO `" + getTableName() + "`(`id`,`name`,`medicine_id`)"
				+ " VALUES('',?,?)";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE `" + getTableName() + "`("
				+ "`id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`name` varchar(50),"
				+ "`medicine_id` int"
				+ ")";
	}

	@Override
	protected String getTableName() {
		return "disease";
	}

	@Override
	protected void setInsertQuery(Disease entity) throws SQLException {
		insert.setString(1, entity.getName());
		insert.setInt(2, entity.getMedicineId());
	}

	@Override
	protected void setUpdateQuery(Disease entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setInt(2, entity.getMedicineId());
		update.setInt(3, entity.getId());
	}

	@Override
	protected Disease build(ResultSet resultSet) throws SQLException {
		Disease disease = new Disease();
		disease.setId(resultSet.getInt("id"));
		disease.setName(resultSet.getString("name"));
		disease.setMedicineId(resultSet.getInt("medicine_id"));
		return disease;
	}
	
}
