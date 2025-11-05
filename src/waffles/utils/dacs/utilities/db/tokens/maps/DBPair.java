package waffles.utils.dacs.utilities.db.tokens.maps;

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
 * @see DBToken
 */
public class DBPair implements PairToken<DBToken, DBToken>
{
	private DBToken key, val;
	
	/**
	 * Creates a new {@code DBPair}.
	 * 
	 * @param k  a key token
	 * @param v  a value token
	 * 
	 * 
	 * @see DBToken
	 */
	public DBPair(DBToken k, DBToken v)
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
	public DBToken Key()
	{
		return key;
	}
}