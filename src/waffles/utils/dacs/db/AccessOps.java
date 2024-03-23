package waffles.utils.dacs.db;

/**
 * An {@code AccessOps} enum defines a file access hint.
 * 
 * @author Waffles
 * @since Sep 12, 2016
 * @version 1.0
 */
public enum AccessOps
{
	/**
	 * The file is used for copy operations.
	 */
	COPY_WRITE,
	/**
	 * The file is used for write operations.
	 */
	READ_WRITE,
	/**
	 * The file is used for read operations.
	 */
	READ_ONLY;
}