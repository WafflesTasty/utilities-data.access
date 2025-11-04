package waffles.utils.dacs.db.schema.format;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.entities.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLExists} formats an SQL string for a {@code DBEntity} existence check.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @param <E>  an entity type
 * @see DBEntity
 * @see DBSchema
 * @see Format
 */
public class SQLExists<E extends DBEntity<?>> implements Format<DBSchema<E>>
{
	private E ent;
	
	/**
	 * Creates a new {@code SQLExists}.
	 * 
	 * @param e  an entity
	 */
	public SQLExists(E e)
	{
		ent = e;
	}

	
	@Override
	public String parse(DBSchema<E> scm)
	{	
		String sql = "";
		sql += "SELECT id FROM";
		sql += " " + scm.Table() + " ";
		sql += "WHERE " + Database.ID + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
}