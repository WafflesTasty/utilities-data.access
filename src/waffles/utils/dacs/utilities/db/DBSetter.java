package waffles.utils.dacs.utilities.db;

import waffles.utils.dacs.db.handlers.DBHandleable;

/**
 * A {@code DBSetter} changes a single value in a {@code DBHandleable}.
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
public interface DBSetter<H extends DBHandleable<?>>
{
	/**
	 * Changes a value with the {@code DBSetter}.
	 * 
	 * @param hnd  a database handler
	 * @param val  a database value
	 * @return  an old value
	 */
	public abstract Object set(H hnd, Object val);
}