package zeno.util.data.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import zeno.util.data.FileSystem;
import zeno.util.tools.helper.Iterables;
import zeno.util.tools.helper.iterables.StreamIterable;

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
	private Path path;

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
		this(Paths.get(url));
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
		this(p.Path().resolve(n));
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
		path = p;
		create();
	}
	
		
	/**
	 * Returns an iterable of the subfolders in this {@code Folder}.
	 * </br> This stream is empty if the folder does not exist in the file system.
	 * 
	 * @return  a stream of folders
	 * @throws IOException  if the stream could not be accessed
	 * 
	 * 
	 * @see StreamIterable
	 */
	public StreamIterable<Folder> Folders() throws IOException
	{
		return Iterables.of(Files.walk(path, 1).filter
		((p) -> Files.isDirectory(p)).filter
		((p) -> !p.equals(path)).map
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
	 * @see StreamIterable
	 */
	public StreamIterable<File> Files() throws IOException
	{
		return Iterables.of(Files.walk(path, 1).filter
		((p) -> !Files.isDirectory(p)).map
		((p) ->
		{
			return new File(p);
		}));
	}
			
	
	/**
	 * Checks if the {@code Folder} contains subfolders.
	 * 
	 * @return  {@code true} if subfolders exist
	 */
	public boolean hasFolders()
	{
		try(StreamIterable<Folder> stream = Folders())
		{
			return !Folders().iterator().hasNext();
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(path);
		}
	}
	
	/**
	 * Checks if the {@code Folder} contains files.
	 * 
	 * @return  {@code true} if files exist
	 */
	public boolean hasFiles()
	{
		try(StreamIterable<File> stream = Files())
		{
			return !Files().iterator().hasNext();
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(path);
		}
	}

	
	@Override
	public boolean exists()
	{
		return Files.isDirectory(path);
	}
	
	@Override
	public void copyTo(Folder p)
	{
		p.create();
		Folder copy = new Folder(p, Name());
		try(StreamIterable<Folder> folders = Folders())
		{
			for(Folder f : folders)
			{
				f.copyTo(copy);
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(path);
		}
		
		try(StreamIterable<File> files = Files())
		{
			for(File f : files)
			{
				f.copyTo(copy);
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(path);
		}
	}
	
	@Override
	public void create()
	{
		if(!exists())
		{
			try{Files.createDirectory(path);}
			catch(IOException e)
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
			try(StreamIterable<Folder> folders = Folders())
			{
				for(Folder f : folders)
				{
					f.delete();
				}
			}
			catch (IOException e)
			{
				throw new FileSystem.AccessError(path);
			}
			
			try(StreamIterable<File> files = Files())
			{
				for(File f : files)
				{
					f.delete();
				}
			}
			catch (IOException e)
			{
				throw new FileSystem.AccessError(path);
			}
			
			try{Files.delete(path);}
			catch(IOException e)
			{
				e.printStackTrace();
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