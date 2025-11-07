package waffles.utils.dacs.db.access.entity.format;

import waffles.utils.dacs.db.access.entity.DBEntity;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap.Format;

/**
 * An {@code SQLEntityInsert} inserts a {@code DBEntity} into a {@code DBTable}.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBEntity
 */
public class SQLEntityInsert implements SQLFormat<DBEntity<?>>
{
	private DBTable<?> tbl;
	
	/**
	 * Creates a new {@code SQLEntityInsert}.
	 * 
	 * @param t  a database table
	 * 
	 * 
	 * @see DBTable
	 */
	public SQLEntityInsert(DBTable<?> t)
	{
		tbl = t;
	}

	
	@Override
	public String parse(DBEntity<?> ent)
	{	
		DBMap map = tbl.Formatter().Map(ent);
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