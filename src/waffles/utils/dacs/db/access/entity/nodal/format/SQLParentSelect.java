package waffles.utils.dacs.db.access.entity.nodal.format;

import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.entity.nodal.DBLink;
import waffles.utils.dacs.db.access.entity.nodal.DBNodal;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap.Format;

/**
 * An {@code SQLParentSelect} selects a {@code DBNodal} from a {@code DBTable}.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBNodal
 */
public class SQLParentSelect implements SQLFormat<DBNodal<?>>
{
	private DBLink<?> scm;
	
	/**
	 * Creates a new {@code SQLParentSelect}.
	 * 
	 * @param s  a database link
	 * 
	 * 
	 * @see DBLink
	 */
	public SQLParentSelect(DBLink<?> s)
	{
		scm = s;
	}

	
	@Override
	public String parse(DBNodal<?> acs)
	{	
		DBMap map = scm.Map(acs);
		DBTable<?> tbl = scm.Master();
		Format fmt = map.Formatter();
		
		
		String sql = "";
		sql += "SELECT";
		sql += " " + fmt.Keys().condense() + " ";
		sql += "FROM";
		sql += " " + tbl.Name() + " ";
		sql += "WHERE";
		sql += " " + scm.GUID() + " = ";
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