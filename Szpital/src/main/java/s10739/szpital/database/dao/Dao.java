package s10739.szpital.database.dao;

import s10739.szpital.database.EntityBase;

public interface Dao<E extends EntityBase> {
	public void add(E entity);
	public void remove(E entity);
	public E get(int id);
	public void update(E entity);
}