package waffles.utils.dacs;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import waffles.utils.sets.utilities.rooted.Hierarchy;

/**
 * The {@code FileSystem} defines static operations that target the host file system.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 */
public final class FileSystem
{
	/**
	 * An {@code Item} defines a single item in the host file system.
	 *
	 * @author Waffles
	 * @since 06 Nov 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Hierarchy
	 * @see FileLink
	 */
	public static abstract class Item extends Hierarchy implements FileLink.Mutable
	{
		private Path path;

		/**
		 * Creates a new {@code Item}.
		 * 
		 * @param url  an item url
		 * 
		 * 
		 * @see String
		 */
		public Item(String url)
		{
			this(Paths.get(url));
		}
		
		/**
		 * Creates a new {@code Item}.
		 * 
		 * @param p  a parent folder
		 * @param n  an item name
		 * 
		 * 
		 * @see String
		 */
		public Item(Folder p, String n)
		{
			this(p.Path().resolve(n));
		}
		
		/**
		 * Creates a new {@code Item}.
		 * 
		 * @param p  an item path
		 * 
		 * 
		 * @see Path
		 */
		public Item(Path p)
		{
			path = p;
		}
				

		@Override
		public boolean equals(Object o)
		{
			if(o instanceof Item)
			{
				Path p = ((Item) o).Path();
				return Path().equals(p);
			}

			return false;
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
		
		@Override
		public int hashCode()
		{
			return Path().hashCode();
		}
				
		@Override
		public Path Path()
		{
			return path;
		}
	}

	/**
	 * The default character set used to interpret {@code Files}.
	 * 
	 * 
	 * @see Charset
	 */
	public static final Charset CHAR_SET = StandardCharsets.ISO_8859_1;
	

	/**
	 * Returns the working {@code Folder} of the current application.
	 * 
	 * @return  a current folder
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
	 * @return  a home folder
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


	FileSystem()
	{
		// NOT APPLICABLE
	}
}