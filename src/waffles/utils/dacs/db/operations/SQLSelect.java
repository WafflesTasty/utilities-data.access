package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLSelect} formats an SQL string for a {@code DBEntity} select.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see DBSchema
 * @see Format
 */
public class SQLSelect implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLSelect}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLSelect(DBHandleable<?> h)
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