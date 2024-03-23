package waffles.utils.dacs.files;

import waffles.utils.dacs.File;

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
	 * @param obj   a data object
	 * @param file  a target file
	 * 
	 * 
	 * @see File
	 */
	public abstract void write(O obj, File file);
		
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