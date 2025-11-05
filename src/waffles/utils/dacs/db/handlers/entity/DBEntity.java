package waffles.utils.dacs.db.handlers.entity;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.DBHandler;

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
 * @see DBHandleable
 * @see Database
 */
public interface DBEntity<D extends Database<?>> extends DBHandleable<D>
{
	@Override
	public default DBHandler Handler()
	{
		return new DBEntityHandler(this);
	}
}