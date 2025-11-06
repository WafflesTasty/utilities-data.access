package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
<<<<<<< HEAD
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLInsert} formats an SQL string for a {@code DBEntity} insert.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 * 
 * @see DBSchema
 * @see Format
 */
public class SQLInsert implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLInsert}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLInsert(DBHandleable<?> h)
	{
		hnd = h;
	}
	
	
	@Override
	public String parse(DBSchema<?> scm)
	{
		SQLFormat fmt = scm.Formatter(SQLOps.INSERT);
		
		
		String sql = "";
		sql += "INSERT" + " ";
		sql += fmt.Table(hnd) + " ";		
		sql += "(" + fmt.Keys(hnd) + ")" + " ";
		sql += "VALUES" + " ";
		sql += "(" + fmt.Values(hnd) + ")" + " ";
=======
import waffles.utils.dacs.db.handlers.DBHandler;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLInsert} formats an SQL string for a {@code DBEntity} insert.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 * 
 * @see DBSchema
 * @see Format
 */
public class SQLInsert implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLInsert}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLInsert(DBHandleable<?> h)
	{
		hnd = h;
	}
	
	
	@Override
	public String parse(DBSchema<?> scm)
	{
		DBHandler h = hnd.Handler();
		DBMap map = h.map(hnd, scm);
		
		DBMap.Format f = map.Formatter();
		String keys =   f.Keys().condense();
		String vals = f.Values().condense();
		

		String sql = "";
		sql += "INSERT " + scm.Table();
		sql += " (" + keys + ") ";
		sql += "VALUES (" + vals + ")";
>>>>>>> branch 'master' of https://github.com/WafflesTasty/utilities-data.access
		return sql;
	}
}