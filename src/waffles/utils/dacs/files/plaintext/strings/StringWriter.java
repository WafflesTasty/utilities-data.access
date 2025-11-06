package waffles.utils.dacs.files.plaintext.strings;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import waffles.utils.dacs.File;
import waffles.utils.dacs.FileSystem;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.utilities.files.AccessError;

/**
 * A {@code StringWriter} writes strings to file in unformatted plaintext.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 *
 * 
 * @see Iterable
 * @see Writer
 */
public class StringWriter implements Writer<Iterable<String>>
{
	private File file;
	private BufferedWriter writer;
	
	@Override
	public void write(Iterable<String> obj, File f)
	{
		try
		{
			file = f;
			writer = Files.newBufferedWriter(f.Path(), FileSystem.CHAR_SET);
			for(String line : obj)
			{
				writer.write(line + "\r\n");
			}
			
			writer.close();
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}
}