package waffles.utils.dacs.db.access.entity;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBSetter;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code DBTable} manages a one-to-one relationship between database rows and entities.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @see DBAccess
 * @see DBSchema
 */
public class DBTable<A extends DBAccess<?>> implements DBSchema<A>
{
	/**
	 * Defines a default GUID column for a {@code DBTable}.
	 */
	public static String DEFAULT_GUID = "id";
	

	private String name;
	private String[] keys;
	
	private DBGetter.Map<A> getter;
	private DBSetter.Map<A> setter;
	
	/**
	 * Creates a new {@code DBTable}.
	 * 
	 * @param n  a table name
	 */
	public DBTable(String n)
	{
		name = n;
		keys = new String[0];
		
 		getter = new DBGetter.Map<>();
 		setter = new DBSetter.Map<>();
	}
	
	
	/**
	 * Returns the {@code DBTable} guid.
	 * 
	 * @return  a guid column
	 */
	public String GUID()
	{
		return DEFAULT_GUID;
	}
	
	/**
	 * Returns the {@code DBTable} keys.
	 * 
	 * @return  a primary key
	 */
	public String[] Keys()
	{
		return keys;
	}
		
	/**
	 * Returns the {@code DBTable} name.
	 * 
	 * @return  a table name
	 */
	public String Name()
	{
		return name;
	}

	
	/**
	 * Adds a key to the {@code DBTable}.
	 * This is treated as a primary key in
	 * combination with the GUID.
	 * 
	 * @param key  a primary key
	 */
	public void addKey(String key)
	{
		keys = Array.add.to(keys, key);
	}
		
	/**
	 * Adds a getter to the {@code DBTable}.
	 * 
	 * @param key  a database key
	 * @param val  a database getter
	 * 
	 * 
	 * @see DBGetter
	 */
	public void add(String key, DBGetter<? super A> val)
	{
		getter.put(new DBLiteral(key), val);
	}
	
	/**
	 * Adds a setter to the {@code DBTable}.
	 * 
	 * @param key  a database key
	 * @param val  a database setter
	 * 
	 * 
	 * @see DBSetter
	 */
	public void add(String key, DBSetter<? super A> val)
	{
		setter.put(new DBLiteral(key), val);
	}	
	
		
	@Override
	public DBGetter.Map<A> Getter()
	{
		return getter;
	}

	@Override
	public DBSetter.Map<A> Setter()
	{
		return setter;
	}
	
	@Override
	public DBTable<?> Master()
	{
		return this;
	}
}