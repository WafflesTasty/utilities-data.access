package zeno.util.data.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zeno.util.data.FileSystem;
import zeno.util.data.files.File;

/**
 * The {@code TextFile} class defines a {@code File Handler} for plain text.
 *
 * @author Zeno
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see File
 */
public class TextFile implements File.Handler
{
	private List<String> lines;
	
	/**
	 * Creates a new {@code TextFile}.
	 */
	public TextFile()
	{
		lines = new ArrayList<>();
	}
	
	
	/**
	 * Returns the text in the {@code TextFile}.
	 * 
	 * @return  a list of strings
	 * 
	 * 
	 * @see String
	 * @see List
	 */
	public List<String> Lines()
	{
		return lines;
	}
	
	/**
	 * Returns a string from the {@code TextFile}.
	 * 
	 * @param index  a line index
	 * @return  a text string
	 * 
	 * 
	 * @see String
	 */
	public String Line(int index)
	{
		return lines.get(index);
	}
		
	/**
	 * Removes a string from the {@code TextFile}.
	 * 
	 * @param index  a line index
	 */
	public void remove(int index)
	{
		lines.remove(index);
	}
	
	/**
	 * Adds a string to the {@code TextFile}.
	 * 
	 * @param line  a new string line
	 * 
	 * 
	 * @see String
	 */
	public void add(String line)
	{
		lines.add(line);
	}
	
	/**
	 * Clears the {@code TextFile}.
	 */
	public void clear()
	{
		lines.clear();
	}
	
	
	/**
	 * Appends data to a {@code TextFile}.
	 * 
	 * @param file  a file to append
	 * 
	 * 
	 * @see File
	 */
	public void append(File file)
	{
		try(BufferedWriter writer = File.Appender(file))
		{
			for(String line : lines)
			{
				writer.write(line + "\r\n");
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(file);
		}
	}
	
	@Override
	public void write(File file)
	{
		try(BufferedWriter writer = File.Writer(file))
		{
			for(String line : lines)
			{
				writer.write(line + "\r\n");
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(file);
		}
	}
	
	@Override
	public void read(File file)
	{
		if(!file.exists())
		{
			throw new FileSystem.FileNotFoundError(file);
		}
		
		lines.clear();
		try(BufferedReader reader = File.Reader(file))
		{
			String line = "";
			while(line != null)
			{
				line = reader.readLine();
				if(line != null)
				{
					lines.add(line);
				}
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(file);
		}
	}
}