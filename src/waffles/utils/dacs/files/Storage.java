package waffles.utils.dacs.files;

import waffles.utils.dacs.File;
import waffles.utils.dacs.utilities.files.AccessError;

/**
 * A {@code Storage} defines data which can be read or written to the file system.
 *
 * @author Waffles
 * @since 06 Nov 2023
 * @version 1.0
 */
public interface Storage
{
	/**
	 * Reads the {@code Storage} from a {@code File}.
	 * 
	 * @param file  a source file
	 * @throws AccessError  if the file could not be accessed
	 * 
	 * 
	 * @see AccessError
	 * @see File
	 */
	public abstract void read(File file) throws AccessError;
	
	/**
	 * Writes the {@code Storage} to a {@code File}.
	 * 
	 * @param file  a target file
	 * @throws AccessError  if the file could not be accessed
	 * 
	 * 
	 * @see File
	 */
	public abstract void write(File file) throws AccessError;
	
	
	/**
	 * Reads the {@code Storage} from a {@code File}.
	 * 
	 * @param path  a file path
	 * @throws AccessError  if the file could not be accessed
	 * 
	 * 
	 * @see AccessError
	 */
	public default void read(String path) throws AccessError
	{
		read(new File(path));
	}
	
	/**
	 * Writes the {@code Storage} to a {@code File}.
	 * 
	 * @param url  a target url
	 * @throws AccessError  if the file could not be accessed
	 * 
	 * 
	 * @see AccessError
	 */
	public default void write(String url) throws AccessError
	{
		write(new File(url));
	}
}