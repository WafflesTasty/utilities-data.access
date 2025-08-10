package waffles.utils.dacs.files.tokens;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.files.plaintext.chars.CharReader;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.parsers.Parsable;

/**
 * A {@code TokenReader} reads a {@code Token} from a {@code File}.
 * The behavior of the reader is dependent on a {@code Parser}, which
 * runs through all characters in the {{@link #read(Iterable)}
 * method and generates the resulting {@code Token}.
 *
 * @author Waffles
 * @since 05 Dec 2023
 * @version 1.1
 * 
 * 
 * @param <T>  a token type
 * @see Reader
 * @see Token
 */
public class TokenReader<T extends Token> implements Reader<T>
{	
	private Parsable<T> parser;
	
	/**
	 * Creates a new {@code TokenReader}.
	 * 
	 * @param p  a token parser
	 * 
	 * 
	 * @see Parsable
	 */
	public TokenReader(Parsable<T> p)
	{
		parser = p;
	}
	
	/**
	 * Returns a parser for the {@code TokenReader}.
	 * 
	 * @return  a token parser
	 * 
	 * 
	 * @see Parsable
	 */
	public Parsable<T> Parser()
	{
		return parser;
	}
	
	
	/**
	 * Reads a token from an {@code Iterable}.
	 * 
	 * @param src  a source iterable
	 * @return  a token
	 * 
	 * 
	 * @see Iterable
	 */
	public T read(Iterable<Character> src)
	{
		for(char c : src)
		{
			if(!Parser().consume(c))
			{
				break;
			}
		}
		
		T tkn = Parser().generate();
		Parser().reset();
		return tkn;
	}
		
	
	@Override
	public T read(File file)
	{
		return read(new CharReader().read(file));
	}
}