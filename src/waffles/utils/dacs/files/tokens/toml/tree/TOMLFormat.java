package waffles.utils.dacs.files.tokens.toml.tree;

import java.util.Iterator;

import waffles.utils.dacs.files.tokens.toml.tree.nodes.TOMLNode;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.sets.utilities.rooted.iterators.DepthFirst;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code TOMLFormat} parses a {@code TOMLTree} into a set of strings.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 * 
 * 
 * @see TOMLTree
 * @see Format
 */
public class TOMLFormat implements Format<TOMLTree>
{
	/**
	 * A {@code TOMLFormat.Descriptor} iterates over the
	 * nodes in a {@code TOMLTree} in a depth-first manner,
	 * performing descriptive parsing on each in turn.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see Iterator
	 */
	public class Descriptor implements Iterator<String>
	{
		private String next;
		private Iterator<String> text;
		private Iterator<TOMLNode> nodes;
		
		/**
		 * Creates a new {@code Descriptor}.
		 * 
		 * @param node  a root node
		 * 
		 * 
		 * @see TOMLNode
		 */
		public Descriptor(TOMLNode node)
		{
			nodes = new DepthFirst<>(node);
			text = new EmptyIterator<>();
//			Skip the root string.
			next = findNext();
			next = findNext();
		}

		
		private String findNext()
		{
			if(text.hasNext())
			{
				return text.next();
			}
			
			if(nodes.hasNext())
			{
				TOMLNode node = nodes.next();
				text = node.describe().iterator();
				return findNext();
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
	
	
	@Override
	public Iterable<String> describe(TOMLTree t)
	{
		return () -> new Descriptor(t.Root());
	}
	
	@Override
	public String parse(TOMLTree t)
	{
		return t.Root().condense();
	}
}