package waffles.utils.dacs.files.tokens.objects;

import waffles.utils.dacs.files.tokens.Token;
import waffles.utils.lang.Format;
import waffles.utils.sets.keymaps.Pair;

/**
 * A {@code TokenPair} defines a {@code Token} consisting of a key-value pair.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 * 
 * 
 * @param <K>  a key type
 * @param <V>  a value type
 * @see Token
 * @see Pair
 */
public interface TokenPair<K extends Token, V extends Token> extends Pair<K, V>, Token
{			
	/**
	 * Defines a default {@code TokenPair} separator.
	 * If you want a different one, override
	 * the {@link #Formatter()} method.
	 */
	public static char SEPARATOR = ',';
	
	/**
	 * Returns a formatter for the {@code TokenPair}.
	 * 
	 * @param sep  a pair separator
	 * @return  a pair formatter
	 * 
	 * 
	 * @see Format
	 */
	public default Format<TokenPair<K, V>> Formatter(char sep)
	{
		return pair -> 
		{
			String key = pair.Key().parse();
			String val = pair.Value().parse();
			return key + " " + sep + " " + val;
		};
	}	
	
	
	@Override
	public default Format<?> Formatter()
	{
		return Formatter(SEPARATOR);
	}
}