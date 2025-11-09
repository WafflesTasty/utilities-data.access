package waffles.utils.dacs.db.access.links;

import java.util.UUID;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.access.entity.DBEntity;

/**
 * A {@code DBLink} defines a database object that maps to an inner join.
 * This defines a parent-child relationship between entities.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <D>  a database type
 * @see Database
 * @see DBEntity
 */
public interface DBLink<D extends Database<?>> extends DBEntity<D>
{
	/**
	 * Returns a parent {@code DBEntity}.
	 * 
	 * @return  a parent entity
	 * 
	 * 
	 * @see DBEntity
	 */
	public abstract DBEntity<D> Parent();
	
	
	@Override
	public default boolean exists(D db)
	{
		return Parent().exists(db);
	}
	
	@Override
	public default UUID GUID()
	{
		return Parent().GUID();
	}
}