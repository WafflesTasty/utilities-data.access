package waffles.utils.dacs.files.tokens.third.csv;

import waffles.utils.lang.tokens.Token;
import waffles.utils.tools.patterns.properties.counters.Indexable;
import waffles.utils.tools.primitives.Longs;

/**
 * A {@code CSVRow} defines a single row in a {@code CSVFile}.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 *
 * 
 * @see Comparable
 * @see Indexable
 * @see Token
 */
public abstract class CSVRow extends Indexable.Base implements Comparable<CSVRow>, Token
{
	/**
	 * The {@code Type} enum defines all types of a {@code CSVRow}.
	 * Comments are preceeded with a hashtag, with all other lines
	 * separating data with a semicolon. Optionally, the first
	 * line can be used as a column header.
	 * 
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 */
	public static enum Type
	{
		/**
		 * A comment is not used in the csv data model.
		 */
		COMMENT,
		/**
		 * A data defines a single row of csv data.
		 */
		DATA;
	}
		
	/**
 	 * Returns the type of the {@code CSVRow}.
 	 * 
 	 * @return  a line type
 	 */
	public abstract Type Type();
	
	
	@Override
	public int compareTo(CSVRow r)
	{
		return (int) Longs.sign(Index() - r.Index());
	}
}