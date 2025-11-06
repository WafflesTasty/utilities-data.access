package waffles.utils.dacs.utilities.db.sql;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.DBHandler;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.format.maps.DBMap;

/**
 * A {@code SQLFormat} defines formatting for a SQL operation.
 *
 * @author Waffles
 * @since 06 Nov 2025
 * @version 1.1
 */
@FunctionalInterface
public interface SQLFormat
{	
	/**
	 * Returns the schema of the {@code SQLFormat}.
	 * 
	 * @return  a database schema
	 * @see DBSchema
	 */
	public abstract DBSchema<?> Schema();
	
	
	/**
	 * Generates a key string from the {@code SQLFormat}.
	 * By default, these contain all getters in a schema.
	 * 
	 * @param hnd  a database handler
	 * @return  a key string
	 * 
	 * 
	 * @see DBHandleable
	 */
	public default String Keys(DBHandleable<?> hnd)
	{
		DBHandler h = hnd.Handler();
		DBMap map = h.map(hnd, Schema());
		DBMap.Format f = map.Formatter();
		return f.Keys().condense();
	}
	
	/**
	 * Generates a pair string from the {@code SQLFormat}.
	 * By default, these contain all getters in a schema,
	 * except for the unique identifier column.
	 * 
	 * @param hnd  a database handler
	 * @return  a pair string
	 * 
	 * 
	 * @see DBHandleable
	 */
	public default String Pairs(DBHandleable<?> hnd)
	{
		DBHandler h = hnd.Handler();
		DBMap map = h.map(hnd, Schema());
		return map.condense();
	}
	
	/**
	 * Generates a value string from the {@code SQLFormat}.
	 * By default, these contain all getters in a schema.
	 * 
	 * @param hnd  a database handler
	 * @return  a value string
	 * 
	 * 
	 * @see DBHandleable
	 */
	public default String Values(DBHandleable<?> hnd)
	{
		DBHandler h = hnd.Handler();
		DBMap map = h.map(hnd, Schema());
		DBMap.Format f = map.Formatter();
		return f.Values().condense();
	}

	/**
	 * Generates a check string from the {@code SQLFormat}.
	 * By default, this checks whether the identifier of the
	 * schema is the same as the identifier of the handler.
	 * 
	 * @param hnd  a database handler
	 * @return  a check string
	 * 
	 * 
	 * @see DBHandleable
	 */
	public default String Check(DBHandleable<?> hnd)
	{
		return Schema().ID() + " = '" + hnd.GUID() + "'";
	}
	
	/**
	 * Generates a table string from the {@code SQLFormat}.
	 * By default, this returns the master table.
	 * 
	 * @param hnd  a database handler
	 * @return  a table string
	 * 
	 * 
	 * @see DBHandleable
	 */
	public default String Table(DBHandleable<?> hnd)
	{
		return Schema().Formatter().Master();
	}
}