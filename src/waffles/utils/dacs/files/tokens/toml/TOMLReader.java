package waffles.utils.dacs.files.tokens.toml;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.files.plaintext.strings.StringReader;
import waffles.utils.dacs.files.tokens.toml.tree.nodes.TOMLHeader;
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
	private TOMLParser parser;
	private StringReader reader;
	
	@Override
	public TOMLHeader read(File file)
	{
		parser = new TOMLParser();
		reader = new StringReader();

		
		TOMLHeader root = null;
		for(String s : reader.read(file))
		{
			parser.reset();
			for(char c : Strings.iterate(s))
			{
				parser.consume(c);
			}

			root = parser.generate();
		}
		
		return root;
	}
}