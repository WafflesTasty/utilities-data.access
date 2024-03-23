package waffles.utils.dacs.files.tokens.third.toml.tree;

import java.util.Iterator;

import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLNode;
import waffles.utils.lang.Format;
import waffles.utils.sets.trees.traversal.DepthFirst;
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
	 * The {@code TOMLFormat.Verbose} class iterates over the
	 * nodes in a {@code TOMLTree} in a depth-first manner,
	 * performing verbose parsing on each in turn.
	 *
	 * @author Waffles
	 * @since 21 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see Iterator
	 */
	public class Verbose implements Iterator<String>
	{
		private String next;
		private Iterator<String> text;
		private Iterator<TOMLNode> nodes;
		
		/**
		 * Creates a new {@code Verbose}.
		 * 
		 * @param node  a root node
		 * 
		 * 
		 * @see TOMLNode
		 */
		public Verbose(TOMLNode node)
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
				text = node.verbose().iterator();
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
	public Iterator<String> verbose(TOMLTree tree)
	{
		return new Verbose(tree.Root());
	}
	
	@Override
	public String parse(TOMLTree tree)
	{
		return tree.Root().parse();
	}
}