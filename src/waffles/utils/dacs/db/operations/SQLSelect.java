package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.DBHandler;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLSelect} formats an SQL string for a {@code DBEntity} select.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see DBSchema
 * @see Format
 */
public class SQLSelect implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLSelect}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLSelect(DBHandleable<?> h)
	{
		hnd = h;
	}
	
	
	@Override
	public String parse(DBSchema<?> scm)
	{
		DBHandler h = hnd.Handler();
		DBMap map = h.map(hnd, scm);
		
		DBMap.Format fmt = map.Formatter();
		String keys = fmt.Keys().condense();
		
		
		String sql = "";
		sql += "SELECT " + keys + " ";
		sql += "FROM " + scm.Table() + " ";
		sql += "WHERE " + scm.ID() + " = ";
		sql += "'" + hnd.GUID() + "'";
		return sql;
	}
}