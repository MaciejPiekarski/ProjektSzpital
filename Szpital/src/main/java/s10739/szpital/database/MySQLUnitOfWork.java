package s10739.szpital.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySQLUnitOfWork implements UnitOfWork {

	private Map<EntityBase, UnitOfWorkDao> taskMap;

	Connection connection;

	public MySQLUnitOfWork() {
		taskMap = new HashMap<EntityBase, UnitOfWorkDao>();
		connection = getConnection();
	}

	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed())
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/projekt",
								"admin", "nimda");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void markNew(EntityBase entity, UnitOfWorkDao dao) {
		entity.setEntityOperation(EntityOperation.insert);
		taskMap.put(entity, dao);

	}

	public void markDeleted(EntityBase entity, UnitOfWorkDao dao) {
		entity.setEntityOperation(EntityOperation.delete);
		taskMap.put(entity, dao);

	}

	public void markUpdated(EntityBase entity, UnitOfWorkDao dao) {
		entity.setEntityOperation(EntityOperation.update);
		taskMap.put(entity, dao);

	}

	public void commit() {
		Connection connection = getConnection();

		try {
			connection.setAutoCommit(false);

			for (EntityBase entity : taskMap.keySet()) {
				switch (entity.getEntityOperation()) {
				case insert:
					taskMap.get(entity).persistAdd(entity);
					break;
				case delete:
					taskMap.get(entity).persistDelete(entity);
					break;
				case update:
					taskMap.get(entity).persistUpdate(entity);
					break;
				default:
					break;
				}
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
