package s10739.szpital.database;

public abstract class EntityBase {
	protected int id;
	
	protected EntityOperation entityOperation = EntityOperation.none;

	public EntityOperation getEntityOperation() {
		return entityOperation;
	}

	public void setEntityOperation(EntityOperation entityOperation) {
		this.entityOperation = entityOperation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 
}
