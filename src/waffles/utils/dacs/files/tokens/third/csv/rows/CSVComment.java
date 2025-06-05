package waffles.utils.dacs.files.tokens.third.csv.rows;

import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.files.tokens.third.csv.CSVRow;
import waffles.utils.dacs.utilities.parsers.strings.StringGateParser;
import waffles.utils.lang.Format;

/**
 * A {@code CSVComment} defines a {@code CSVRow} representing a comment.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 *
 * 
 * @see CSVRow
 */
public class CSVComment implements CSVRow, Comparable<CSVComment>
{
	/**
	 * Returns a delimiter for a {@code CSVComment}.
	 */
	public static final char DELIMITER = '#';
		
	/**
	 * A {@code CSVComment.Formatter} can format {@code CSVComments}.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 *
	 *
	 * @see CSVComment
	 * @see Format
	 */
	public static class Formatter implements Format<CSVComment>
	{
		@Override
		public String parse(CSVComment cmt)
		{
			return DELIMITER + " " + cmt.Token().Value();
		}
	}
		
	/**
	 * A {@code CSVComment.Parser} parses {@code CSVComments} as string gates.
	 * A comment line can be preceded by whitespace but
	 * must otherwise start with a hashtag #,
	 * i.e. # This is a comment.
	 *
	 * @author Waffles
	 * @since 22 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see StringGateParser
	 * @see CSVComment
	 */
	public static class Parser extends StringGateParser<CSVComment>
	{
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			super(DELIMITER);
		}

		
		@Override
		public CSVComment generate(String cmt)
		{
			return new CSVComment(cmt);
		}
	}


	private int line;
	private StringToken token;
	
	/**
	 * Creates a new {@code CSVComment}.
	 * 
	 * @param s  a comment string
	 */
	public CSVComment(String s)
	{
		setToken(s);
	}
	
	
	/**
	 * Changes the token of the {@code CSVComment}.
	 * 
	 * @param s  a comment string
	 */
	public void setToken(String s)
	{
		String val = s;
		if(s.startsWith("\"") && s.endsWith("\""))
		{
			val = s.substring(1, s.length()-1);
		}
		
		token = new StringToken(val);
	}
	
	/**
	 * Returns the token of the {@code CSVComment}.
	 * 
	 * @return  a string token
	 * 
	 * 
	 * @see StringToken
	 */
	public StringToken Token()
	{
		return token;
	}

	
	/**
	 * Changes the line of the {@code CSVComment}.
	 * 
	 * @param l  a line number
	 */
	public void setLine(int l)
	{
		line = l;
	}
	
	/**
	 * Returns the line of the {@code CSVComment}.
	 * 
	 * @return  a line number
	 */
	public int Line()
	{
		return line;
	}

	
	@Override
	public int compareTo(CSVComment c)
	{
		return Line() - c.Line();
	}
	
	@Override
	public Format<?> Formatter()
	{
		return new Formatter();
	}
	
	@Override
	public Type Type()
	{
		return Type.COMMENT;
	}


}