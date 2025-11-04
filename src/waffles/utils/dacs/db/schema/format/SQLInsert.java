package waffles.utils.dacs.db.schema.format;

import waffles.utils.dacs.db.entities.DBEntity;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLInsert} formats an SQL string for a {@code DBEntity} insert.
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
public class SQLInsert<E extends DBEntity<?>> implements Format<DBSchema<E>>
{
	private E ent;
	
	/**
	 * Creates a new {@code SQLInsert}.
	 * 
	 * @param e  an entity
	 */
	public SQLInsert(E e)
	{
		ent = e;
	}
	
	
	@Override
	public String parse(DBSchema<E> scm)
	{
		String vals = scm.Values(ent);
		String keys = scm.Keys();
		
		String sql = "";
		sql += "INSERT " + scm.Table();
		sql += " (" + keys + ") ";
		sql += "VALUES (" + vals + ")";
		return sql;
	}
}