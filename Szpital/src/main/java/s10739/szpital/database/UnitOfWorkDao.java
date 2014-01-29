package s10739.szpital.database;

public interface UnitOfWorkDao {
    public void persistAdd(EntityBase entity);
    public void persistDelete(EntityBase entity);
    public void persistUpdate(EntityBase entity);
}
