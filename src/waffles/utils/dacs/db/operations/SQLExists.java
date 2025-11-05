package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLExists} formats an SQL string for a {@code DBEntity} existence check.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see DBSchema
 * @see Format
 */
public class SQLExists implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLExists}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLExists(DBHandleable<?> h)
	{
		hnd = h;
	}

	
	@Override
	public String parse(DBSchema<?> scm)
	{	
		String sql = "";
		sql += "SELECT id FROM";
		sql += " " + scm.Table() + " ";
		sql += "WHERE " + scm.ID() + " = ";
		sql += "'" + hnd.GUID() + "'";
		return sql;
	}
}