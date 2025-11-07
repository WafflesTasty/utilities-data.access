package waffles.utils.dacs.db.access.entity;

import java.util.UUID;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.access.DBAccess;

/**
 * A {@code DBEntity} defines a database object that maps to a table one-to-one.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <D>  a database type
 * @see Database
 * @see DBAccess
 */
public interface DBEntity<D extends Database<?>> extends DBAccess<D>
{
	/**
	 * A {@code DBEntity.Base} implements {@code DBEntity} as a root object.
	 * Its unique identifier can be passed or generated at construction.
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
	public class Base<D extends Database<?>> implements DBEntity<D>
	{			
		private UUID uid;

		/**
		 * Creates a new {@code DBEntity}.
		 * 
		 * @param id  a unique identifier
		 */
		public Base(String id)
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
		public Base(UUID id)
		{
			uid = id;
		}
			
		/**
		 * Creates a new {@code DBEntity}.
		 * A random GUID is generated.
		 */
		public Base()
		{
			this(UUID.randomUUID());
		}


	 	@Override
		public UUID GUID()
		{
			return uid;
		}
	}
}