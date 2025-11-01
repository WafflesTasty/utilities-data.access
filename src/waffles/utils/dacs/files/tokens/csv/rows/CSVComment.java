package waffles.utils.dacs.files.tokens.csv.rows;

import waffles.utils.dacs.files.tokens.csv.CSVRow;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.parsers.basic.strings.GatedParser;
import waffles.utils.lang.tokens.primitive.StringToken;

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
public class CSVComment extends CSVRow
{
	/**
	 * Defines a delimiter for a {@code CSVComment}.
	 */
	public static final char DELIMITER = '#';
		
	/**
	 * A {@code Formatter} parses a {@code CSVComment} into a string.
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
	 * A {@code Parser} parses a {@code CSVComment} as a string gate.
	 * A comment line can be preceded by whitespace but
	 * must otherwise start with a hashtag #,
	 * i.e. # This is a comment.
	 *
	 * @author Waffles
	 * @since 22 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see GatedParser
	 * @see CSVComment
	 */
	public static class Parser extends GatedParser<CSVComment>
	{
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			super(DELIMITER);
		}

		
		@Override
		public CSVComment compute(String cmt)
		{
			return new CSVComment(cmt);
		}
	}


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
	 * Changes the {@code CSVComment} token.
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
	 * Returns the {@code CSVComment} token.
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