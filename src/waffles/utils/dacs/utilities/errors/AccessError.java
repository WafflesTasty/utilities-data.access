package waffles.utils.dacs.utilities.errors;

import java.nio.file.Path;

import waffles.utils.dacs.File;

/**
 * An {@code AccessError} is thrown whenever a file path cannot be accessed.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see RuntimeException
 */
public class AccessError extends RuntimeException
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
		super("I couldn't access this path: " + p.toString() + ".");
	}
}