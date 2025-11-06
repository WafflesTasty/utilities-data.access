package waffles.utils.dacs.db.operations;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
<<<<<<< HEAD
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLDelete} formats an SQL string for a {@code DBEntity} delete.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see DBSchema
 * @see Format
 */
public class SQLDelete implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLDelete}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLDelete(DBHandleable<?> h)
	{
		hnd = h;
	}

	
	@Override
	public String parse(DBSchema<?> scm)
	{	
		SQLFormat fmt = scm.Formatter(SQLOps.DELETE);
		
		
		String sql = "";
		sql += "DELETE FROM" + " ";
		sql += fmt.Table(hnd) + " ";
		sql += "WHERE" + " ";
		sql += fmt.Check(hnd);
=======
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLDelete} formats an SQL string for a {@code DBEntity} delete.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @see DBSchema
 * @see Format
 */
public class SQLDelete implements Format<DBSchema<?>>
{
	private DBHandleable<?> hnd;
	
	/**
	 * Creates a new {@code SQLDelete}.
	 * 
	 * @param h  a handler
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLDelete(DBHandleable<?> h)
	{
		hnd = h;
	}

	
	@Override
	public String parse(DBSchema<?> scm)
	{	
		String sql = "";
		sql += "DELETE FROM";
		sql += " " + scm.Table() + " ";
		sql += "WHERE " + scm.ID() + " = ";
		sql += "'" + hnd.GUID() + "'";
>>>>>>> branch 'master' of https://github.com/WafflesTasty/utilities-data.access
		return sql;
	}
}