package waffles.utils.dacs.db.schema;

import waffles.utils.dacs.db.entities.DBEntity;
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
	/**
	 * Defines a default id column.
	 */
	public static String DEFAULT_ID = "id";
	
	
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
 	
 	/**
 	 * Returns the {@code DBSchema} id.
 	 * 
 	 * @return  an id column
 	 */
	public String ID()
	{
		return DEFAULT_ID;
	}
 	
 	
 	/**
 	 * Returns the {@code DBSchema} pairs.
 	 * This method excludes the id column,
 	 * for compatibility with updates.
 	 * 
 	 * @param e  a source entity
 	 * @return   a value string
 	 * 
 	 * 
 	 * @see DBEntity
 	 */
	public String Pairs(DBEntity<?> e)
	{
		return Getter().Pairs((E) e).condense();
	}
 	
 	/**
 	 * Returns the {@code DBSchema} values.
 	 * 
 	 * @param e  a source entity
 	 * @return   a value string
 	 * 
 	 * 
 	 * @see DBEntity
 	 */
 	public String Values(DBEntity<?> e)
 	{
 		return Getter().Values((E) e).condense();
 	}


	@Override
	public Format<DBSchema<?>> Formatter()
	{
		return s -> s.Table();
	}
}