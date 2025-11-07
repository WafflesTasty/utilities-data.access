package waffles.utils.dacs.utilities.db.sql;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.lang.tokens.format.Format;

/**
 * An {@code SQLFormat} formats a {@code DBAccess} into an SQL string.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <A>  an access type
 */
public interface SQLFormat<A extends DBAccess<?>> extends Format<A>
{
	// NOT APPLICABLE
}