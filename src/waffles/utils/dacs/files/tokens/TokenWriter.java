package waffles.utils.dacs.files.tokens;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import waffles.utils.dacs.File;
import waffles.utils.dacs.FileSystem;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * A {@code TokenWriter} writes {@code Tokens} to a {@code File}.
 *
 * @author Waffles
 * @since 12 Mar 2024
 * @version 1.1
 *
 *
 * @param <T>  a token class
 * @see Writer
 * @see Token
 */
public class TokenWriter<T extends Token> implements Writer<T>
{
	private File file;
	private BufferedWriter writer;

	@Override
	public void write(T token, File f)
	{
		try
		{
			writer = Files.newBufferedWriter(f.Path(), FileSystem.CHAR_SET);
			writer.write(onParse(token));
			writer.close();
			file = f;
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}
	
	/**
	 * Generates a string from a single token.
	 * 
	 * @param token  a token value
	 * @return  a token string
	 */
	public String onParse(T token)
	{
		return token.parse();
	}
}