package waffles.utils.dacs.utilities.db;

import waffles.utils.dacs.db.DBRow;
import waffles.utils.dacs.db.access.DBAccess;

/**
 * A {@code DBLoader} loads SQL data into a {@code DBAccess} object.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @see DBAccess
 */
@FunctionalInterface
public interface DBLoader<A extends DBAccess<?>>
{
	/**
	 * Loads an object with data in a {@code DBSchema}.
	 * 
	 * @param acs  a database access
	 * @param row  a database row
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBRow
	 */
	public abstract boolean load(A acs, DBRow row);
}
