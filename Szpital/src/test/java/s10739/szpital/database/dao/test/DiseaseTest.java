package s10739.szpital.database.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s10739.szpital.database.MySQLUnitOfWork;
import s10739.szpital.database.mysql.MySQLDiseaseDao;
import s10739.szpital.database.mysql.MySQLMedicineDao;
import s10739.szpital.model.Medicine;
import s10739.szpital.model.Disease;

public class DiseaseTest {
	private MySQLDiseaseDao mySqlDiseaseDao;
	private MySQLMedicineDao mySqlMedicineDao;
	private MySQLUnitOfWork mySqlUnitOfWork;

	private Statement drop;

	@Before
	public void setUp() {
		Medicine medicine = new Medicine();
		medicine.setId(1);
		medicine.setName("Aspiryna");

		Disease disease = new Disease();
		disease.setId(1);
		disease.setName("Grypa");
		// TODO disease.setMedicineId();
		try {
			mySqlUnitOfWork = new MySQLUnitOfWork();
			mySqlDiseaseDao = new MySQLDiseaseDao(mySqlUnitOfWork);
			mySqlMedicineDao = new MySQLMedicineDao(mySqlUnitOfWork);

			mySqlMedicineDao.add(medicine);
			mySqlDiseaseDao.add(disease);

			mySqlUnitOfWork.commit();
			drop = mySqlUnitOfWork.getConnection().createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet() {
		Disease d1 = mySqlDiseaseDao.get(1);
		Disease d2 = mySqlDiseaseDao.get(0);
		Disease d3 = mySqlDiseaseDao.get(1);
		
		assertNotNull("Zwrócono null mimo ze obiekt jest w bazie",d1);
		assertNull("Zwrócono obiekt mimo, ¿e nie powinno go byæ bazie",d2);
		assertEquals("Grypa", d1.getName());
		assertNotSame(d1,d3);
	}
	
	@After
	public void teardown()
	{
		try
		{
				drop.executeUpdate("drop table disease");
				drop.executeUpdate("drop table medicine");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
