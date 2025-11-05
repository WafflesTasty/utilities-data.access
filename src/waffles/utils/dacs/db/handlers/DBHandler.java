package waffles.utils.dacs.db.handlers;

import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.data.DBRow;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * A {@code DBHandler} manages how data is loaded into a {@code DBHandleable}.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 */
public interface DBHandler
{	
	/**
	 * Loads a database row in the {@code DBHandler}.
	 * 
	 * @param row  a database row
	 * @param scm  a database scheme
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 * @see DBRow
	 */
	public abstract boolean load(DBRow row, DBSchema<?> scm);
	
	/**
	 * Creates a database map in the {@code DBHandler}.
	 * 
	 * @param hnd  a database handler
	 * @param scm  a database schema
	 * @return  a database map
	 * 
	 * 
	 * @see DBHandleable
	 * @see DBSchema
	 * @see DBMap
	 */
	public abstract DBMap map(DBHandleable<?> hnd, DBSchema<?> scm);
}