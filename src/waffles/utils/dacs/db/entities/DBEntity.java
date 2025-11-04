package waffles.utils.dacs.db.entities;

import java.util.UUID;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.lang.tokens.Token;

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
public interface DBEntity<D extends Database<?>>
{	
	/**
	 * Defines the unique null identifier.
	 */
	public static final UUID NULL_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
	
	/**
	 * A {@code DBEntity.Schema} maps a {@code DBEntity} to a unique table row.
	 *
	 * @author Waffles
	 * @since 04 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see DBEntity
	 * @see DBSchema
	 */
	public static class Schema<O extends DBEntity<?>> extends DBSchema<O>
	{
		/**
		 * Creates a new {@code Schema}.
		 * 
		 * @param tbl  a database table
		 */
		public Schema(String tbl)
		{
			super(tbl);	Getter().put(ID(), getGUID());
		}
		
		Value<O> getGUID()
		{
			return e -> e.GUID();
		}
	}
	
	
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
		 * @return   a token
		 */
		public abstract Token get(E e);
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
	
	/**
	 * A {@code Value} returns a single object in a {@code DBEntity}.
	 *
	 * @author Waffles
	 * @since 03 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <E>  an entity type
	 * @see DBEntity
	 */
	public static interface Value<E extends DBEntity<?>>
	{
		/**
		 * Queries an entity for the {@code Value}.
		 * 
		 * @param e  an entity
		 * @return   a value
		 */
		public abstract Object get(E e);
	}
	
		
	/**
	 * Deletes the {@code DBEntity}.
	 * 
	 * @param db  a database
	 * @return  {@code true} if deleted
	 */
	public abstract boolean delete(D db);

	/**
	 * Fetches the {@code DBEntity}.
	 * 
	 * @param db  a database
	 * @return  {@code true} if exists
	 */
	public abstract boolean exists(D db);
	
	/**
	 * Inserts the {@code DBEntity}.
	 * 
	 * @param db  a database
	 * @return  {@code true} if inserted
	 */
	public abstract boolean insert(D db);
	
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
 	public abstract UUID GUID();
}