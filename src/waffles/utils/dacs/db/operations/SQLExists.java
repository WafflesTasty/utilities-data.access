package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLExists} formats an SQL string for a database existence check.
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
		SQLFormat fmt = scm.Formatter(SQLOps.SELECT);
		
		
		String sql = "";
		sql += "SELECT" + " ";
		sql += fmt.Keys(hnd) + " ";
		sql += "FROM" + " ";
		sql += fmt.Table(hnd) + " ";
		sql += "WHERE" + " ";
		sql += fmt.Check(hnd);
		return sql;
	}
}