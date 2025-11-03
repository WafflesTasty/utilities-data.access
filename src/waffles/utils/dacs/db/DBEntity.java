package waffles.utils.dacs.db;

import java.util.UUID;

/**
 * A {@code DBEntity} defines an entity that can be transferred to a database.
 * Each entity is represented by a unique {@link #GUID()} identifier.
 *
 * @author Waffles
 * @since 31 Oct 2025
 * @version 1.1
 * 
 * 
 * @param <D>  a database type
 * @see Database
 */
public abstract class DBEntity<D extends Database<?>>
{	
	/**
	 * A {@code Getter} queries a single value in a {@code DBEntity}.
	 *
	 * @author Waffles
	 * @since 03 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <E>  an entity type
	 * @see DBEntity
	 */
	@FunctionalInterface
	public static interface Getter<E extends DBEntity<?>>
	{
		/**
		 * Queries an entity with the {@code Getter}.
		 * 
		 * @param e  an entity
		 * @return   a value
		 */
		public abstract Object get(E e);
	}
	
	/**
	 * A {@code Setter} changes a single value in a {@code DBEntity}.
	 *
	 * @author Waffles
	 * @since 03 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <E>  an entity type
	 * @see DBEntity
	 */
	@FunctionalInterface
	public static interface Setter<E extends DBEntity<?>>
	{
		/**
		 * Changes an entity with the {@code Setter}.
		 * 
		 * @param e  an entity
		 * @param v  a value
		 * @return  {@code true} if successful
		 */
		public abstract boolean set(E e, Object v);
	}
	
	
	private UUID uid;

	/**
	 * Creates a new {@code DBEntity}.
	 * 
	 * @param id  a unique identifier
	 */
	public DBEntity(String id)
	{
		this(UUID.fromString(id));
	}
	
	/**
	 * Creates a new {@code DBEntity}.
	 * 
	 * @param id  a unique identifier
	 * 
	 * 
	 * @see UUID
	 */
	public DBEntity(UUID id)
	{
		uid = id;
	}
		
	/**
	 * Creates a new {@code DBEntity}.
	 * A random {@code UUID} is generated.
	 */
	public DBEntity()
	{
		this(UUID.randomUUID());
	}

	
	/**
	 * Deletes the {@code DBEntity}.
	 * 
	 * @param db  a database
	 * @return  {@code true} if deleted
	 */
	public abstract boolean delete(D db);

	/**
	 * Selects the {@code DBEntity}.
	 * 
	 * @param db  a database
	 * @return  {@code true} if selected
	 */
	public abstract boolean select(D db);
	
	/**
	 * Updates the {@code DBEntity}.
	 * 
	 * @param db  a database
	 * @return  {@code true} if updated
	 */
	public abstract boolean update(D db);
	
	
	/**
	 * Returns the {@code DBEntity} id.
	 * 
	 * @return  a unique identifier
	 * 
	 * 
	 * @see UUID
	 */
 	public UUID GUID()
	{
		return uid;
	}
}