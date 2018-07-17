package zeno.util.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import zeno.util.tools.Iterables;
import zeno.util.tools.patterns.manipulators.Loadable;
import zeno.util.tools.patterns.manipulators.Saveable;

/**
 * The {@code File} class defines a generic file stored on the hard disk.
 * 
 * @since Sep 23, 2016
 * @author Zeno
 * 
 * @see Loadable
 * @see Saveable
 */
public class File implements Loadable, Saveable
{
	/**
	 * Defines the default character set used in text files.
	 */
	public static final Charset CHAR_SET = StandardCharsets.ISO_8859_1;
	
	/**
	 * The {@code File.Handler} class defines a file containing specific objects.
	 * 
	 * @param <O>  the type of objects stored in the file
	 * 
	 * @since Sep 23, 2016
	 * @author Zeno
	 *
	 * @see Iterable
	 * @see File
	 */
	public static abstract class Handler<O> extends File implements Iterable<O>
	{
		private List<O> objects;
		
		/**
		 * Creates a new {@code File.Handler}.
		 * 
		 * @param url  a file url
		 * @see String
		 */
		public Handler(String url)
		{
			super(url);
		}

		/**
		 * Creates a new {@code File.Handler}.
		 * 
		 * @param path  a file path
		 * @see Path
		 */
		public Handler(Path path)
		{
			super(path);
		}
		
		
		/**
		 * Writes a list of objects into the {@code File.Handler}.
		 * 
		 * @param objects  a list of objects to write
		 * @return  {@code true} if successful
		 */
		protected abstract boolean write(List<O> objects);

		/**
		 * Reads a list of objects into the {@code File.Handler}.
		 * 
		 * @return  a list of objects being read
		 * @see List
		 */
		protected abstract List<O> read();
		
		
		/**
		 * Removes an object from the {@code File.Handler}.
		 * 
		 * @param object  an object to remove
		 */
		public void remove(O object)
		{
			if(object != null)
			{
				objects.remove(object);
			}
		}
		
		/**
		 * Adds an object to the end of the {@code File.Handler}.
		 * 
		 * @param object  an object to add
		 */
		public void add(O object)
		{
			if(object != null)
			{
				objects.add(object);
			}
		}
		
		/**
		 * Returns the objects of the {@code File.Handler}.
		 * 
		 * @return  the handler's objects
		 * @see List
		 */
		public List<O> Objects()
		{
			return objects;
		}
		
		/**
		 * Clears the objects from the {@code File.Handler}.
		 */
		public void clear()
		{
			objects.clear();
		}
		
		
		@Override
		public Iterator<O> iterator()
		{
			return objects.iterator();
		}
		
		@Override
		public boolean load()
		{
			if(!super.load())
			{
				return false;
			}
			
			objects = read();
			if(objects == null)
			{
				objects = new ArrayList<>();
				return false;
			}
			
			return true;
		}
		
		@Override
		public boolean save()
		{
			if(!super.save())
			{
				return false;
			}
			
			if(!write(objects))
			{
				return false;
			}
			
			return true;
		}
	}
	
	
	private Path fpath;
	
	/**
	 * Creates a new {@code File}.
	 * 
	 * @param url  a file url
	 * @see String
	 */
	public File(String url)
	{
		this(Paths.get(url));
	}

	/**
	 * Creates a new {@code File}.
	 * 
	 * @param path  a file path
	 * @see Path
	 */
	public File(Path path)
	{
		fpath = path;
		load();
	}
	
	
	/**
	 * Iterates over all paths in the {@code File}.
	 * 
	 * @param depth  a recursion depth
	 * @return  a fpath iterable
	 * @see Iterable
	 * @see Path
	 */
	public Iterable<Path> paths(int depth)
	{
		return () ->
		{
			Iterator<Path> iterator = null;
			
			try
			{
				iterator = Files.walk(fpath, depth).iterator();
				iterator.next();
			}
			catch (IOException e)
			{
				iterator = Iterables.<Path>empty().iterator();
			}
			
			return iterator;
		};
	}
	
	/**
	 * Changes the current fpath of the {@code File}.
	 * 
	 * @param url  a file url
	 * @see String
	 */
	public void setPath(String url)
	{
		setPath(Paths.get(url));
	}
	
	/**
	 * Changes the current path of the {@code File}.
	 * 
	 * @param path  a file path
	 * @see Path
	 */
	public void setPath(Path path)
	{
		fpath = path;
	}
	
	/**
	 * Checks if the fpath points to a {@code Directory}.
	 * 
	 * @return  {@code true} if the fpath is a directory
	 */
	public boolean isDirectory()
	{
		return Files.isDirectory(fpath);
	}
	
	/**
	 * Checks if the fpath points to a {@code File}.
	 * 
	 * @return  {@code true} if the fpath is a file
	 */
	public boolean isPresent()
	{
		return Files.exists(fpath);
	}
		
	/**
	 * Deletes the {@code File} from hard disk.
	 * 
	 * @return  {@code true} if the file is deleted
	 */
	public boolean delete()
	{
		try
		{
			Files.deleteIfExists(fpath);
		}
		catch (IOException e)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returns the fpath of the {@code File}.
	 * 
	 * @return  the file fpath
	 * @see Path
	 */
	public Path Path()
	{
		return fpath;
	}
	
	
	@Override
	public String toString()
	{
		return getClass() + "@" + fpath;
	}
	
	@Override
	public boolean load()
	{
		if(!isPresent())
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
				Files.createFile(fpath);
			}
			catch (IOException e)
			{
				return false;
			}
		}
		
		return true;
	}
}