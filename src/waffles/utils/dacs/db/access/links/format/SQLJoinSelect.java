package waffles.utils.dacs.db.access.links.format;

import waffles.utils.dacs.db.access.entity.DBEntity;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.links.DBJoin;
import waffles.utils.dacs.db.access.links.DBLink;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.tokens.DBToken;

/**
 * An {@code SQLJoinSelect} selects a {@code DBLink} from a {@code DBJoin}.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see SQLFormat
 * @see DBLink
 */
public class SQLJoinSelect implements SQLFormat<DBLink<?>>
{
	private DBJoin<?> join;
	
	/**
	 * Creates a new {@code SQLJoinSelect}.
	 * 
	 * @param j  a database join
	 * 
	 * 
	 * @see DBJoin
	 */
	public SQLJoinSelect(DBJoin<?> j)
	{
		join = j;
	}

	
	@Override
	public String parse(DBLink<?> lnk)
	{	
		DBEntity<?> ent = lnk.Parent();
		DBTable<?> mst = join.Master();
		
		String keys =  Keys(mst);
		String from = mst.Name();
		
		for(DBTable<?> tbl : join.Slaves())
		{
			from += " INNER JOIN ";
			from += tbl.Name();
			from += " ON ";
			from += mst.Name() + "." + mst.GUID();
			from += " = ";
			from += "'" + ent.GUID() + "'";
			
			if(!keys.isEmpty())
				keys += ", ";
			keys += Keys(tbl);
		}
		
		String sql = "";
		sql += "SELECT";
		sql += " " + keys + " ";
		sql += "FROM";
		sql += " " + from + " ";
		sql += "WHERE";
		sql += " " + mst.GUID() + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
	
	String Keys(DBTable<?> tbl)
	{
		String s = "";
		for(DBToken tkn : tbl.Getter().Keys())
		{
			if(!s.isEmpty())
				s += ", ";
			s += tbl.Name();
			s += ".";
			s += tkn.condense();
		}
		
		return s;
	}
}