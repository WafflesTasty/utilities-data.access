package waffles.utils.dacs.files.tokens.third.toml.tree.nodes;

import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.files.tokens.parsers.primitive.choice.StringParser;
import waffles.utils.dacs.files.tokens.third.toml.TOMLParser.Data;
import waffles.utils.dacs.files.tokens.third.toml.tree.TOMLTree;
import waffles.utils.lang.Strings;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.sets.trees.Nodal;
import waffles.utils.sets.trees.Node;

/**
 * A {@code TOMLNode} defines a single node in a {@code TOMLTree}.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 *
 * 
 * @see Token
 * @see Node
 */
public class TOMLNode extends Node implements Token
{
	/**
	 * Defines a default delimiter for a {@code TOMLNode}.
	 */
	public static final char DELIMITER = '.';
	/**
	 * Defines a default separator for a {@code TOMLNode}.
	 */
	public static final char SEPARATOR = ':';
	
		
	/**
	 * A {@code TOMLNode.Formatter} formats a {@code TOMLNode} into a string.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 *
	 *
	 * @param <O>  a node type
	 * @see TOMLNode
	 * @see Format
	 */
	public static class Formatter<O extends TOMLNode> implements Format<O>
	{
		@Override
		public String parse(O node)
		{
			String key = "";
			if(node.Depth() > 1)
			{
				key = Strings.repeat(' ', 2 * node.Depth() - 2) + key;
				key = Strings.replaceLast(key, " ", "" + DELIMITER);
			}
			
			key += node.Key().condense();
			key += " " + SEPARATOR + " ";
			return key;
		}
	}
	
	/**
	 * A {@code TOMLNode.Parser} parses a {@code TOMLNode} to a string.
	 * A key is any literal or string followed by a colon, i.e.
	 * settings : . Any key needs to be preceded by dots
	 * representing its depth, i.e. .sound would
	 * be a subsetting underneath the
	 * previous setting.
	 *
	 * @author Waffles
	 * @since 22 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see StringParser
	 * @see Data
	 */
	public static class Parser extends StringParser<Data>
	{
		static enum State
		{
			INITIAL,
			DELIMIT,
			TOKEN,
			SEPAR,
			FAIL,
			DONE;
		}
		
		private int depth;
		private State state;
		
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			state = State.INITIAL;
			depth = 0;
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
				state = State.TOKEN;
				if(s == DELIMITER)
					return true;
			}
			case TOKEN:
			{
				if(super.consume(s))
					return true;
				
				state = State.SEPAR;
			}
			case SEPAR:
			{
				if(Character.isWhitespace(s))
					return true;
				if(s == SEPARATOR)
				{
					state = State.DONE;
					return true;
				}
				
				state = State.FAIL;
			}
			case DONE:
			{
				if(Character.isWhitespace(s))
					return true;
				return false;
			}
			case FAIL:
			default:
				return false;
			}
		}

		@Override
		public Data compute(Object obj)
		{
			if(state == State.DONE)
			{
				String name = obj.toString();
				TOMLNode node = new TOMLNode(name);
				return new Data(node, depth);
			}
			
			return null;
		}
		
		@Override
		public void reset()
		{
			super.reset();
			state = State.INITIAL;
			depth = 0;
		}
	}
	
	/**
	 * The {@code TOMLNode.Type} enum defines all four TOML types.
	 * All keys and values in a {@code TOMLNode} can take any of the
	 * types null, boolean, Number, or String. Strings can either be
	 * literals containing no whitespace, or general strings with
	 * whitespace, which are parsed to file between quotes.
	 * 
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 */
	public static enum Type
	{
		/**
		 * A comment is not used in the toml data model.
		 */
		COMMENT,
		/**
		 * A header node defines a subsection of parameter groups.
		 */
		HEADER,
		/**
		 * A key-value pair defines a single parameter value.
		 */
		VALUE,
		/**
		 * A key node defines a parameter group.
		 */
		KEY;
	}
	
	
	private StringToken key;
	
	/**
	 * Creates a new {@code TOMLNode}.
	 * 
	 * @param k  a key value
	 */
	public TOMLNode(String k)
	{
		setKey(k);
	}

	/**
	 * Creates a new {@code TOMLNode}.
	 * 
	 * @param k  a key token
	 */
	public TOMLNode(StringToken k)
	{
		key = k;
	}
	
	/**
	 * Returns a child of the {@code TOMLNode}.
	 * 
	 * @param key  a key string
	 * @return  a child node
	 * 
	 * 
	 * @see TOMLNode
	 */
	public TOMLNode Child(String key)
	{
		for(Nodal c : Children())
		{
			TOMLNode child = (TOMLNode) c;
			Object obj = child.Key().Value();
			if(key.equals(obj.toString()))
			{
				return child;
			}
		}
		
		return null;
	}
		
	/**
	 * Changes the key of the {@code TOMLNode}.
	 * 
	 * @param k  a key value
	 */
	public void setKey(String k)
	{
		String val = k;
		if(k.startsWith("\"") && k.endsWith("\""))
		{
			val = k.substring(1, k.length()-1);
		}
		
		key = new StringToken(val);
	}
	
	/**
	 * Returns the key of the {@code TOMLNode}.
	 * 
	 * @return  a key token
	 * 
	 * 
	 * @see StringToken
	 */
	public StringToken Key()
	{
		return key;
	}
 	
 	/**
 	 * Returns the  {@code TOMLNode} type.
 	 * 
 	 * @return  a node type
 	 */
	public Type Type()
	{
		return Type.KEY;
	}
	

	@Override
	public Formatter<?> Formatter()
	{
		return new Formatter<>();
	}

	@Override
	public TOMLNode Child(int i)
	{
		return (TOMLNode) super.Child(i);
	}
	
	@Override
	public TOMLNode Parent()
	{
		return (TOMLNode) super.Parent();
	}
	
	@Override
	public TOMLNode Root()
	{
		return (TOMLNode) super.Root();
	}
	
	@Override
	public TOMLTree Set()
	{
		return (TOMLTree) super.Set();
	}
}