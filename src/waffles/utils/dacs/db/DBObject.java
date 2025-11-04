package waffles.utils.dacs.db;

import java.util.UUID;

import waffles.utils.dacs.db.schema.DBSchema;

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
	/**
	 * A {@code DBObject.Schema} maps a {@code DBObject} to a unique table row.
	 *
	 * @author Waffles
	 * @since 04 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see DBObject
	 * @see DBSchema
	 */
	public class Schema<O extends DBObject<?>> extends DBSchema<O>
	{
		/**
		 * Creates a new {@code Schema}.
		 * 
		 * @param tbl  a database table
		 */
		public Schema(String tbl)
		{
			super(tbl);
			Getter().put(Database.ID, getGUID());
		}
		
		Value<O> getGUID()
		{
			return e -> e.GUID();
		}
	}
	
	
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