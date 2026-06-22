package waffles.utils.dacs.db.access;

import waffles.utils.lang.tokens.format.Format;

/**
 * A {@code DBFormat} parses {@code DBAccess} objects into strings.
 *
 * @author Waffles
 * @since Apr 7, 2026
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @see DBAccess
 * @see Format
 */
public interface DBFormat<A extends DBAccess<?>> extends Format<A>
{
	// NOT APPLICABLE
}