package waffles.utils.dacs.files.plaintext.strings;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import waffles.utils.dacs.File;
import waffles.utils.dacs.FileSystem;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * A {@code StringAppender} appends strings to files in unformatted plaintext.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 *
 * 
 * @see Iterable
 * @see Writer
 */
public class StringAppender implements Writer<Iterable<String>>
{
	private File file;
	private BufferedWriter writer;
	
	@Override
	public void write(Iterable<String> obj, File f)
	{
		try
		{
			file = f;
			writer = Files.newBufferedWriter(f.Path(), FileSystem.CHAR_SET, StandardOpenOption.APPEND);
			for(String line : obj)
			{
				writer.write(line + "\r\n");
			}
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}
}