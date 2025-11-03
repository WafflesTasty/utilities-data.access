package waffles.utils.dacs.db.schema;

import waffles.utils.dacs.db.DBEntity;
import waffles.utils.dacs.db.schema.maps.DBGetter;
import waffles.utils.dacs.db.schema.maps.DBSetter;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;

/**
 * A {@code DBSchema} maps database table columns to {@code DBEntity} values.
 * A {@code DBSchema} is used to facilitate delete, select and update statements.
 * 
 * @author Waffles
 * @since 02 Nov 2025
 * @version 1.1
 * 
 * 
 * @param <E>  an entity type
 * @see DBEntity
 * @see Token
 */
public class DBSchema<E extends DBEntity<?>> implements Token
{
	private String table;
	private DBGetter<E> getter;
	private DBSetter<E> setter;
	
	/**
	 * Creates a new {@code DBSchema}.
	 * 
	 * @param tbl  a database table
	 */
 	public DBSchema(String tbl)
	{
 		table = tbl;
 		getter = new DBGetter<>();
 		setter = new DBSetter<>();
	}

	/**
	 * Returns the {@code DBSchema} getter.
	 * 
	 * @return  a database getter
	 * 
	 * 
	 * @see DBGetter
	 */
 	public DBGetter<E> Getter()
 	{
 		return getter;
 	}
 	
	/**
	 * Returns the {@code DBSchema} setter.
	 * 
	 * @return  a database setter
	 * 
	 * 
	 * @see DBSetter
	 */
 	public DBSetter<E> Setter()
 	{
 		return setter;
 	}

 	/**
 	 * Returns the {@code DBSchema} values.
 	 * 
 	 * @param e  a source entity
 	 * @return   a value string
 	 */
 	public String Values(E e)
 	{
 		return Getter().Values(e).condense();
 	}
 	 	
	/**
	 * Returns the {@code DBSchema} table.
	 * 
	 * @return  a table name
	 */
	public String Table()
	{
		return table;
	}
 	
	/**
	 * Returns the {@code DBSchema} keys.
	 * 
	 * @return  a key string
	 */
 	public String Keys()
 	{
 		return Getter().condense();
 	}


	@Override
	public Format<DBSchema<?>> Formatter()
	{
		return s -> s.Table();
	}
}