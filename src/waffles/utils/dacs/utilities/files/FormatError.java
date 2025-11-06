package waffles.utils.dacs.utilities.files;

/**
 * A {@code FormatError} is thrown whenever a file format is violated.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see RuntimeException
 */
public class FormatError extends RuntimeException
{
	private static final long serialVersionUID = 671754289874248164L;
	

	/**
	 * Creates a new {@code FormatError}.
	 * 
	 * @param desc  a description text
	 */
	public FormatError(String desc)
	{
		super(desc);
	}
}