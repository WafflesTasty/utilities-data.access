package waffles.utils.dacs.db.access.entity;

import waffles.utils.dacs.db.DBRow;
import waffles.utils.dacs.db.access.DBFormat;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.format.SQLEntityInsert;
import waffles.utils.dacs.db.access.entity.format.SQLEntitySelect;
import waffles.utils.dacs.db.access.entity.format.SQLEntityUpdate;
import waffles.utils.dacs.db.access.format.SQLAccessDelete;
import waffles.utils.dacs.db.access.format.SQLAccessExists;
import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBLoader;
import waffles.utils.dacs.utilities.db.DBSetter;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * A {@code DBTable} manages a one-to-one relationship between database rows and entities.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <E>  an entity type
 * @see DBEntity
 * @see DBSchema
 */
public class DBTable<E extends DBEntity<?>> implements DBSchema<E>
{
	/**
	 * Defines a default GUID column for a {@code DBTable}.
	 */
	public static String DEFAULT_GUID = "id";
	
	/**
	 * A {@code Format} generates SQL for a {@code DBTable}.
	 *
	 * @author Waffles
	 * @since 07 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see DBFormat
	 */
	@FunctionalInterface
	public static interface Format extends DBFormat
	{
		/**
		 * Returns a table for the {@code Format}.
		 * 
		 * @return  a database table
		 * 
		 * 
		 * @see DBTable
		 */
		public abstract DBTable<?> Table();
		
		/**
		 * Creates a {@code DBMap} for a {@code DBEntity}.
		 * 
		 * @param ent  a database entity
		 * @return  a database map
		 * 
		 * 
		 * @see DBEntity
		 * @see DBMap
		 */
		public default DBMap Map(DBEntity<?> ent)
		{
			return new DBEntityMap<>(Table(), ent);
		}


		@Override
		public default SQLFormat<?> create(SQLOps ops)
		{
			switch(ops)
			{
			case DELETE:
				return new SQLAccessDelete(Table());
			case EXISTS:
				return new SQLAccessExists(Table());
			case INSERT:
				return new SQLEntityInsert(Table());				
			case SELECT:
				return new SQLEntitySelect(Table());
			case UPDATE:
				return new SQLEntityUpdate(Table());
			default:
				return null;
			}
		}
	}


	private String name;
	
	private DBGetter.Map<E> getter;
	private DBSetter.Map<E> setter;
	
	/**
	 * Creates a new {@code DBTable}.
	 * 
	 * @param n  a table name
	 */
	public DBTable(String n)
	{
		name = n;
		
 		getter = new DBGetter.Map<>();
 		setter = new DBSetter.Map<>();
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
	public void add(String key, DBGetter<E> val)
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
	public void add(String key, DBSetter<E> val)
	{
		setter.put(new DBLiteral(key), val);
	}
	
	
	/**
	 * Returns a getter in the {@code DBTable}.
	 * 
	 * @param key  a database key
	 * @return  a database getter
	 * 
	 * 
	 * @see DBLiteral
	 * @see DBGetter
	 */
	public DBGetter<? super E> Getter(DBLiteral key)
	{
		return getter.get(key);
	}
	
	/**
	 * Returns a setter in the {@code DBTable}.
	 * 
	 * @param key  a database key
	 * @return  a database setter
	 * 
	 * 
	 * @see DBLiteral
	 * @see DBSetter
	 */
	public DBSetter<? super E> Setter(DBLiteral key)
	{
		return setter.get(key);
	}
	
	
	/**
	 * Returns the getter of the {@code DBTable}.
	 * 
	 * @return  a getter map
	 * 
	 * 
	 * @see DBGetter
	 */
	public DBGetter.Map<E> Getter()
	{
		return getter;
	}
	
	/**
	 * Returns the setter of the {@code DBTable}.
	 * 
	 * @return  a setter map
	 * 
	 * 
	 * @see DBSetter
	 */
	public DBSetter.Map<E> Setter()
	{
		return setter;
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
	 * Returns the {@code DBTable} guid.
	 * 
	 * @return  a guid column
	 */
	public String GUID()
	{
		return DEFAULT_GUID;
	}

	
	@Override
	public DBLoader<E> Loader()
	{
		return (ent, row) ->
		{
			DBMap map = Formatter().Map(ent);
			while(row.hasNext())
			{
				DBRow r = row.next();
				for(DBLiteral tkn : map.Keys())
				{
					Object o = r.get(tkn.condense());
					map.put(tkn, new DBToken(o));
				}
				
				return true;				
			}
			
			return false;
		};
	}

	@Override
	public DBTable<?> Master()
	{
		return this;
	}

	@Override
	public Format Formatter()
	{
		return () -> this;
	}
}