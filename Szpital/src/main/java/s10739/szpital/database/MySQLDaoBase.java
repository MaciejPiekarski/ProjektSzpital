package s10739.szpital.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class MySQLDaoBase<E extends EntityBase> extends DaoBase<E> {
	protected Statement stmt;

	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement selectAll;
	protected PreparedStatement select;

	public MySQLDaoBase(MySQLUnitOfWork mySQLUnitOfWork) {
		super(mySQLUnitOfWork);
		try {
			Connection connection = mySQLUnitOfWork.getConnection();

			ResultSet resultSet = connection.getMetaData().getTables(null,
					null, null, null);
			boolean exist = false;
			stmt = connection.createStatement();

			while (resultSet.next()) {
				if (resultSet.getString("TABLE_NAME").equalsIgnoreCase(
						getTableName())) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				stmt.executeUpdate(getCreateQuery());
			}
			insert = connection.prepareStatement(getInsertQuery());

			update = connection.prepareStatement(getUpdateQuery());

			delete = connection.prepareStatement("" + "delete from "
					+ getTableName() + " where id=?");

			select = connection.prepareStatement("" + "select * from "
					+ getTableName() + " where id=?");

			selectAll = connection.prepareStatement("" + "select * from "
					+ getTableName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void persistAdd(EntityBase ent) {
		@SuppressWarnings("unchecked")
		E entity = (E) ent;

		try {
			setInsertQuery(entity);
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void persistDelete(EntityBase ent) {
		@SuppressWarnings("unchecked")
		E entity = (E) ent;

		try {
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void persistUpdate(EntityBase ent) {
		@SuppressWarnings("unchecked")
		E entity = (E) ent;
		try {
			setUpdateQuery(entity);
			update.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public E get(int id) {

		try {
			select.setInt(1, id);
			ResultSet resultSet = select.executeQuery();
			while (resultSet.next()) {

				return build(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected abstract String getUpdateQuery();

	protected abstract String getInsertQuery();

	protected abstract String getCreateQuery();

	protected abstract String getTableName();

	protected abstract void setInsertQuery(E entity) throws SQLException;

	protected abstract void setUpdateQuery(E entity) throws SQLException;

	protected abstract E build(ResultSet resultSet) throws SQLException;

}
