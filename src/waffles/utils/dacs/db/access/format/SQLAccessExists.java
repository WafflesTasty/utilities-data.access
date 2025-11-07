package waffles.utils.dacs.db.access.format;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;

/**
 * An {@code SQLAccessExists} verifies a {@code DBAccess} in a {@code DBTable}.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBAccess
 */
public class SQLAccessExists implements SQLFormat<DBAccess<?>>
{
	private DBTable<?> tbl;
	
	/**
	 * Creates a new {@code SQLAccessExists}.
	 * 
	 * @param t  a database table
	 * 
	 * 
	 * @see DBTable
	 */
	public SQLAccessExists(DBTable<?> t)
	{
		tbl = t;
	}

	
	@Override
	public String parse(DBAccess<?> ent)
	{	
		String sql = "";
		sql += "SELECT";
		sql += " " + tbl.GUID() + " ";
		sql += "FROM";
		sql += " " + tbl.Name() + " ";
		sql += "WHERE";
		sql += " " + tbl.GUID() + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
}