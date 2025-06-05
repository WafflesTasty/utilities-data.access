package waffles.utils.dacs.utilities.formats;

import waffles.utils.lang.Formattable;

/**
 * A {@code ListFormattable} can parse a list of objects into a string.
 *
 * @author Waffles
 * @since 13 Mar 2024
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see Formattable
 */
@FunctionalInterface
public interface ListFormattable<O extends Formattable> extends Formattable
{
	/**
	 * Defines a default separator for a {@code ListFormattable}.
	 */
	public static final char SEPARATOR = ',';
	/**
	 * Defines a default lower delimiter for a {@code ListFormattable}.
	 */
	public static final char LOWER = '[';
	/**
	 * Defines a default upper delimiter for a {@code ListFormattable}.
	 */
	public static final char UPPER = ']';
	
	
	/**
	 * Returns a list of formattable objects.
	 * 
	 * @return  a format list
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<O> FormatList();
	
	/**
	 * Returns a formatter for the {@code ListFormattable}.
	 * 
	 * @param isCompact  single line or multi-line
	 * @param upper  upper character of the list
	 * @param delim  delimiter between list objects
	 * @param lower  lower character of the list
	 * @return  a list formatter
	 * 
	 * 
	 * @see ListFormat
	 */
	public default ListFormat<O> Formatter(boolean isCompact, char upper, char delim, char lower)
	{
		return new ListFormat<>(isCompact, upper, delim, lower);
	}

	/**
	 * Returns a formatter for the {@code ListFormattable}.
	 * 
	 * @param upper  upper character of the list
	 * @param delim  delimiter between list objects
	 * @param lower  lower character of the list
	 * @return  a list formatter
	 * 
	 * 
	 * @see ListFormat
	 */
	public default ListFormat<O> Formatter(char upper, char delim, char lower)
	{
		return Formatter(true, upper, delim, lower);
	}
	
	/**
	 * Returns a formatter for the {@code ListFormattable}.
	 * 
	 * @param upper  upper character of the list
	 * @param lower  lower character of the list
	 * @return  a list formatter
	 * 
	 * 
	 * @see ListFormat
	 */
	public default ListFormat<O> Formatter(char upper, char lower)
	{
		return Formatter(upper, SEPARATOR, lower);
	}
	
	
	@Override
	public default ListFormat<O> Formatter()
	{
		return Formatter(LOWER, UPPER);
	}
}
