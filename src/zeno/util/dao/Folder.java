package zeno.util.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The {@code Folder} class defines a generic folder stored on the hard disk.
 * 
 * @since Sep 23, 2016
 * @author Zeno
 * 
 * @see File
 */
public class Folder extends File
{
	/**
	 * Creates a new {@code Folder}.
	 * 
	 * @param url  a folder url
	 */
	public Folder(String url)
	{
		super(url);
	}
	
	/**
	 * Creates a new {@code Folder}.
	 * 
	 * @param path  a folder path
	 * @see Path
	 */
	public Folder(Path path)
	{
		super(path);
	}

	
	@Override
	public boolean delete()
	{
		File file = null;
		boolean isDeleted = true;
		for(Path path : paths(1))
		{
			if(Files.isDirectory(path))
				file = new Folder(path);
			else
				file = new File(path);
			
			if(!file.delete())
			{
				isDeleted = false;
			}
		}
		
		if(!isDeleted)
		{
			return false;
		}
		
		return super.delete();
	}
	
	@Override
	public boolean load()
	{
		if(!isDirectory())
		{
			return false;
		}
		
		return true;
	}

	@Override
	public boolean save()
	{
		if(!isPresent())
		{
			try
			{
				Files.createDirectories(Path());
			}
			catch (IOException e)
			{
				return false;
			}
		}
		
		return true;
	}
}