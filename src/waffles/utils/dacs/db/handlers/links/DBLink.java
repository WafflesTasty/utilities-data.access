package waffles.utils.dacs.db.handlers.links;

import java.util.UUID;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.sets.IterableSet;

/**
 * A {@code DBLink} implements {@code DBHandleable} as a set of parent-child links.
 *
 * @author Waffles
 * @since 31 Oct 2025
 * @version 1.1
 * 
 * 
 * @param <D>  a database type
 * @param <E>  an entity type
 * @see DBHandleable
 * @see IterableSet
 * @see Database
 * @see DBEntity
 */
public interface DBLink<D extends Database<?>, E extends DBEntity<D>> extends DBHandleable<D>, IterableSet<E>
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
	public abstract DBLinkHandler Handler();
	

	@Override
	public default boolean exists(D db)
	{
		return Parent().exists(db);
	}
	
	@Override
	public default boolean update(D db)
	{
		if(exists(db))
		{
			delete(db);
		}
		
		return insert(db);
	}
		
	@Override
	public default UUID GUID()
	{
		return Parent().GUID();
	}



	@Override
	public default boolean delete(D db)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public default boolean insert(D db)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public default boolean select(D db)
	{
		return false;
//		return db.select(this, null);
	}
}