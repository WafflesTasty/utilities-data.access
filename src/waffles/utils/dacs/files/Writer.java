package waffles.utils.dacs.files;

import waffles.utils.dacs.File;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * A {@code Writer} writes a data object to a {@code File} in the {@code FileSystem}.
 *
 * @author Waffles
 * @since 06 Nov 2023
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 */
@FunctionalInterface
public interface Writer<O>
{
	/**
	 * Writes data to a {@code File}.
	 * 
	 * @param obj   an object to write to file
	 * @param file  a target file
	 * @throws AccessError  if the file could not be accessed
	 * 
	 * 
	 * @see File
	 */
	public abstract void write(O obj, File file) throws AccessError;
		
	/**
	 * Writes data to a {@code File}.
	 * 
	 * @param obj   a data object
	 * @param url  a target url
	 */
	public default void write(O obj, String url)
	{
		write(obj, new File(url));
	}
}