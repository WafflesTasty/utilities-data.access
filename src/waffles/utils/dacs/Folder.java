package waffles.utils.dacs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import waffles.utils.dacs.utilities.Streamer;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * The {@code Folder} class defines a directory in the file system.
 *
 * @author Zeno
 * @since Sep 17, 2019
 * @version 1.0
 * 
 *
 * @see FileSystem
 */
public class Folder extends FileSystem.Item
{
	/**
	 * Creates a new {@code Folder}.
	 * 
	 * @param url  a folder url
	 * 
	 * 
	 * @see String
	 */
	public Folder(String url)
	{
		super(url);
	}
	
	/**
	 * Creates a new {@code Folder}.
	 * 
	 * @param p  a parent folder
	 * @param n  a folder name
	 * 
	 * 
	 * @see String
	 */
	public Folder(Folder p, String n)
	{
		super(p, n);
	}
	
	/**
	 * Creates a new {@code Folder}.
	 * 
	 * @param p  a folder path
	 * 
	 * 
	 * @see Path
	 */
	public Folder(Path p)
	{
		super(p);
	}
	
		
	/**
	 * Returns an iterable of the subfolders in this {@code Folder}.
	 * </br> This stream is empty if the folder does not exist in the file system.
	 * 
	 * @return  a stream of folders
	 * @throws IOException  if the stream could not be accessed
	 * 
	 * 
	 * @see Streamer
	 */
	public Streamer<Folder> Folders() throws IOException
	{
		return new Streamer<>(Files.walk(Path(), 1).filter
		((p) -> Files.isDirectory(p)).filter
		((p) -> !p.equals(Path())).map
		((p) ->
		{
			return new Folder(p);
		}));
	}
	
	/**
	 * Returns an iterable of the files in this {@code Folder}.
	 * </br> This stream is empty if the folder does not exist in the file system.
	 * 
	 * @return  a stream of files
	 * @throws IOException  if the stream could not be accessed
	 * 
	 * 
	 * @see Streamer
	 */
	public Streamer<File> Files() throws IOException
	{
		return new Streamer<>(Files.walk(Path(), 1).filter
		((p) -> !Files.isDirectory(p)).map
		((p) ->
		{
			return new File(p);
		}));
	}
	
	
	/**
	 * Returns a file inside of this {@code Folder}.
	 * 
	 * @param n  a relative path
	 * @return  a subfile
	 * 
	 * 
	 * @see String
	 * @see File
	 */
	public File subFile(String n)
	{
		return new File(this, n);
	}
	
	/**
	 * Returns a folder inside of this {@code Folder}.
	 * 
	 * @param n  a relative path
	 * @return  a subfolder
	 * 
	 * 
	 * @see String
	 */
	public Folder subFolder(String n)
	{
		return new Folder(this, n);
	}
		
	/**
	 * Checks if the {@code Folder} contains subfolders.
	 * 
	 * @return  {@code true} if subfolders exist
	 */
	public boolean hasFolders()
	{
		try(Streamer<Folder> stream = Folders())
		{
			return Folders().iterator().hasNext();
		}
		catch (IOException e)
		{
			throw new AccessError(Path());
		}
	}
	
	/**
	 * Checks if the {@code Folder} contains files.
	 * 
	 * @return  {@code true} if files exist
	 */
	public boolean hasFiles()
	{
		try(Streamer<File> stream = Files())
		{
			return Files().iterator().hasNext();
		}
		catch (IOException e)
		{
			throw new AccessError(Path());
		}
	}

	

	@Override
	public boolean exists()
	{
		return Files.isDirectory(Path());
	}
	
	@Override
	public Folder copyTo(Folder p)
	{
		p.create();
		Folder copy = new Folder(p, Name());
		copy.create();
		
		try(Streamer<Folder> folders = Folders())
		{
			for(Folder f : folders)
			{
				f.copyTo(copy);
			}
		}
		catch (IOException e)
		{
			throw new AccessError(Path());
		}
		
		try(Streamer<File> files = Files())
		{
			for(File f : files)
			{
				f.copyTo(copy);
			}
		}
		catch (IOException e)
		{
			throw new AccessError(Path());
		}
		
		return copy;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Folder)
		{
			return super.equals(o);
		}

		return false;
	}
	
	@Override
	public Folder renameTo(String name)
	{
		Folder tgt = new Folder(Path().getParent().resolve(name));
		try(Streamer<Folder> folders = Folders())
		{
			for(Folder f : folders)
			{
				f.copyTo(tgt);
			}
		}
		catch(IOException e)
		{
			throw new AccessError(Path());
		}
		
		delete();
		return tgt;
	}
	
	
	@Override
	public void create()
	{
		if(!exists())
		{
			try{Files.createDirectories(Path());}
			catch(IOException e)
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
			try(Streamer<Folder> folders = Folders())
			{
				for(Folder f : folders)
				{
					f.delete();
				}
			}
			catch (IOException e)
			{
				throw new AccessError(Path());
			}
			
			try(Streamer<File> files = Files())
			{
				for(File f : files)
				{
					f.delete();
				}
			}
			catch (IOException e)
			{
				throw new AccessError(Path());
			}
			
			try{Files.delete(Path());}
			catch(IOException e)
			{
				e.printStackTrace();
				throw new AccessError(Path());
			}
		}
	}
}