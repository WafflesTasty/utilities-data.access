package waffles.utils.dacs.files.tokens.toml.tree.nodes;

import waffles.utils.dacs.files.tokens.toml.TOMLParser.Data;
import waffles.utils.lang.tokens.parsers.basic.strings.GatedParser;

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
	 * A {@code TOMLComment.Parser} parses a {@code TOMLComment}.
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
	 * @see Data
	 */
	public static class Parser extends GatedParser<Data>
	{
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			super(DELIMITER);
		}


		@Override
		public Data compute(String cmt)
		{
			return new Data(new TOMLComment(cmt), 1);
		}
	}
	
	/**
	 * A {@code TOMLComment.Formatter} formats a {@code TOMLComment}.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see TOMLNode
	 */
	public static class Formatter extends TOMLNode.Formatter<TOMLComment>
	{
		@Override
		public String parse(TOMLComment c)
		{
			String cmt = c.Key().condense();
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