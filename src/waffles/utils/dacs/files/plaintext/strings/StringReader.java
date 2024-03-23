package waffles.utils.dacs.files.plaintext.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import waffles.utils.dacs.File;
import waffles.utils.dacs.FileSystem;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * A {@code StringReader} reads text files by iterating over each line in order.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 *
 * 
 * @see Iterable
 * @see Iterator
 * @see Reader
 */
public class StringReader implements Iterator<String>, Reader<Iterable<String>>
{
	private File file;
	private BufferedReader reader;
	
	/**
	 * Counts the number of lines in a {@code File}.
	 * 
	 * @param f  a text file
	 * @return   a line count
	 * 
	 * 
	 * @see File
	 */
	public int count(File f)
	{
		int count = 0;
		for(String s : read(f))
		{
			count++;
		}
		
		return count;
	}
	
	@Override
	public Iterable<String> read(File f)
	{
		try
		{
			reader = Files.newBufferedReader(f.Path(), FileSystem.CHAR_SET);
			next = reader.readLine();
			file = f;
		}
		catch (IOException e)
		{
			throw new AccessError(f);
		}
		
		return () -> this;
	}

	
	private String next;
	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public String next()
	{
		try
		{
			String curr = next;
			next = reader.readLine();
			return curr;
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}
}