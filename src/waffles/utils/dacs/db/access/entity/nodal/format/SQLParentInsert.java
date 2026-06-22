package waffles.utils.dacs.db.access.entity.nodal.format;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.entity.nodal.DBNodal;
import waffles.utils.dacs.db.access.entity.nodal.DBNode;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap.Format;

/**
 * An {@code SQLParentInsert} inserts a {@code DBAccess} into a {@code DBTable}.
 * Note: Deprecated because of conflicts with GUID columns during parsing.
 * 
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBNodal
 */
@Deprecated
public class SQLParentInsert implements SQLFormat<DBNodal<?>>
{
	public static final int BUG_FIX = 42;
	
	
	private DBSchema<?> scm;
	
	/**
	 * Creates a new {@code SQLParentInsert}.
	 * 
	 * @param s  a database schema
	 * 
	 * 
	 * @see DBSchema
	 */
	public SQLParentInsert(DBSchema<?> s)
	{
		scm = s;
	}

	
	@Override
	public String parse(DBNodal<?> pnt)
	{	
		DBMap map = scm.Map(pnt);
		DBTable<?> tbl = scm.Master();
		Format fmt = map.Formatter();
		
		String sql = "";
		sql += "INSERT";
		sql += " " + tbl.Name() + " ";
		sql += "(" + fmt.Keys().condense() + ") ";
		sql += "VALUES";
		
		DBNode node = pnt.Arch(scm);
		for(int k = 0; k < node.ChildCount(); k++)
		{
			DBAccess<?> acs = node.Child(k);
			if(k > 0)
			{
				sql += ", ";
			}
			
			map = scm.Map(acs);
			fmt = map.Formatter();
			
			sql += "(" + fmt.Values().condense() + ")";
		}

		return sql;
	}
}