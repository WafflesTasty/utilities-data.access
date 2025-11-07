package waffles.utils.dacs.db.access;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.utilities.Identifiable;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;

/**
 * A {@code DBAccess} object maintains a dataset in a {@code Database}.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <D>  a database type
 * @see Identifiable
 * @see Database
 * @see Token
 */
@FunctionalInterface
public interface DBAccess<D extends Database<?>> extends Identifiable, Token
{
	/**
	 * Deletes the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if deleted
	 */
	public default boolean delete(D db)
	{
		return false;
	}

	/**
	 * Fetches the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if exists
	 */
	public default boolean exists(D db)
	{
		return select(db);
	}
	
	/**
	 * Inserts the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if inserted
	 */
	public default boolean insert(D db)
	{
		return false;
	}
	
	/**
	 * Selects the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if selected
	 */
	public default boolean select(D db)
	{
		return false;
	}
	
	/**
	 * Updates the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if updated
	 */
	public default boolean update(D db)
	{
		if(!exists(db))
			return insert(db);
		else
		{
			if(delete(db))
			{
				return insert(db);
			}
		}
		
		return false;
	}
	
	
	@Override
	public default Format<?> Formatter()
	{
		return o -> GUID().toString();
	}
}