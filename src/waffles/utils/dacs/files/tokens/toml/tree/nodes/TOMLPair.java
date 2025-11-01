package waffles.utils.dacs.files.tokens.toml.tree.nodes;

import waffles.utils.dacs.files.tokens.toml.TOMLParser.Data;
import waffles.utils.lang.tokens.PairToken;
import waffles.utils.lang.tokens.parsers.basic.PairParser;
import waffles.utils.lang.tokens.primitive.StringToken;

/**
 * A {@code TOMLPair} defines a single key-value pair for a {@code TOMLFile}.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 * 
 * 
 * @see PairToken
 * @see StringToken
 * @see TOMLNode
 */
public class TOMLPair extends TOMLNode implements PairToken<StringToken, StringToken>
{
	/**
	 * Defines a default delimiter for a {@code TOMLPair}.
	 */
	public static final char DELIMITER = '.';
	/**
	 * Defines a default separator for a {@code TOMLPair}.
	 */
	public static final char SEPARATOR = ':';
	
			
	/**
	 * A {@code TOMLPair.Formatter} formats a {@code TOMLPair}.
	 * A {@code TOMLPair} has a key-value separated by a colon.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see TOMLNode
	 * @see TOMLPair
	 */
	public static class Formatter extends TOMLNode.Formatter<TOMLPair>
	{
		@Override
		public String parse(TOMLPair node)
		{
			StringToken val = node.Value();
			return super.parse(node) + val.condense();
		}
	}
	
	/**
	 * A {@code TOMLPair.Parser} parses a {@code TOMLPair}.
	 * These values can be any literal or string separated by
	 * a colon, i.e. vsync : true. Any key needs to be preceded
	 * by dots indicating its depth, i.e. .sound = 24 would
	 * be a subsetting underneath the previous setting.
	 *
	 * @author Waffles
	 * @since 22 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see PairParser
	 * @see Data
	 */
	public static class Parser extends PairParser<Data>
	{
		static enum State
		{
			INITIAL,
			DELIMIT,
			PAIR,
			FAIL,
			DONE;
		}
		
		
		private int depth;
		private State state;
		private StringToken.Parser key;
		private StringToken.Parser val;
		
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			super(SEPARATOR);
			state = State.INITIAL;
			depth = 1;
		}

		
		@Override
		public Data generate()
		{
			StringToken k = key.generate();
			StringToken v = val.generate();
			
			if(key != null && val != null)
			{
				TOMLPair p = new TOMLPair(k, v);
				return new Data(p, depth);
			}
			
			return null;
		}
		
		@Override
		public StringToken.Parser KeyParser()
		{
			if(key == null)
				key = new StringToken.Parser();
			return key;
		}
		
		@Override
		public StringToken.Parser ValueParser()
		{
			if(val == null)
				val = new StringToken.Parser();
			return val;
		}
		
		@Override
		public boolean consume(Character s)
		{
			switch(state)
			{
			case INITIAL:
			{
				if(Character.isWhitespace(s))
				{
					depth++;
					return true;
				}
				
				depth = 1 + depth / 2;
				state = State.DELIMIT;
			}
			case DELIMIT:
			{
				state = State.PAIR;
				if(s == DELIMITER)
					return true;
				else
					depth = 1;
			}
			case PAIR:
			{
				if(super.consume(s))
					return true;
				
				state = State.DONE;
			}
			case FAIL:
			case DONE:
			default:
				return false;
			}
		}
				
		@Override
		public void reset()
		{
			super.reset();
			state = State.INITIAL;
			depth = 1;
		}
	}
	
	
	private StringToken val;
		
	/**
	 * Creates a new {@code TOMLPair}.
	 * 
	 * @param k  a pair key
	 * @param v  a pair val
	 * 
	 * 
	 * @see StringToken
	 */
	public TOMLPair(StringToken k, StringToken v)
	{
		super(k); val = v;
	}
	
	/**
	 * Creates a new {@code TOMLPair}.
	 * 
	 * @param k  a pair key
	 * @param v  a pair val
	 */
	public TOMLPair(String k, Object v)
	{
		super(k); setValue(v);
	}
	
	/**
	 * Creates a new {@code TOMLPair}.
	 * 
	 * @param k  a pair key
	 */
	public TOMLPair(String k)
	{
		this(k, null);	
	}
	

	/**
	 * Changes the val of the {@code TOMLPair}.
	 * 
	 * @param v  an object val
	 */
	public void setValue(Object v)
	{
		val = new StringToken(v);
	}
	
	/**
	 * Changes the val of the {@code TOMLPair}.
	 * 
	 * @param v  a string val
	 */
	public void setValue(String v)
	{
		setValue((Object) v);
	}
	
	/**
	 * Changes the val of the {@code TOMLPair}.
	 * 
	 * @param v  a boolean val
	 */
	public void setValue(boolean v)
	{
		setValue((Object) v);
	}
	
	/**
	 * Changes the val of the {@code TOMLPair}.
	 * 
	 * @param v  a float val
	 */
	public void setValue(float v)
	{
		setValue((Object) v);
	}
	
	/**
	 * Changes the val of the {@code TOMLPair}.
	 * 
	 * @param v  an int val
	 */
	public void setValue(int v)
	{
		setValue((Object) v);
	}

	
	@Override
	public Formatter Formatter()
	{
		return new Formatter();
	}

	@Override
	public StringToken Value()
	{
		return val;
	}
	
	@Override
	public Type Type()
	{
		return Type.VALUE;
	}
}