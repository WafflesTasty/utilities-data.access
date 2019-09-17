package zeno.util.data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import zeno.util.data.files.Folder;
import zeno.util.tools.patterns.properties.IRelatable;

/**
 * The {@code FileSystem} class defines static access methods to manipulate the file system.
 *
 * @author Zeno
 * @since Sep 17, 2019
 * @version 1.0
 */
public final class FileSystem
{
	/**
	 * The {@code AccessError} class defines an error thrown when a path can't be accessed.
	 *
	 * @author Zeno
	 * @since Sep 17, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class AccessError extends RuntimeException
	{
		private static final long serialVersionUID = -5817136207785192255L;
		

		/**
		 * Creates a new {@code AccessError}.
		 * 
		 * @param p  a target path
		 * 
		 * 
		 * @see Path
		 */
		public AccessError(Path p)
		{
			super("Could not access " + p.toString() + ".");
		}
	}

	/**
	 * The {@code Item} class defines a single item in the {@code FileSystem}.
	 *
	 * @author Zeno
	 * @since Sep 17, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see FileSystem
	 * @see IRelatable
	 */
	public static abstract class Item implements IRelatable
	{		
		/**
		 * Returns the path of the {@code Item}.
		 * 
		 * @return  an file system path
		 * 
		 * 
		 * @see Path
		 */
		public abstract Path Path();

		/**
		 * Creates the {@code Item} in the file system.
		 */
		public abstract void create();
		
		/**
		 * Deletes the {@code Item} from the file system.
		 */
		public abstract void delete();
		
		/**
		 * Copies the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a parent folder
		 * 
		 * 
		 * @see Folder
		 */
		public abstract void copyTo(Folder p);
		

		/**
		 * Moves the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a parent folder
		 * 
		 * 
		 * @see Folder
		 */
		public void moveTo(Folder p)
		{
			copyTo(p);
			delete();
		}
		
		/**
		 * Copies the {@code Item} to a new folder in the file system.
		 * 
		 * @param url  a folder url
		 * 
		 * 
		 * @see String
		 */
		public void copyTo(String url)
		{
			copyTo(Paths.get(url));
		}
		
		/**
		 * Moves the {@code Item} to a new folder in the file system.
		 * 
		 * @param url  a folder url
		 * 
		 * 
		 * @see Path
		 */
		public void moveTo(String url)
		{
			moveTo(Paths.get(url));
		}
		
		/**
		 * Copies the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a folder path
		 * 
		 * 
		 * @see Path
		 */
		public void copyTo(Path p)
		{
			copyTo(new Folder(p));
		}
		
		/**
		 * Moves the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a folder path
		 * 
		 * 
		 * @see Path
		 */
		public void moveTo(Path p)
		{
			moveTo(new Folder(p));
		}
		
		
		/**
		 * Checks if the {@code Item} is in the file system.
		 * 
		 * @return  {@code true} if the item exists
		 */
		public boolean exists()
		{
			return Files.exists(Path());
		}
		
		/**
		 * Returns the file name of the {@code Item}.
		 * 
		 * @return  a file name
		 * 
		 * 
		 * @see String
		 */
		public String Name()
		{
			return Path().getFileName().toString();
		}
		
		
		@Override
		public String toString()
		{
			return Path().toString();
		}
		
		@Override
		public Folder Parent()
		{
			Path p = Path().getParent();
			if(p != null)
			{
				return new Folder(p);
			}

			return null;
		}
	}
	
	
	/**
	 * Returns the working {@code Folder} of the current application.
	 * 
	 * @return  the application's working folder
	 * 
	 * 
	 * @see Folder
	 */
	public static Folder Current()
	{
		return new Folder(java.lang.System.getProperty("user.dir"));
	}
	
	/**
	 * Returns the home {@code Folder} of the current user.
	 * 
	 * @return  the user's home folder
	 * 
	 * 
	 * @see Folder
	 */
	public static Folder Home()
	{
		return new Folder(java.lang.System.getProperty("user.home"));
	}
	
	
	private FileSystem()
	{
		// NOT APPLICABLE
	}
}