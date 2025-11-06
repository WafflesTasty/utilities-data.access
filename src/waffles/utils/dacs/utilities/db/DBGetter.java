package waffles.utils.dacs.utilities.db;

import waffles.utils.dacs.db.handlers.DBHandleable;

/**
 * A {@code DBGetter} retrieves a single value from a {@code DBHandleable}.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 *
 * @param <H>  a handler type
 * @see DBHandleable
 */
@FunctionalInterface
public interface DBGetter<H extends DBHandleable<?>>
{
	/**
	 * Returns a value with the {@code DBGetter}.
	 * 
	 * @param hnd  a database handler
	 * @return  a handler value
	 */
	public abstract Object get(H hnd);
}