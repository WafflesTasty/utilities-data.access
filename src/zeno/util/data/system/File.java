package zeno.util.data.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import zeno.util.data.FileSystem;

/**
 * The {@code File} class defines a regular file in the file system.
 *
 * @author Zeno
 * @since Sep 17, 2019
 * @version 1.0
 * 
 *
 * @see FileSystem
 */
public class File extends FileSystem.Item
{	
	/**
	 * Creates a {@code BufferedReader} for a {@code File}.
	 * 
	 * @param file  a file to open
	 * @return  a buffered file reader
	 * @throws IOException  if the file cannot be accessed
	 * 
	 * 
	 * @see BufferedReader
	 * @see IOException
	 */
	public static BufferedReader Reader(File file) throws IOException
	{
		return Files.newBufferedReader(file.Path(), FileSystem.CHAR_SET);
	}
	
	/**
	 * Creates a {@code BufferedWriter} for a {@code File}.
	 * This writer appends additional data to the end of the file.
	 * 
	 * @param file  a file to open
	 * @return  a buffered file writer
	 * @throws IOException  if the file cannot be accessed
	 * 
	 * 
	 * @see BufferedWriter
	 * @see IOException
	 */
	public static BufferedWriter Appender(File file) throws IOException
	{
		return Files.newBufferedWriter(file.Path(), FileSystem.CHAR_SET, StandardOpenOption.APPEND);
	}
	
	/**
	 * Creates a {@code BufferedWriter} for a {@code File}.
	 * 
	 * @param file  a file to open
	 * @return  a buffered file writer
	 * @throws IOException  if the file cannot be accessed
	 * 
	 * 
	 * @see BufferedWriter
	 * @see IOException
	 */
	public static BufferedWriter Writer(File file) throws IOException
	{
		return Files.newBufferedWriter(file.Path(), FileSystem.CHAR_SET);
	}
	
	/**
	 * The {@code Handler} interface defines an object capable of
	 * reading and writing its contents to {@code Files}.
	 *
	 * @author Zeno
	 * @since Sep 17, 2019
	 * @version 1.0
	 */
	public static interface Handler
	{
		/**
		 * Reads a file's contents into the {@code Handler}.
		 * 
		 * @param file  a file to read from
		 * 
		 * 
		 * @see File
		 */
		public abstract void read(File file);
		
		/**
		 * Writes a file with the contents of the {@code Handler}.
		 * 
		 * @param file  a file to write to
		 * 
		 * 
		 * @see File
		 */
		public abstract void write(File file);
			
		/**
		 * Writes a file with the contents of the {@code Handler}.
		 * 
		 * @param url  a file url to write to
		 * 
		 * 
		 * @see String
		 */
		public default void write(String url)
		{
			write(new File(url));
		}
		
		/**
		 * Reads a file's contents into the {@code Handler}.
		 * 
		 * @param url  a file url to read from
		 * 
		 * 
		 * @see String
		 */
		public default void read(String url)
		{
			read(new File(url));
		}
	}
	
	
	private Path path;

	/**
	 * Creates a new {@code File}.
	 * 
	 * @param url  a folder url
	 * 
	 * 
	 * @see String
	 */
	public File(String url)
	{
		this(Paths.get(url));
	}
	
	/**
	 * Creates a new {@code File}.
	 * 
	 * @param p  a parent folder
	 * @param n  a folder name
	 * 
	 * 
	 * @see String
	 */
	public File(Folder p, String n)
	{
		this(p.Path().resolve(n));
	}
	
	/**
	 * Creates a new {@code File}.
	 * 
	 * @param p  a folder path
	 * 
	 * 
	 * @see Path
	 */
	public File(Path p)
	{
		path = p;
		create();
	}
	
	
	@Override
	public int hashCode()
	{
		return path.hashCode();
	}
	
	@Override
	public boolean exists()
	{
		return Files.isReadable(path);
	}
	
	@Override
	public File copyTo(Folder p)
	{
		File copy = new File(p, Name());
		
		try
		{
			Files.copy(path, copy.Path(),
			StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(copy);
		}
		
		return copy;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof File)
		{
			return path.equals(((File) o).Path());
		}

		return false;
	}
	
	@Override
	public File renameTo(String name)
	{
		Path tgt = path.getParent().resolve(name);
		
		try
		{	
			Files.move(path, tgt);
		}
		catch(IOException e)
		{
			throw new FileSystem.AccessError(path);
		}
		
		return new File(tgt);
	}
	
	
	@Override
	public void create()
	{
		if(!exists())
		{
			try
			{
				Parent().create();
				Files.createFile(path);
			}
			catch (IOException e)
			{
				throw new FileSystem.AccessError(path);
			}
		}
	}

	@Override
	public void delete()
	{
		if(exists())
		{
			try
			{
				Files.delete(path);
			}
			catch (IOException e)
			{
				throw new FileSystem.AccessError(path);
			}
		}
	}
	
	@Override
	public Path Path()
	{
		return path;
	}
}