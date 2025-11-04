package waffles.utils.dacs.db.entities.root;

import java.util.UUID;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.entities.DBEntity;

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
public interface DBLink<D extends Database<?>> extends DBEntity<D>
{			
	/**
	 * Returns the parent of the {@code DBLink}.
	 * 
	 * @return  a parent object
	 * 
	 * 
	 * @see DBObject
	 */
	public abstract DBEntity<D> Parent();

	
 	@Override
	public default UUID GUID()
	{
		return Parent().GUID();
	}
}