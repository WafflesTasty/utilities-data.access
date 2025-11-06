package waffles.utils.dacs.db.schema;

import waffles.utils.dacs.db.handlers.DBHandleable;

/**
 * A {@code DBTable} implements a {@code DBSchema} for a single table.
 * 
 * @author Waffles
 * @since 02 Nov 2025
 * @version 1.1
 * 
 * 
 * @param <H>  a handler type
 * @see DBHandleable
 * @see DBSchema
 */
public class DBTable<H extends DBHandleable<?>> extends DBSchema<H>
{		
	private String table;
	
	/**
	 * Creates a new {@code DBTable}.
	 * 
	 * @param t  a database table
	 */
 	public DBTable(String t)
	{
 		table = t;
	}

 	
	@Override
	public Formatter Formatter()
	{
		return () -> table;
	}
}