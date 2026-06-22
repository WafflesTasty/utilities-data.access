package waffles.utils.dacs.db.access.format;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap.Format;

/**
 * An {@code SQLAccessInsert} inserts a {@code DBAccess} into a {@code DBTable}.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBAccess
 */
public class SQLAccessInsert implements SQLFormat<DBAccess<?>>
{
	private DBSchema<?> scm;
	
	/**
	 * Creates a new {@code SQLAccessInsert}.
	 * 
	 * @param s  a database schema
	 * 
	 * 
	 * @see DBSchema
	 */
	public SQLAccessInsert(DBSchema<?> s)
	{
		scm = s;
	}

	
	@Override
	public String parse(DBAccess<?> acs)
	{	
		DBMap map = scm.Map(acs);
		DBTable<?> tbl = scm.Master();
		Format fmt = map.Formatter();
		
		
		String sql = "";
		sql += "INSERT";
		sql += " " + tbl.Name() + " ";
		sql += "(" + fmt.Keys().condense() + ") ";
		sql += "VALUES";
		sql += "(" + fmt.Values().condense() + ")";
		return sql;
	}
}