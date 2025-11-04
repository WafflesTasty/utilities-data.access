package waffles.utils.dacs.db.schema.format;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.db.entities.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLUpdate} formats an SQL string for a {@code DBEntity} update.
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
public class SQLUpdate<E extends DBEntity<?>> implements Format<DBSchema<E>>
{
	private E ent;
	
	/**
	 * Creates a new {@code SQLUpdate}.
	 * 
	 * @param e  an entity
	 */
	public SQLUpdate(E e)
	{
		ent = e;
	}
	
	
	@Override
	public String parse(DBSchema<E> scm)
	{
		String pairs = scm.Pairs(ent);
		
		String sql = "";
		sql += "UPDATE " + scm.Table() + " ";
		sql += "SET " + pairs + " ";
		sql += "WHERE " + Database.ID + " = ";
		sql += "'" + ent.GUID() + "'";
		return sql;
	}
}