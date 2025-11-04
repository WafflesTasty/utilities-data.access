package waffles.utils.dacs.db.entities.root;

import java.util.UUID;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.entities.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;

/**
 * A {@code DBLink} implements {@code DBEntity} as a parent-child link.
 * Its identifier is inherited from the parent object.
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
public abstract class DBLink<D extends Database<?>> implements DBEntity<D>
{		
	/**
	 * A {@code DBLink.Schema} maps a {@code DBLink} to a table row.
	 *
	 * @author Waffles
	 * @since 04 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see DBSchema
	 * @see DBLink
	 */
	public class Schema<O extends DBLink<?>> extends DBSchema<O>
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
	
	
	private DBObject<D> parent;

	/**
	 * Creates a new {@code DBLink}.
	 * 
	 * @param p  a parent object
	 * 
	 * 
	 * @see DBObject
	 */
	public DBLink(DBObject<D> p)
	{
		parent = p;
	}
	

 	@Override
	public UUID GUID()
	{
		return parent.GUID();
	}
}