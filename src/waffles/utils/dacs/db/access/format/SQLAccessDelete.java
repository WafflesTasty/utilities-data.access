package waffles.utils.dacs.db.access.format;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;

/**
 * An {@code SQLAccessDelete} deletes a {@code DBAccess} from a {@code DBTable}.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBAccess
 */
public class SQLAccessDelete implements SQLFormat<DBAccess<?>>
{
	private DBTable<?> tbl;
	
	/**
	 * Creates a new {@code SQLAccessDelete}.
	 * 
	 * @param t  a database table
	 * 
	 * 
	 * @see DBTable
	 */
	public SQLAccessDelete(DBTable<?> t)
	{
		tbl = t;
	}

	
	@Override
	public String parse(DBAccess<?> ent)
	{	
		String sql = "";
		sql += "DELETE FROM";
		sql += " " + tbl.Name() + " ";
		sql += "WHERE";
		sql += " " + tbl.GUID() + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
}