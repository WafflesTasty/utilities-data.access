package waffles.utils.dacs.files.tokens.third.toml.tree.nodes;

import waffles.utils.dacs.files.tokens.third.toml.TOMLParser;
import waffles.utils.dacs.utilities.parsers.strings.StringGateParser;
import waffles.utils.lang.Format;

/**
 * A {@code TOMLComment} defines a {@code TOMLNode} representing a comment line.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 *
 * 
 * @see TOMLNode
 */
public class TOMLComment extends TOMLNode
{
	/**
	 * Returns a delimiter for a {@code TOMLComment}.
	 */
	public static final char DELIMITER = '#';
	
	/**
	 * A {@code TOMLComment.Parser} parses a comment line.
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
	 * @see TOMLParser
	 */
	public static class Parser extends StringGateParser<TOMLParser.Data> implements TOMLParser
	{
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			super(DELIMITER);
		}


		@Override
		public Data generate(String cmt)
		{
			return new Data(new TOMLComment(cmt), 1);
		}
	}
	
	/**
	 * A {@code TOMLComment.Formatter} performs basic parsing for a {@code TOMLComment}.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see TOMLComment
	 * @see Format
	 */
	public static class Formatter extends TOMLNode.Formatter<TOMLComment>
	{
		@Override
		public String parse(TOMLComment c)
		{
			String cmt = c.Key().parse();
			cmt = cmt.substring(1, cmt.length()-1);
			return DELIMITER + " " + cmt;
		}
	}
	
	
	/**
	 * Creates a new {@code Comment}.
	 * 
	 * @param cmt  a comment string
	 */
	public TOMLComment(String cmt)
	{
		super(cmt);
	}

	
	@Override
	public Formatter Formatter()
	{
		return new Formatter();
	}
	
	@Override
	public Type Type()
	{
		return Type.COMMENT;
	}
}