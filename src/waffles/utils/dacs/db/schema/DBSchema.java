package waffles.utils.dacs.db.schema;

import waffles.utils.dacs.db.handlers.DBHandleable;
<<<<<<< HEAD
import waffles.utils.dacs.utilities.Mastery;
import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBSetter;
import waffles.utils.dacs.utilities.db.format.DBLiteral;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
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
public abstract class DBSchema<H extends DBHandleable<?>> implements Countable, Token
{	
	/**
	 * Defines a default id column.
	 */
	public static String DEFAULT_ID = "id";

	/**
	 * A {@code Formatter} defines formatting for a {@code DBSchema}.
	 * A {@code Formatter} spawns a separate {@code SQLFormat}
	 * instance for every type of {@code SQLOps}.
	 *
	 * @author Waffles
	 * @since 06 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see DBSchema
	 * @see Mastery
	 * @see Format
	 */
	public interface Formatter extends Format<DBSchema<?>>, Mastery
	{
		/**
		 * Generates an {@code SQLFormat} type.
		 * 
		 * @param scm  a database schema
		 * @param ops  an operation type  
		 * @return  an sql format
		 * 
		 * 
		 * @see SQLFormat
		 * @see SQLOps
		 */
		public default SQLFormat get(DBSchema<?> scm, SQLOps ops)
		{
			return () -> scm;
		}

		@Override
		public default String parse(DBSchema<?> scm)
		{
			return scm.Formatter().Master();
		}
	}
	
	
	private JavaMap<DBLiteral, DBGetter<H>> getter;
	private JavaMap<DBLiteral, DBSetter<H>> setter;

	/**
	 * Creates a new {@code DBSchema}.
	 */
 	public DBSchema()
	{
 		getter = new JavaMap<>();
 		setter = new JavaMap<>();
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
		getter.put(new DBLiteral(key), val);
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
		setter.put(new DBLiteral(key), val);
	}
 	
	
	/**
	 * Returns a getter from the {@code DBSchema}.
	 * 
	 * @param key  a database key
	 * @return  a database getter
	 * 
	 * 
	 * @see DBLiteral
	 * @see DBGetter
	 */
	public DBGetter<H> Getter(DBLiteral key)
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
	 * @see DBLiteral
	 * @see DBSetter
	 */
	public DBSetter<H> Setter(DBLiteral key)
	{
		return setter.get(key);
	}
	
	
	/**
	 * Iterates the getters of the {@code DBSchema}.
	 * 
	 * @return  a getter iterable
	 * 
	 * 
	 * @see DBLiteral
	 * @see Iterable
	 */
	public Iterable<DBLiteral> Getters()
	{
		return getter.Keys();
	}
	
	/**
	 * Iterates the setters of the {@code DBSchema}.
	 * 
	 * @return  a setter iterable
	 * 
	 * 
	 * @see DBLiteral
	 * @see Iterable
	 */
	public Iterable<DBLiteral> Setters()
	{
		return setter.Keys();
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
	
	
	/**
	 * Creates an {@code SQLFormat}.
	 * 
	 * @param ops  an operation type
	 * @return  an operation format
	 * 
	 * 
	 * @see SQLFormat
	 * @see SQLOps
	 */
	public SQLFormat Formatter(SQLOps ops)
	{
		return Formatter().get(this, ops);
	}
	
	@Override
	public abstract Formatter Formatter();
=======
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
	
		
 	@Override
	public Format<DBSchema<?>> Formatter()
	{
 		return s -> s.Table();
	}
>>>>>>> branch 'master' of https://github.com/WafflesTasty/utilities-data.access

	@Override
	public int Count()
	{
		return getter.Count();
	}
}