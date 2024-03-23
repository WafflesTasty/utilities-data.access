package waffles.utils.dacs.files.tokens.third.toml;

import waffles.utils.dacs.files.tokens.Token.Parser;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLNode;

/**
 * A {@code TOMLParser} defines a possible line parser for a {@code TOMLReader}.
 * This parser generates a {@code Data} object containing a {@code TOMLNode}
 * and an integer representing the node's depth in the {@code TOMLTree}.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 *
 * 
 * @see Parser
 */
public interface TOMLParser extends Parser<TOMLParser.Data>
{
	/**
	 * A {@code TOMLParser.Data} object contains a {@code TOMLNode}
	 * and a node depth. This serves as an intermediate object
	 * when parsing a {@code TOMLTree} from file.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 */
	public static class Data
	{
		private int depth;
		private TOMLNode node;
		
		/**
		 * Creates a new {@code TOMLParser.Data}.
		 * 
		 * @param n  a toml node
		 * @param d  a node depth
		 * 
		 * 
		 * @see TOMLNode
		 */
		public Data(TOMLNode n, int d)
		{
			depth = d;
			node  = n;
		}
		
		/**
		 * Returns the node of the {@code Data}.
		 * 
		 * @return  a toml node
		 * 
		 * 
		 * @see TOMLNode
		 */
		public TOMLNode Node()
		{
			return node;
		}
		
		/**
		 * Returns the depth of the {@code Data}.
		 * 
		 * @return  a node depth
		 */
		public int Depth()
		{
			return depth;
		}
	}
}