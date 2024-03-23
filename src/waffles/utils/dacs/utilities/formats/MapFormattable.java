package waffles.utils.dacs.utilities.formats;

import waffles.utils.dacs.files.tokens.objects.TokenPair;
import waffles.utils.lang.Formattable;

/**
 * A {@code MapFormattable} can parse a list of map pairs into a string.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 *
 *
 * @param <P>  a pair type
 * @see ListFormattable
 * @see Formattable
 */
@FunctionalInterface
public interface MapFormattable<P extends TokenPair<?,?>> extends ListFormattable<P>
{
	// NOT APPLICABLE
}