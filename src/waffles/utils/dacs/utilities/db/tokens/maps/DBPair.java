package waffles.utils.dacs.utilities.db.tokens.maps;

import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.lang.tokens.PairToken;
import waffles.utils.lang.tokens.format.Format;

/**
 * A {@code DBPair} defines a key-value pair with SQL formatting.
 *
 * @author Waffles
 * @since 04 Nov 2025
 * @version 1.1
 *
 * 
 * @see PairToken
 * @see DBLiteral
 * @see DBToken
 */
public class DBPair implements PairToken<DBLiteral, DBToken>
{
	private DBToken val;
	private DBLiteral key;
	
	/**
	 * Creates a new {@code DBPair}.
	 * 
	 * @param k  a key token
	 * @param v  a value token
	 * 
	 * 
	 * @see DBLiteral
	 * @see DBToken
	 */
	public DBPair(DBLiteral k, DBToken v)
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
	public DBToken Value()
	{
		return val;
	}
	
	@Override
	public DBLiteral Key()
	{
		return key;
	}
}