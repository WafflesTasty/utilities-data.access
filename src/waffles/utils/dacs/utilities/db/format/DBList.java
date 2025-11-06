package waffles.utils.dacs.utilities.db.format;

import waffles.utils.lang.tokens.ListToken;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;

/**
 * A {@code DBList} defines a {@code ListToken} with SQL formatting.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 *
 * @param <T>  a token type
 * @see ListToken
 * @see Token
 */
@FunctionalInterface
public interface DBList<T extends Token> extends ListToken<T>
{
	@Override
	public default Format<?> Formatter()
	{
		return Formatter(',');
	}
}