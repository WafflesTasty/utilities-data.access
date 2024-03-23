package waffles.utils.dacs.utilities.formats;

import waffles.utils.lang.Format;
import waffles.utils.lang.Formattable;
import waffles.utils.lang.Strings;

/**
 * A {@code ListFormat} defines a formatter for an {@code Iterable}.
 *
 * @author Waffles
 * @since 13 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see ListFormattable
 * @see Formattable
 * @see Iterable
 * @see Format
 */
public class ListFormat<O extends Formattable> implements Format<ListFormattable<O>>
{
	private boolean isCompact;
	private char upper, delim, lower;

	/**
	 * Creates a new {@code ListFormat}.
	 * 
	 * @param upper  upper character of the list
	 * @param lower  lower character of the list
	 */
	public ListFormat(char upper, char lower)
	{
		this(upper, ',', lower);
	}
	
	/**
	 * Creates a new {@code ListFormat}.
	 * 
	 * @param upper  upper character of the list
	 * @param delim  delimiter between list objects
	 * @param lower  lower character of the list
	 */
	public ListFormat(char upper, char delim, char lower)
	{
		this(true, upper, delim, lower);
	}

	/**
	 * Creates a new {@code ListFormat}.
	 * 
	 * @param isCompact  single line or multi-line
	 * @param upper  upper character of the list
	 * @param delim  delimiter between list objects
	 * @param lower  lower character of the list
	 */
	public ListFormat(boolean isCompact, char upper, char delim, char lower)
	{
		this.isCompact = isCompact;
		this.upper = upper;
		this.delim = delim;
		this.lower = lower;
	}
	
	
	@Override
	public String parse(ListFormattable<O> set)
	{
		String text = "";
		text += upper + newLine();

		boolean isEmpty = true;
		for(O obj : set.FormatList())
		{
			if(!isEmpty)
			{
				text += delim + newLine();
			}
			
			text += obj.parse() + " ";
			isEmpty = false;
		}

		text += lower + newLine();
		return text;
	}
	
	private String newLine()
	{
		return isCompact ? " " : Strings.newLine();
	}
}