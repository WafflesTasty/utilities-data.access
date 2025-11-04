package waffles.utils.dacs.db.schema.format;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.entities.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
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
	private DBEntity<?> ent;
	
	/**
	 * Creates a new {@code SQLDelete}.
	 * 
	 * @param e  an entity
	 * 
	 * 
	 * @see DBEntity
	 */
	public SQLDelete(DBEntity<?> e)
	{
		ent = e;
	}

	
	@Override
	public String parse(DBSchema<?> scm)
	{	
		String sql = "";
		sql += "DELETE FROM";
		sql += " " + scm.Table() + " ";
		sql += "WHERE " + Database.ID + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
}