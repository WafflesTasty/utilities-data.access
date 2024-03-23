package waffles.utils.dacs.files.tokens.third.toml;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.files.plaintext.strings.StringReader;
import waffles.utils.dacs.files.tokens.third.toml.TOMLParser.Data;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLComment;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLHeader;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLNode;
import waffles.utils.dacs.files.tokens.third.toml.tree.nodes.TOMLPair;
import waffles.utils.dacs.utilities.parsers.choice.ChoiceParser;
import waffles.utils.lang.Strings;

/**
 * A {@code TOMLReader} reads {@code TOMLHeaders} from a {@code File}.
 * These serve as the root nodes for a {@code TOMLFile}.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 *
 * 
 * @see TOMLHeader
 * @see Reader
 */
public class TOMLReader implements Reader<TOMLHeader>
{
	/**
	 * A {@code TOMLReader.Parser} performs parsing for a {@code TOMLReader}.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see ChoiceParser
	 * @see TOMLParser
	 */
	public static class Parser extends ChoiceParser<TOMLParser.Data>
	{
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			add(new TOMLComment.Parser());
			add(new TOMLHeader.Parser());
			add(new TOMLNode.Parser());
			add(new TOMLPair.Parser());
		}
		
		@Override
		public void reset()
		{
//			Parse an initial space to load
//			TOMLComment as the initial
//			parser target.			
			super.reset();
			consume(' ');
		}
	}
	
	
	private Parser parser;
	private StringReader reader;
	
	@Override
	public TOMLHeader read(File file)
	{
		parser = new Parser();
		reader = new StringReader();

		TOMLHeader header = new TOMLHeader("");
		TOMLNode value = header.Data().Root();
		
		for(String s : reader.read(file))
		{
			parser.reset();
			for(char c : Strings.iterate(s))
			{
				parser.consume(c);
			}
			
			Data data = parser.generate();
			TOMLNode node = data.Node();
			int depth = data.Depth();
			
			switch(node.Type())
			{
			case HEADER:
			{
				while(depth < header.Depth() + 1)
				{
					header = header.Parent();
				}
				
				header.addChild(node);
				header = (TOMLHeader) node;
				value = header.Data().Root();
				break;
			}
			case KEY:
			case VALUE:
			{
				while(depth <= value.Depth() + 1)
				{
					value = value.Parent();
				}
				
				value.addChild(node);
				value = node;
			}
			case COMMENT:
			default:
				break;
			}
		}
		
		return header.Root();
	}
}