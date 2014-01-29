package s10739.szpital.database;

import s10739.szpital.database.dao.Dao;

public abstract class DaoBase<E extends EntityBase> implements Dao<E>, UnitOfWorkDao{
	
	protected UnitOfWork unitOfWork;
	
	public DaoBase(UnitOfWork unitOfWork){
		this.unitOfWork = unitOfWork;
	}

	public void add(E entity) {
		unitOfWork.markNew(entity, this);
		
	}

	public void remove(E entity) {
		unitOfWork.markDeleted(entity, this);
		
	}

	public void update(E entity) {
		unitOfWork.markUpdated(entity, this);		
	}
	
}
