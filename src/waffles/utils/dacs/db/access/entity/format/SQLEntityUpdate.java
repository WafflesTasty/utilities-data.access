package waffles.utils.dacs.db.access.entity.format;

import waffles.utils.dacs.db.access.entity.DBEntity;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.entity.DBTable.Format;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * An {@code SQLEntityUpdate} updates a {@code DBEntity} into a {@code DBTable}.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBEntity
 */
public class SQLEntityUpdate implements SQLFormat<DBEntity<?>>
{
	private DBTable<?> tbl;
	
	/**
	 * Creates a new {@code SQLEntityUpdate}.
	 * 
	 * @param t  a database table
	 * 
	 * 
	 * @see DBTable
	 */
	public SQLEntityUpdate(DBTable<?> t)
	{
		tbl = t;
	}

	
	@Override
	public String parse(DBEntity<?> ent)
	{	
		Format fmt = tbl.Formatter();
		DBMap map = fmt.Map(ent);
		
		
		String sql = "";
		sql += "UPDATE";
		sql += " " + tbl.Name() + " ";
		sql += "SET";
		sql += " " + map.condense() + " ";
		sql += "WHERE";
		sql += " " + tbl.GUID() + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
}