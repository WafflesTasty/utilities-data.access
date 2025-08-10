package waffles.utils.dacs.files.tokens.third.toml;

import waffles.utils.dacs.files.tokens.parsers.ChoiceParser;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLComment;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLHeader;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLNode;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLPair;

/**
 * A {@code TOMLParser} defines the top-level parser for a {@code TOMLReader}.
 * This is implemented as a {@code ChoiceParser} with the following options.
 * <ul>
 *   <li>{@code TOMLComment.Parser}</li> 
 *   <li>{@code TOMLHeader.Parser}</li> 
 *   <li>{@code TOMLNode.Parser}</li> 
 *   <li>{@code TOMLPair.Parser}</li>
 * </ul>
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 *
 * 
 * @see ChoiceParser
 * @see TOMLHeader
 */
public class TOMLParser extends ChoiceParser<TOMLParser.Data, TOMLHeader>
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
	
	
	private TOMLNode data;
	private TOMLHeader head;
	
	/**
	 * Creates a new {@code TOMLParser}.
	 */
	public TOMLParser()
	{
		head = new TOMLHeader("");
		data = head.Data().Root();
		
		add(new TOMLComment.Parser());
		add(new TOMLHeader.Parser());
		add(new TOMLNode.Parser());
		add(new TOMLPair.Parser());
	}
	
	
	@Override
	public TOMLHeader compute(Data d)
	{
		int depth = d.Depth();
		TOMLNode node = d.Node();
		
		switch(node.Type())
		{
		case HEADER:
		{
			while(depth < head.Depth() + 1)
			{
				head = head.Parent();
			}
			
			head.addChild(node);
			head = (TOMLHeader) node;
			data = head.Data().Root();
			break;
		}
		case KEY:
		case VALUE:
		{
			while(depth < data.Depth() + 1)
			{
				data = data.Parent();
			}
			
			data.addChild(node);
			data = node;
		}
		case COMMENT:
		default:
			break;
		}
		
		return head.Root();
	}
}