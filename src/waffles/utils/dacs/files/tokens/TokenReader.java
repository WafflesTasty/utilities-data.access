package waffles.utils.dacs.files.tokens;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.files.plaintext.chars.CharReader;
import waffles.utils.dacs.files.tokens.Token.Parser;

/**
 * A {@code TokenReader} reads {@code Tokens} from a {@code File}.
 *
 * @author Waffles
 * @since 05 Dec 2023
 * @version 1.1
 * 
 * 
 * @param <T>  a token class
 * @see Reader
 * @see Token
 */
public class TokenReader<T extends Token> implements Reader<T>
{	
	private Parser<T> parser;
	
	/**
	 * Creates a new {@code TokenReader}.
	 * 
	 * @param p  a token parser
	 * 
	 * 
	 * @see Parser
	 */
	public TokenReader(Parser<T> p)
	{
		parser = p;
	}
	
	/**
	 * Returns a parser for the {@code TokenReader}.
	 * 
	 * @return  a token parser
	 * 
	 * 
	 * @see Token
	 */
	public Parser<T> Parser()
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