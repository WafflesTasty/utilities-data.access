package waffles.utils.dacs.db.schema.format;

import waffles.utils.dacs.db.entities.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
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
	private DBEntity<?> ent;
	
	/**
	 * Creates a new {@code SQLSelect}.
	 * 
	 * @param e  an entity
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLSelect(DBEntity<?> e)
	{
		ent = e;
	}
	
	
	@Override
	public String parse(DBSchema<?> scm)
	{
		String keys = scm.Keys();
		
		String sql = "";
		sql += "SELECT DISTINCT " + keys + " ";
		sql += "FROM " + scm.Table() + " ";
		sql += "WHERE " + scm.ID() + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
}