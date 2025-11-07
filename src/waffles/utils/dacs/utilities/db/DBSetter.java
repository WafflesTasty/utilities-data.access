package waffles.utils.dacs.utilities.db;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.sets.countable.keymaps.wrapper.JavaMap;

/**
 * A {@code DBSetter} changes a value in a {@code DBAccess}.
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
public interface DBSetter<A extends DBAccess<?>>
{
	/**
	 * A {@code DBSetter.Map} maps column keys to database setters.
	 *
	 * @author Waffles
	 * @since 07 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @param <A>  an access type
	 * @see DBLiteral
	 * @see DBAccess
	 * @see DBSetter
	 * @see JavaMap
	 */
	public static class Map<A extends DBAccess<?>> extends JavaMap<DBLiteral, DBSetter<? super A>>
	{
		/**
		 * Adds a getter to the {@code DBGetter.Map}.
		 * 
		 * @param key  a database key
		 * @param val  a database getter
		 * 
		 * 
		 * @see DBSetter
		 */
		public void add(String key, DBSetter<? super A> val)
		{
			put(new DBLiteral(key), val);
		}
	}
	
	
	/**
	 * Changes a value with the {@code DBSetter}.
	 * 
	 * @param acs  a database access
	 * @param val  a database value
	 * @return  an old value
	 */
	public abstract Object set(A acs, Object val);
}