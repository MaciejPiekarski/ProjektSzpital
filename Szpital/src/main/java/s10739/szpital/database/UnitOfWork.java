package s10739.szpital.database;

public interface UnitOfWork {
    public void markNew(EntityBase entity, UnitOfWorkDao dao);
    public void markDeleted(EntityBase entity, UnitOfWorkDao dao);
    public void markUpdated(EntityBase entity, UnitOfWorkDao dao);
    public void commit();
    public void close();
}
