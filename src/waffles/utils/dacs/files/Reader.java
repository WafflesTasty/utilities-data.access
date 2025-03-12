package waffles.utils.dacs.files;

import waffles.utils.dacs.File;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * A {@code Reader} reads a data object from a {@code File} in the {@code FileSystem}.
 *
 * @author Waffles
 * @since 06 Nov 2023
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 */
@FunctionalInterface
public interface Reader<O>
{
	/**
	 * Reads data from a {@code File}.
	 * 
	 * @param file  a source file
	 * @return  an object read from the file
	 * @throws AccessError  if the file could not be accessed
	 * 
	 * 
	 * @see AccessError
	 * @see File
	 */
	public abstract O read(File file) throws AccessError;
}