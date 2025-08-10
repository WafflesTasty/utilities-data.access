package waffles.utils.dacs.files.tokens.third.toml.tree;

import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLNode;
import waffles.utils.lang.tokens.Token;
import waffles.utils.sets.trees.Tree;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code TOMLTree} defines the data structure of a simple {@code TOML} document.
 * Each {@code TOMLNode} in the tree defines a subsection in {@code TOML} format.
 * In a deliciously confusing twist, each subsection defines its own internal
 * {@code TOMLTree}, which contains the actual key-value pairs of the
 * {@code TOML} format, subdivided into groups.
 * 
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 *
 * 
 * @see Token
 * @see Tree
 */
public class TOMLTree extends Tree implements Token
{
	/**
	 * Returns a child of the {@code TOMLTree}.
	 * 
	 * @param keys  a set of keys
	 * @return  a child node
	 */
	public TOMLNode Child(String... keys)
	{
		TOMLNode node = Root();
		
		String[] set = Array.copy.of(keys);
		while(set.length > 0)
		{
			node = node.Child(set[0]);
			set = Array.remove.from(set, 0);
			if(node == null)
			{
				break;
			}
		}
		
		return node;
	}
	
	
	@Override
	public TOMLFormat Formatter()
	{
		return new TOMLFormat();
	}

	@Override
	public TOMLNode Root()
	{
		return (TOMLNode) super.Root();
	}
	
	@Override
	public int Count()
	{
		return Root().Arch().TreeSize();
	}
}