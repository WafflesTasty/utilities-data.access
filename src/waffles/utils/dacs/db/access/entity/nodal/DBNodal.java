package waffles.utils.dacs.db.access.entity.nodal;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.DBEntity;
import waffles.utils.sets.utilities.arboreal.Nodal;

/**
 * A {@code DBNodal} defines a {@code DBAccess} with a parent-child relationship.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <D>  a database type
 * @see Database
 * @see DBEntity
 * @see Nodal
 */
public interface DBNodal<D extends Database<?>> extends Nodal, DBEntity<D>
{
	/**
	 * Returns the architecture of the {@code DBNodal}.
	 * 
	 * @param s  a database schema
	 * @return   a database node
	 * 
	 * 
	 * @see DBSchema
	 * @see DBNode
	 */
	public default DBNode Arch(DBSchema<?> s)
	{
		return Arch();
	}
	
	
	@Override
	public abstract DBNode Arch();
}