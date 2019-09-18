package zeno.util.data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import zeno.util.data.system.File;
import zeno.util.data.system.Folder;
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
	 * Defines the default character set used in reading files.
	 * 
	 * 
	 * @see Charset
	 */
	public static final Charset CHAR_SET = StandardCharsets.ISO_8859_1;
		
	/**
	 * The {@code FileNotFoundError} class defines an error thrown when a file does not exist.
	 *
	 * @author Zeno
	 * @since Sep 17, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class FileNotFoundError extends RuntimeException
	{
		private static final long serialVersionUID = -3255871204648574328L;

		
		/**
		 * Creates a new {@code FileNotFoundError}.
		 * 
		 * @param f  a target file
		 * 
		 * 
		 * @see File
		 */
		public FileNotFoundError(File f)
		{
			super("Could not find file " + f.toString());
		}
	}

	/**
	 * The {@code MalformedFileError} class defines an error thrown when a file cannot be parsed.
	 *
	 * @author Zeno
	 * @since Sep 17, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class MalformedFileError extends RuntimeException
	{
		private static final long serialVersionUID = -8920449152648231475L;
		

		/**
		 * Creates a new {@code MalformedFileError}.
		 * 
		 * @param file  a target file
		 * @param line  a target line
		 * 
		 * 
		 * @see String
		 * @see File
		 */
		public MalformedFileError(File file, String line)
		{
			super("Could not parse file " + file.Path() + ". The following line is malformed: \r\n" + line);
		}
	}
	
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
		 * @param f  a target file
		 * 
		 * 
		 * @see File
		 */
		public AccessError(File f)
		{
			this(f.Path());
		}

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
			super("Could not access path " + p.toString() + ".");
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
		 * Renames the {@code Item} to a new file name.
		 * 
		 * @param name  a file name
		 * @return  the created item
		 * 
		 * 
		 * @see String
		 */
		public abstract Item renameTo(String name);
		
		/**
		 * Copies the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a parent folder
		 * @return  the created item
		 * 
		 * 
		 * @see Folder
		 */
		public abstract Item copyTo(Folder p);
		
		
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
		 * Moves the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a parent folder
		 * @return  the created item
		 * 
		 * 
		 * @see Folder
		 */
		public Item moveTo(Folder p)
		{
			Item move = copyTo(p);
			delete(); return move;
		}
				
		/**
		 * Copies the {@code Item} to a new folder in the file system.
		 * 
		 * @param url  a folder url
		 * @return  the created item
		 * 
		 * 
		 * @see String
		 */
		public Item copyTo(String url)
		{
			return copyTo(Paths.get(url));
		}
		
		/**
		 * Moves the {@code Item} to a new folder in the file system.
		 * 
		 * @param url  a folder url
		 * @return  the created item
		 * 
		 * 
		 * @see Path
		 */
		public Item moveTo(String url)
		{
			return moveTo(Paths.get(url));
		}
		
		/**
		 * Copies the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a folder path
		 * @return  the created item
		 * 
		 * 
		 * @see Path
		 */
		public Item copyTo(Path p)
		{
			return copyTo(new Folder(p));
		}
		
		/**
		 * Moves the {@code Item} to a new folder in the file system.
		 * 
		 * @param p  a folder path
		 * @return  the created item
		 * 
		 * 
		 * @see Path
		 */
		public Item moveTo(Path p)
		{
			return moveTo(new Folder(p));
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
	
	/**
	 * Returns the OS of the current machine.
	 * 
	 * @return  a machine os
	 * 
	 * 
	 * @see String
	 */
	public static String OS()
	{
		return java.lang.System.getProperty("os.name");
	}
	
	
	private FileSystem()
	{
		// NOT APPLICABLE
	}
}