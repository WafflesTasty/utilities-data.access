package zeno.util.data.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
	public boolean exists()
	{
		return Files.isReadable(path);
	}
	
	@Override
	public void copyTo(Folder p)
	{
		File copy = new File(p, Name());
		try
		{
			Files.copy(path, copy.Path(),
			StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(copy.Path());
		}
	}
	
	@Override
	public void create()
	{
		if(!exists())
		{
			try
			{
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