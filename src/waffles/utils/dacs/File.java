package waffles.utils.dacs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import waffles.utils.dacs.utilities.files.AccessError;

/**
 * A {@code File} defines a link pointing to a single document in the file system.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 *
 * @see FileSystem
 */
public class File extends FileSystem.Item
{	
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
		super(url);
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
		super(p, n);
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
		super(p);
	}
	
	
	
	@Override
	public boolean exists()
	{
		return Files.isReadable(Path());
	}
		
		
	@Override
	public File copyTo(Folder p)
	{
		File copy = new File(p, Name());
		
		try
		{
			Files.copy(Path(), copy.Path(),
			StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e)
		{
			throw new AccessError(copy);
		}
		
		return copy;
	}
		
	@Override
	public File renameTo(String name)
	{
		Path tgt = Path().getParent().resolve(name);
		
		try
		{	
			Files.move(Path(), tgt);
		}
		catch(IOException e)
		{
			throw new AccessError(Path());
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
				Files.createFile(Path());
			}
			catch (IOException e)
			{
				throw new AccessError(Path());
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
				Files.delete(Path());
			}
			catch (IOException e)
			{
				throw new AccessError(Path());
			}
		}
	}
}