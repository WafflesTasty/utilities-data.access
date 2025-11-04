package waffles.utils.dacs.db.entities.root;

import java.util.UUID;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.entities.DBEntity;

/**
 * A {@code DBObject} implements {@code DBEntity} as a root object.
 * Its identifier can be passed or generated at construction.
 *
 * @author Waffles
 * @since 31 Oct 2025
 * @version 1.1
 * 
 * 
 * @param <D>  a database type
 * @see Database
 * @see DBEntity
 */
public abstract class DBObject<D extends Database<?>> implements DBEntity<D>
{			
	private UUID uid;

	/**
	 * Creates a new {@code DBObject}.
	 * 
	 * @param id  a unique identifier
	 */
	public DBObject(String id)
	{
		this(UUID.fromString(id));
	}
	
	/**
	 * Creates a new {@code DBObject}.
	 * 
	 * @param id  a unique identifier
	 * 
	 * 
	 * @see UUID
	 */
	public DBObject(UUID id)
	{
		uid = id;
	}
		
	/**
	 * Creates a new {@code DBObject}.
	 * A random {@code GUID} is generated.
	 */
	public DBObject()
	{
		this(UUID.randomUUID());
	}


 	@Override
	public UUID GUID()
	{
		return uid;
	}
}