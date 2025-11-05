package waffles.utils.dacs.files.tokens;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import waffles.utils.dacs.File;
import waffles.utils.dacs.FileSystem;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.utilities.files.AccessError;
import waffles.utils.lang.tokens.Token;

/**
 * A {@code TokenWriter} writes a {@code Token} to a {@code File}.
 * By default, the {@code condense()} function is used to serialize
 * the {@code Token} to a string. Alternatively, a boolean flag can
 * be provided to serialize its full description instead.
 * 
 *
 * @author Waffles
 * @since 12 Mar 2024
 * @version 1.1
 *
 *
 * @param <T>  a token type
 * @see Writer
 * @see Token
 */
public class TokenWriter<T extends Token> implements Writer<T>
{
	/**
	 * The {@code Hints} interface defines {@code TokenWriter} settings.
	 *
	 * @author Waffles
	 * @since 10 Aug 2025
	 * @version 1.1
	 */
	@FunctionalInterface
	public static interface Hints
	{
		/**
		 * Indicates whether to condense the {@code Token}.
		 * 
		 * @return  {@code true} for condensed writing
		 */
		public abstract boolean isCondensed();
	}
	
	
	private File file;
	private Hints hints;
		
	/**
	 * Creates a new {@code TokenWriter}.
	 * 
	 * @param h  file hints
	 * 
	 * 
	 * @see Hints
	 */
	public TokenWriter(Hints h)
	{
		hints = h;
	}
	
	/**
	 * Creates a new {@code TokenWriter}.
	 * 
	 * @param cnd  a condense state
	 */
	public TokenWriter(boolean cnd)
	{
		this(() -> cnd);
	}
	
	/**
	 * Returns {@code TokenWriter} hints.
	 * 
	 * @return  writer hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return hints;
	}
	
	
	@Override
	public void write(T tkn, File f)
	{
		BufferedWriter writer;
		
		try
		{
			writer = Files.newBufferedWriter(f.Path(), FileSystem.CHAR_SET);
			
			if(Hints().isCondensed())
				writer.write(tkn.condense());
			else
			{
				for(String s : tkn.describe())
				{
					writer.write(s);
				}
			}

			writer.close();
			file = f;
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}
}