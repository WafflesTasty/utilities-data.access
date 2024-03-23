package waffles.utils.dacs.files.tokens.third.toml.tree.nodes;

import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.files.tokens.objects.TokenPair;
import waffles.utils.dacs.files.tokens.third.toml.TOMLParser;
import waffles.utils.dacs.utilities.parsers.objects.PairParser;
import waffles.utils.dacs.utilities.parsers.tokens.StringTokenParser;

/**
 * A {@code TOMLPair} defines a key-value pair in a {@code TOMLFile}.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 * 
 * 
 * @see StringToken
 * @see TokenPair
 * @see TOMLNode
 */
public class TOMLPair extends TOMLNode implements TokenPair<StringToken, StringToken>
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
	 * A {@code TOMLPair.Parser} parses a key-value node.
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
	 * @see TOMLParser
	 */
	public static class Parser implements TOMLParser
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
		private DemiParser parser;
		
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			parser = new DemiParser();
			state = State.INITIAL;
			depth = 1;
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
			}
			case PAIR:
			{
				if(parser.consume(s))
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
		public Data generate()
		{
			TOMLPair pair = parser.generate();
			if(pair != null)
			{
				return new Data(pair, depth);
			}
			
			return null;
		}
		
		@Override
		public void reset()
		{
			state = State.INITIAL;
			parser.reset();
			depth = 1;
		}
	}
	
	/**
	 * A {@code TOMLPair.DemiParser} parses the intermediate
	 * result for a {@code TOMLPair.Parser}. It otherwise
	 * performs as a basic {@code PairParser}.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see PairParser
	 * @see TOMLPair
	 */
	public static class DemiParser extends PairParser<TOMLPair>
	{
		/**
		 * Creates a new {@code DemiParser}.
		 */
		public DemiParser()
		{
			super(SEPARATOR);
		}

		
		@Override
		public StringTokenParser<?> KeyParser()
		{
			return new StringTokenParser.Base();
		}
		
		@Override
		public TOMLPair generate(Object k, Object v)
		{
			StringToken key = (StringToken) k;
			StringToken val = (StringToken) v;
			return new TOMLPair(key, val);
		}
		
		@Override
		public StringTokenParser<?> ValueParser()
		{
			return new StringTokenParser.Base();
		}
	}
	
	/**
	 * A {@code TOMLPair.Formatter} parses a {@code TOMLPair}.
	 * Its key-value pair is separated by a colon.
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
			return super.parse(node) + val.parse();
		}
	}
	
	
	private StringToken value;
	
	/**
	 * Creates a new {@code TOMLPair}.
	 * 
	 * @param key  a key string
	 * @param val  an object value
	 */
	public TOMLPair(String key, Object val)
	{
		this(key); setValue(val);
	}
	
	/**
	 * Creates a new {@code TOMLPair}.
	 * 
	 * @param key  a key token
	 * @param val  a value token
	 * 
	 * 
	 * @see StringToken
	 */
	public TOMLPair(StringToken key, StringToken val)
	{
		super(key); value = val;
	}
		
	/**
	 * Creates a new {@code TOMLPair}.
	 * 
	 * @param key  a key string
	 */
	public TOMLPair(String key)
	{
		super(key);	
	}
	

	/**
	 * Changes the value of the {@code TOMLPair}.
	 * 
	 * @param val  an object value
	 */
	public void setValue(Object val)
	{
		value = new StringToken(val);
	}
	
	/**
	 * Changes the value of the {@code TOMLPair}.
	 * 
	 * @param val  a string value
	 */
	public void setValue(String val)
	{
		setValue((Object) val);
	}
	
	/**
	 * Changes the value of the {@code TOMLPair}.
	 * 
	 * @param val  a boolean value
	 */
	public void setValue(boolean val)
	{
		setValue((Object) val);
	}
	
	/**
	 * Changes the value of the {@code TOMLPair}.
	 * 
	 * @param val  a float value
	 */
	public void setValue(float val)
	{
		setValue((Object) val);
	}
	
	/**
	 * Changes the value of the {@code TOMLPair}.
	 * 
	 * @param val  an int value
	 */
	public void setValue(int val)
	{
		setValue((Object) val);
	}

	
	@Override
	public Formatter Formatter()
	{
		return new Formatter();
	}

	@Override
	public StringToken Value()
	{
		return value;
	}
	
	@Override
	public Type Type()
	{
		return Type.VALUE;
	}

}