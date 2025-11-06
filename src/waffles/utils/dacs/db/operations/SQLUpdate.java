package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
<<<<<<< HEAD
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLUpdate} formats an SQL string for a {@code DBEntity} update.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see DBEntity
 * @see DBSchema
 * @see Format
 */
public class SQLUpdate implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLUpdate}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLUpdate(DBHandleable<?> h)
	{
		hnd = h;
	}
	
	
	@Override
	public String parse(DBSchema<?> scm)
	{
		SQLFormat fmt = scm.Formatter(SQLOps.UPDATE);
		
		
		String sql = "";
		sql += "UPDATE" + " ";
		sql += fmt.Table(hnd) + " ";
		sql += "SET" + " ";
		sql += fmt.Pairs(hnd) + " ";
		sql += "WHERE" + " ";
		sql += fmt.Check(hnd);
=======
import waffles.utils.dacs.db.handlers.DBHandler;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLUpdate} formats an SQL string for a {@code DBEntity} update.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see DBEntity
 * @see DBSchema
 * @see Format
 */
public class SQLUpdate implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLUpdate}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLUpdate(DBHandleable<?> h)
	{
		hnd = h;
	}
	
	
	@Override
	public String parse(DBSchema<?> scm)
	{
		DBHandler h = hnd.Handler();
		DBMap map = h.map(hnd, scm);
		
		DBMap.Format f = map.Formatter();
		String pairs = map.condense();
		
		String sql = "";
		sql += "UPDATE " + scm.Table() + " ";
		sql += "SET " + pairs + " ";
		sql += "WHERE " + scm.ID() + " = ";
		sql += "'" + hnd.GUID() + "'";
>>>>>>> branch 'master' of https://github.com/WafflesTasty/utilities-data.access
		return sql;
	}
}