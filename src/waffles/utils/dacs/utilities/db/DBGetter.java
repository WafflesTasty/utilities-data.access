package waffles.utils.dacs.utilities.db;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.sets.countable.keymaps.wrapper.JavaMap;

/**
 * A {@code DBGetter} retrieves a value from a {@code DBAccess}.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @see DBAccess
 */
@FunctionalInterface
public interface DBGetter<A extends DBAccess<?>>
{
	/**
	 * A {@code DBGetter.Map} maps column keys to database getters.
	 *
	 * @author Waffles
	 * @since 07 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <A>  an access type
	 * @see DBLiteral
	 * @see DBAccess
	 * @see DBGetter
	 * @see JavaMap
	 */
	public static class Map<A extends DBAccess<?>> extends JavaMap<DBLiteral, DBGetter<? super A>>
	{
		/**
		 * Adds a getter to the {@code DBGetter.Map}.
		 * 
		 * @param key  a database key
		 * @param val  a database getter
		 * 
		 * 
		 * @see DBGetter
		 */
		public void add(String key, DBGetter<? super A> val)
		{
			put(new DBLiteral(key), val);
		}
	}
	
	
	/**
	 * Returns a value with the {@code DBGetter}.
	 * 
	 * @param acs  a database access
	 * @return  a database value
	 */
	public abstract Object get(A acs);
}