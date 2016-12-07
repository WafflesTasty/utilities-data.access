package zeno.util.dao.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import zeno.util.dao.File;

/**
 * The {@code TextHandler} class reads files as a sequence of text strings.
 * 
 * @since Sep 23, 2016
 * @author Zeno
 * 
 * @see String
 * @see File
 */
public class TextHandler extends File.Handler<String>
{
	/**
	 * Creates a new {@code TextHandler}.
	 * 
	 * @param url  a file url
	 * @see String
	 */
	public TextHandler(String url)
	{
		super(url);
	}
	
	/**
	 * Creates a new {@code TextHandler}.
	 * 
	 * @param path  a file path
	 * @see Path
	 */
	public TextHandler(Path path)
	{
		super(path);
	}

	
	@Override
	protected boolean write(List<String> objects)
	{
		try(BufferedWriter writer = Files.newBufferedWriter(Path(), File.CHAR_SET))
		{
			for(String line : objects)
			{
				writer.write(line + "\r\n");
			}
		}
		catch (IOException e)
		{
			return false;
		}
		
		return true;
	}

	@Override
	protected List<String> read()
	{
		List<String> lines = new ArrayList<>();
		try(BufferedReader reader = Files.newBufferedReader(Path(), File.CHAR_SET))
		{
			int i = -1;
			String line = "";
			while(line != null)
			{
				line = reader.readLine();
				lines.add(line);
				i++;
			}
			
			lines.remove(i);
		}
		catch (IOException e)
		{
			return null;
		}
		
		return lines;
	}
}