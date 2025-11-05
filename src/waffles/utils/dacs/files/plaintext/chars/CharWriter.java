package waffles.utils.dacs.files.plaintext.chars;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import waffles.utils.dacs.File;
import waffles.utils.dacs.FileSystem;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.utilities.files.AccessError;

/**
 * A {@code CharWriter} writes characters to file in unformatted plaintext.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 *
 * 
 * @see Iterable
 * @see Writer
 */
public class CharWriter implements Writer<Iterable<Character>>
{
	private File file;
	private BufferedWriter writer;
	
	@Override
	public void write(Iterable<Character> obj, File f)
	{
		try
		{
			file = f;
			writer = Files.newBufferedWriter(f.Path(), FileSystem.CHAR_SET);
			for(Character c : obj)
			{
				writer.write(c + "\r\n");
			}
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}
}