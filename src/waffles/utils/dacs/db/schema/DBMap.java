package waffles.utils.dacs.db.schema;

import waffles.utils.lang.tokens.ListToken;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.values.primitive.LiteralToken;
import waffles.utils.sets.countable.keymaps.wrapper.JavaMap;

/**
 * A {@code DBMap} is a map using {@code LiteralToken} objects as keys.
 * It implements {@code ListToken} allowing its keys to be parsed into a string.
 *
 * @author Waffles
 * @since 02 Nov 2025
 * @version 1.1
 *
 *
 * @param <V>  a value type
 * @see LiteralToken
 * @see ListToken
 * @see JavaMap
 */
public class DBMap<V> extends JavaMap<LiteralToken, V> implements ListToken<LiteralToken>
{	
	/**
	 * Returns a value from the {@code DBMap}.
	 * 
	 * @param key  a map key
	 * @return   a map value
	 */
	public V get(String key)
	{
		return get(new LiteralToken(key));
	}
	
	/**
	 * Puts a key-value pair into the {@code DBMap}.
	 * 
	 * @param key  a map key
	 * @param val  a map value
	 * @return  an old value
	 */
	public V put(String key, V val)
	{
		return put(new LiteralToken(key), val);
	}
	
	
	@Override
	public Iterable<LiteralToken> Tokens()
	{
		return Keys();
	}
	
	@Override
	public Format<?> Formatter()
	{
		return Formatter(',');
	}
}