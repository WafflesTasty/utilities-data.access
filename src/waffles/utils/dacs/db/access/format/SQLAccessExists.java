package waffles.utils.dacs.db.access.format;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * An {@code SQLAccessExists} verifies a {@code DBAccess} in a {@code DBSchema}.
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
	private DBSchema<?> scm;
	
	/**
	 * Creates a new {@code SQLAccessExists}.
	 * 
	 * @param s  a database schema
	 * 
	 * 
	 * @see DBSchema
	 */
	public SQLAccessExists(DBSchema<?> s)
	{
		scm = s;
	}

	
	@Override
	public String parse(DBAccess<?> acs)
	{	
		DBMap map = scm.Map(acs);
		DBTable<?> tbl = scm.Master();
		
		
		String sql = "";
		sql += "SELECT";
		sql += " " + tbl.GUID() + " ";
		sql += "FROM";
		sql += " " + tbl.Name() + " ";
		sql += "WHERE";
		sql += " " + tbl.GUID() + " = ";
		sql += "'" + acs.GUID() + "'";
		for(String key : tbl.Keys())
		{
			DBToken val = map.get(key);
			sql += " AND " + key + " = ";
			sql += val.condense();
		}
		
		return sql;
	}
}