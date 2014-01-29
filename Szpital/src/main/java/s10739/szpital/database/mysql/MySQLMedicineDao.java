package s10739.szpital.database.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import s10739.szpital.database.MySQLDaoBase;
import s10739.szpital.database.MySQLUnitOfWork;
import s10739.szpital.database.dao.MedicineDao;
import s10739.szpital.model.Medicine;

public class MySQLMedicineDao extends MySQLDaoBase<Medicine> implements
		MedicineDao {

	public MySQLMedicineDao(MySQLUnitOfWork mySQLUnitOfWork) {
		super(mySQLUnitOfWork);
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE `" + getTableName() + "` SET" + "(`name`)=(?)"
				+ "WHERE `id`=?";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO `" + getTableName() + "`(`name`)"
				+ " VALUES(?)";
	}

	@Override
	protected String getCreateQuery() {
		return "CREATE TABLE `" + getTableName() + "`("
				+ "`id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`name` varchar(50)" + ")";
	}

	@Override
	protected String getTableName() {
		return "medicine";
	}

	@Override
	protected void setInsertQuery(Medicine entity) throws SQLException {
		insert.setString(1, entity.getName());
	}

	@Override
	protected void setUpdateQuery(Medicine entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setInt(2, entity.getId());
	}

	@Override
	protected Medicine build(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		Medicine medicine = new Medicine();
		medicine.setId(resultSet.getInt("id"));
		medicine.setName(resultSet.getString("name"));
		return medicine;
	}

}
