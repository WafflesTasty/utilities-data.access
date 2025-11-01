package waffles.utils.dacs.files.tokens.toml.tree;

import waffles.utils.dacs.files.tokens.toml.tree.nodes.TOMLNode;
import waffles.utils.dacs.files.tokens.toml.tree.nodes.TOMLPair;

/**
 * The {@code TOMLData} class defines a TOML value tree
 * contained within a single {@code TOMLHeader}.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 * 
 * 
 * @see TOMLTree
 */
public class TOMLData extends TOMLTree
{
	/**
	 * Creates a new {@code TOMLData}.
	 */
	public TOMLData()
	{
		setRoot(new TOMLNode(""));
	}
	
	/**
	 * Changes a value in the {@code TOMLData}.
	 * 
	 * @param val   an object value
	 * @param keys  a key set
	 */
	public void set(Object val, String... keys)
	{
		TOMLNode node = Root();
		for(int i = 0; i < keys.length; i++)
		{
			TOMLNode child = node.Child(keys[i]);
			if(child != null)
			{
				if(i == keys.length-1)
				{
					TOMLPair pair = (TOMLPair) child;
					pair.setValue(val);
					return;
				}

				node = child;
				continue;
			}
			
			if(i == keys.length-1)
				child = new TOMLPair(keys[i], val);
			else
				child = new TOMLNode(keys[i]);
			
			node.addChild(child);
			node = child;
		}
	}
	
	/**
	 * Returns a value in the {@code TOMLData}.
	 * 
	 * @param keys  a key set
	 * @return  a data tree
	 * 
	 * 
	 * @see TOMLData
	 */
	public <O> O get(String... keys)
	{
		TOMLNode node = Root();
		for(int i = 0; i < keys.length; i++)
		{
			node = node.Child(keys[i]);
			if(node == null)
			{
				return null;
			}
		}
		
		TOMLPair pair = (TOMLPair) node;
		return pair.Value().Value();
	}
}