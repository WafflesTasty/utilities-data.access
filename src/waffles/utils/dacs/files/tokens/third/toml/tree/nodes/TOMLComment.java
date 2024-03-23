package waffles.utils.dacs.files.tokens.third.toml.tree.nodes;

import waffles.utils.dacs.files.tokens.third.toml.TOMLParser;
import waffles.utils.dacs.utilities.parsers.strings.AnyParser;

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
	 * @see TOMLParser
	 */
	public static class Parser implements TOMLParser
	{
		private static enum State
		{
			INITIAL,
			FINAL,
			FAIL;
		}
		
		
		private State state;
		private AnyParser any;

		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			state = State.INITIAL;
			any = new AnyParser();
		}
		
		
		@Override
		public boolean consume(Character s)
		{
			switch(state)
			{
			case INITIAL:
			{
				if(Character.isWhitespace(s))
					return true;
				if(s == DELIMITER)
				{
					state = State.FINAL;
					return true;
				}
				
				state = State.FAIL;
				return false;
			}
			case FINAL:
				return any.consume(s);
			case FAIL:
			default:
				return false;
			}
		}

		@Override
		public TOMLParser.Data generate()
		{
			String cmt = any.generate();
			TOMLNode node = new TOMLComment(cmt);
			return new Data(node, 1);
		}
		
		@Override
		public void reset()
		{
			state = State.INITIAL;
			any = new AnyParser();
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
	public Type Type()
	{
		return Type.COMMENT;
	}
}