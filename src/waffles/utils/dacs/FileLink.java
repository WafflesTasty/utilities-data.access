package waffles.utils.dacs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import waffles.utils.dacs.utilities.errors.AccessError;
import waffles.utils.tools.patterns.semantics.Immutable;

/**
 * A {@code FileLink} defines a symbolic link in the {@code FileSystem}.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see Immutable
 */
@FunctionalInterface
public interface FileLink extends Immutable
{		
	/**
	 * A {@code Mutable FileLink} can be created, moved and deleted.
	 *
	 * @author Waffles
	 * @since 06 Nov 2023
	 * @version 1.0
	 * 
	 * 
	 * @see FileLink
	 */
	public static interface Mutable extends Immutable.Mutable, FileLink
	{
		/**
		 * Creates the {@code FileLink}, if possible.
		 * 
		 * @throws AccessError if the link could not be created.
		 */
		public abstract void create() throws AccessError;
		
		/**
		 * Deletes the {@code FileLink}, if possible.
		 * 
		 * @throws AccessError if the link could not be deleted.
		 */
		public abstract void delete() throws AccessError;
		
		
		/**
		 * Changes the name of the {@code FileLink}.
		 * 
		 * @param name  a file name
		 * @return      a file link
		 */
		public abstract FileLink renameTo(String name);
		
		/**
		 * Copies the {@code FileLink} to a {@code Folder}.
		 * 
		 * @param p  a parent folder
		 * @return   a file link
		 * 
		 * 
		 * @see Folder
		 */
		public abstract FileLink copyTo(Folder p);
		
		
		/**
		 * Moves the {@code FileLink} to a {@code Folder}.
		 * 
		 * @param p  a parent folder
		 * @return   a file link
		 * 
		 * 
		 * @see Folder
		 */
		public default FileLink moveTo(Folder p)
		{
			FileLink move = copyTo(p);
			delete(); return move;
		}
				
		/**
		 * Copies the {@code FileLink} to a {@code Folder}.
		 * 
		 * @param url  a parent url
		 * @return     a file link
		 */
		public default FileLink copyTo(String url)
		{
			return copyTo(Paths.get(url));
		}
		
		/**
		 * Moves the {@code FileLink} to a {@code Folder}.
		 * 
		 * @param url  a parent url
		 * @return     a file link
		 */
		public default FileLink moveTo(String url)
		{
			return moveTo(Paths.get(url));
		}
		
		/**
		 * Copies the {@code FileLink} to a {@code Folder}.
		 * 
		 * @param p  a parent url
		 * @return   a file link
		 * 
		 * 
		 * @see Path
		 */
		public default FileLink copyTo(Path p)
		{
			return copyTo(new Folder(p));
		}
		
		/**
		 * Moves the {@code FileLink} to a {@code Folder}.
		 * 
		 * @param p  a folder path
		 * @return   a file link
		 * 
		 * 
		 * @see Path
		 */
		public default FileLink moveTo(Path p)
		{
			return moveTo(new Folder(p));
		}
	}
	
	
	/**
	 * Returns the path of the {@code FileLink}.
	 * 
	 * @return  an file system path
	 * 
	 * 
	 * @see Path
	 */
	public abstract Path Path();
	
	/**
	 * Checks if the {@code FileLink} already exists.
	 * 
	 * @return  {@code true} if the file exists
	 */
	public default boolean exists()
	{
		return Files.exists(Path());
	}
	
	/**
	 * Returns the name of the {@code FileLink}.
	 * 
	 * @return  a file name
	 * 
	 * 
	 * @see String
	 */
	public default String Name()
	{
		return Path().getFileName().toString();
	}
}