package waffles.utils.dacs.db.schema;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBSetter;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.sets.countable.keymaps.wrapper.JavaMap;
import waffles.utils.tools.patterns.properties.counters.Countable;

/**
 * A {@code DBSchema} maps database table columns to {@code DBHandleable} values.
 * A {@code DBSchema} is used to facilitate delete, select and update statements.
 * 
 * @author Waffles
 * @since 02 Nov 2025
 * @version 1.1
 * 
 * 
 * @param <H>  a handler type
 * @see DBHandleable
 * @see Countable
 * @see Token
 */
public class DBSchema<H extends DBHandleable<?>> implements Countable, Token
{	
	/**
	 * Defines a default id column.
	 */
	public static String DEFAULT_ID = "id";
		
	
	private String table;
	private JavaMap<DBToken, DBGetter<H>> getter;
	private JavaMap<DBToken, DBSetter<H>> setter;

	/**
	 * Creates a new {@code DBSchema}.
	 * 
	 * @param t  a database table
	 */
 	public DBSchema(String t)
	{
 		table = t;
 		
 		getter = new JavaMap<>();
 		setter = new JavaMap<>();
 		
 		add(ID(), getGUID());
	}
  	
 	
	/**
	 * Adds a getter to the {@code DBSchema}.
	 * 
	 * @param key  a database key
	 * @param val  a database getter
	 * 
	 * 
	 * @see DBGetter
	 */
	public void add(String key, DBGetter<H> val)
	{
		getter.put(new DBToken(key), val);
	}
	
	/**
	 * Adds a setter to the {@code DBSchema}.
	 * 
	 * @param key  a database key
	 * @param val  a database setter
	 * 
	 * 
	 * @see DBSetter
	 */
	public void add(String key, DBSetter<H> val)
	{
		setter.put(new DBToken(key), val);
	}
 	
	
	/**
	 * Returns a getter from the {@code DBSchema}.
	 * 
	 * @param key  a database key
	 * @return  a database getter
	 * 
	 * 
	 * @see DBGetter
	 * @see DBToken
	 */
	public DBGetter<H> Getter(DBToken key)
	{
		return getter.get(key);
	}
	
	/**
	 * Returns a setter from the {@code DBSchema}.
	 * 
	 * @param key  a database key
	 * @return  a database setter
	 * 
	 * 
	 * @see DBSetter
	 * @see DBToken
	 */
	public DBSetter<H> Setter(DBToken key)
	{
		return setter.get(key);
	}
	
	/**
	 * Iterates the keys of the {@code DBSchema}.
	 * 
	 * @return  a key iterable
	 * 
	 * 
	 * @see Iterable
	 * @see DBToken
	 */
	public Iterable<DBToken> Keys()
	{
		return getter.Keys();
	}
	
	
	/**
	 * Returns the table of the {@code DBSchema}.
	 * 
	 * @return  a table name
	 */
	public String Table()
	{
		return table;
	}
 		 	
 	/**
 	 * Returns the id of the {@code DBSchema}.
 	 * 
 	 * @return  an id column
 	 */
	public String ID()
	{
		return DEFAULT_ID;
	}
	
		
	private DBGetter<H> getGUID()
	{
		return h -> h.GUID();
	}

 	@Override
	public Format<DBSchema<?>> Formatter()
	{
 		return s -> s.Table();
	}

	@Override
	public int Count()
	{
		return getter.Count();
	}
}