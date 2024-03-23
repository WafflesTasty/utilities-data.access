package waffles.utils.dacs.files.tokens.third.toml.tree.nodes;

import java.util.Iterator;

import waffles.utils.dacs.files.tokens.third.toml.tree.TOMLTree;
import waffles.utils.dacs.utilities.parsers.strings.StringParser;
import waffles.utils.dacs.files.tokens.third.toml.TOMLParser;
import waffles.utils.dacs.files.tokens.third.toml.tree.TOMLData;
import waffles.utils.lang.Strings;

/**
 * A {@code TOMLHeader} defines a header node in a {@code TOMLFile}.
 * In addition to its subnodes, it also defines its own
 * {@code TOMLTree} of key-value pairs.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 *
 * 
 * @see TOMLNode
 */
public class TOMLHeader extends TOMLNode
{		
	/**
	 * A {@code TOMLHeader.Parser} parses a header string.
	 * A header is any string enclosed between two brackets,
	 * i.e. [general]. Any subheader needs to be preceded
	 * by dots representing its depth, i.e. [.sound]
	 * would be a subheader underneath the
	 * previous header.
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
		private StringParser parser;
		
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			parser = new StringParser('[', ']');
		}



		@Override
		public boolean consume(Character s)
		{
			if(parser.Length() == 0)
			{
				if(Character.isWhitespace(s))
					return true;
			}
			
			return parser.consume(s);
		}

		@Override
		public TOMLParser.Data generate()
		{
			String name = parser.generate().trim();
			if(name != null)
			{
				int depth = 1;
				while(name.startsWith("."))
				{
					name = name.substring(1);
					depth++;
				}
				
				TOMLNode node = new TOMLHeader(name);
				return new Data(node, depth);
			}

			return null;
		}
		
		@Override
		public void reset()
		{
			parser.reset();
		}
	}
	
	/**
	 * A {@code TOMLHeader.Formatter} parses a {@code TOMLHeader}.
	 * Its token is written between brackets, and its data
	 * tree is parsed before moving on to its children.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see TOMLNode
	 */
	public static class Formatter extends TOMLNode.Formatter<TOMLHeader>
	{
		@Override
		public Iterator<String> verbose(TOMLHeader node)
		{
			return new Verbose(node);
		}
		
		@Override
		public String parse(TOMLHeader node)
		{
			String key = node.Key().parse();
			if(node.Depth() > 0)
			{
				key = Strings.repeat('.', node.Depth()-1) + key;
			}

			return UPPER + key + LOWER;
		}
	}
	
	/**
	 * A {@code TOMLHeader.Verbose} iterates over the strings
	 * in a verbose {@code TOMLHeader}.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see Iterator
	 */
	public static class Verbose implements Iterator<String>
	{
		private String next;
		private Iterator<String> text;
			
		/**
		 * Creates a new {@code Verbose}.
		 * 
		 * @param node  a toml header
		 * 
		 * 
		 * @see TOMLHeader
		 */
		public Verbose(TOMLHeader node)
		{
			TOMLTree data = node.Data();
			text = data.verbose().iterator();
			next = node.parse();
		}
		
		
		private String findNext()
		{
			if(text.hasNext())
			{
				return text.next();
			}
			
			return null;
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public String next()
		{
			String curr = next;
			next = findNext();
			return curr;
		}
	}

	
	/**
	 * Returns an upper {@code TOMLHeader} delimiter.
	 */
	public static final char UPPER = '[';
	/**
	 * Returns a  lower {@code TOMLHeader} delimiter.
	 */
	public static final char LOWER = ']';
	
	
	private TOMLData data;
	
	/**
	 * Creates a new {@code TOMLHeader}.
	 * 
	 * @param val  a header value
	 */
	public TOMLHeader(String val)
	{
		super(val);	data = new TOMLData();
	}
	
	/**
	 * Returns {@code TOMLHeader} data.
	 * 
	 * @return  a data tree
	 * 
	 * 
	 * @see TOMLData
	 */
	public TOMLData Data()
	{
		return data;
	}
	
	
	@Override
	public TOMLHeader Root()
	{
		return (TOMLHeader) super.Root();
	}
	
	@Override
	public Formatter Formatter()
	{
		return new Formatter();
	}
	
	@Override
	public TOMLHeader Child(int i)
	{
		return (TOMLHeader) super.Child(i);
	}
	
	@Override
	public TOMLHeader Child(String key)
	{
		return (TOMLHeader) super.Child(key);
	}
		
	@Override
	public TOMLHeader Parent()
	{
		return (TOMLHeader) super.Parent();
	}

	@Override
	public Type Type()
	{
		return Type.HEADER;
	}
}
