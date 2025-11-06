package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLInsert} formats an SQL string for a database insert.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 * 
 * @see DBSchema
 * @see Format
 */
public class SQLInsert implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLInsert}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLInsert(DBHandleable<?> h)
	{
		hnd = h;
	}
	
	
	@Override
	public String parse(DBSchema<?> scm)
	{
		SQLFormat fmt = scm.Formatter(SQLOps.INSERT);
		
		
		String sql = "";
		sql += "INSERT" + " ";
		sql += fmt.Table(hnd) + " ";		
		sql += "(" + fmt.Keys(hnd) + ")" + " ";
		sql += "VALUES" + " ";
		sql += "(" + fmt.Values(hnd) + ")" + " ";
		return sql;
	}
}