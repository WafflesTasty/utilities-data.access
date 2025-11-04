package waffles.utils.dacs.utilities.database;

import waffles.utils.lang.tokens.PairToken;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.values.primitive.LiteralToken;

/**
 * A {@code DBPair} defines a key-value pair for SQL databases.
 *
 * @author Waffles
 * @since 04 Nov 2025
 * @version 1.1
 *
 * 
 * @see LiteralToken
 * @see PairToken
 * @see Token
 */
public class DBPair implements PairToken<LiteralToken, Token>
{
	private Token val;
	private LiteralToken key;
	
	/**
	 * Creates a new {@code DBPair}.
	 * 
	 * @param k  a key token
	 * @param v  a value token
	 * 
	 * 
	 * @see LiteralToken
	 * @see Token
	 */
	public DBPair(LiteralToken k, Token v)
	{
		key = k;
		val = v;
	}
	
	
	@Override
	public Format<?> Formatter()
	{
		return Formatter('=');
	}
	
	@Override
	public LiteralToken Key()
	{
		return key;
	}
	
	@Override
	public Token Value()
	{
		return val;
	}
}