package waffles.utils.dacs.utilities.db.tokens.maps;

import waffles.utils.dacs.utilities.db.tokens.DBList;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.lang.tokens.MapToken;
import waffles.utils.lang.tokens.format.ListFormat;
import waffles.utils.sets.countable.keymaps.KeyMap;

/**
 * A {@code DBMap} defines a {@code KeyMap} with SQL formatting.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 * 
 * @see DBLiteral
 * @see MapToken
 * @see DBToken
 * @see DBPair
 * @see KeyMap
 */
public interface DBMap extends KeyMap<DBLiteral, DBToken>, MapToken<DBPair>
{
	/**
	 * The {@code Format} class defines formatting for a {@code DBMap}.
	 *
	 * @author Waffles
	 * @since 05 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @see ListFormat
	 * @see DBToken
	 */
	public static class Format extends ListFormat<DBToken>
	{	
		private DBMap map;
		
		/**
		 * Creates a new {@code Format}.
		 * 
		 * @param m  a target map
		 * 
		 * 
		 * @see DBMap
		 */
		public Format(DBMap m)
		{
			super(',');
			map = m;
		}
			
		
		/**
		 * Returns a formattable value list of a {@code DBMap}.
		 * 
		 * @return  a value list
		 * 
		 * 
		 * @see DBToken
		 * @see DBList
		 */
		public DBList<DBToken> Values()
		{
			return () -> map.Values();
		}
		
		/**
		 * Returns a formattable key list of a {@code DBMap}.
		 * 
		 * @return  a key list
		 * 
		 * 
		 * @see DBLiteral
		 * @see DBList
		 */
		public DBList<DBLiteral> Keys()
		{
			return () -> map.Keys();
		}
	}

	
	/**
	 * Returns a value from the {@code DBMap}.
	 * 
	 * @param key  a map key
	 * @return   a map value
	 */
	public default DBToken get(String key)
	{
		return get(new DBLiteral(key));
	}
	
	/**
	 * Puts a key-value pair into the {@code DBMap}.
	 * 
	 * @param key  a map key
	 * @param val  a map value
	 * @return  an old value
	 */
	public default DBToken put(String key, DBToken val)
	{
		return put(new DBLiteral(key), val);
	}
	
	/**
	 * Puts a key-value pair into the {@code DBMap}.
	 * 
	 * @param key  a map key
	 * @param val  a map value
	 * @return  an old value
	 */
	public default DBToken put(String key, String val)
	{
		return put(key, new DBToken(val));
	}

	
	@Override
	public abstract Iterable<DBPair> Pairs();
	
	@Override
	public default Iterable<DBPair> Tokens()
	{
		return Pairs();
	}
	
	@Override
	public default Format Formatter()
	{
		return new Format(this);
	}
}