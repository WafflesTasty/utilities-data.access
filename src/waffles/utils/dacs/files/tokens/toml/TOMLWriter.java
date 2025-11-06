package waffles.utils.dacs.files.tokens.toml;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.files.plaintext.strings.StringWriter;
import waffles.utils.dacs.files.tokens.toml.tree.TOMLTree;

/**
 * A {@code TOMLWriter} writes {@code TOMLTrees} to a {@code File}.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 * 
 * 
 * @see TOMLTree
 * @see Writer
 */
public class TOMLWriter implements Writer<TOMLTree>
{
	private StringWriter writer;
	
	@Override
	public void write(TOMLTree t, File f)
	{
		writer = new StringWriter();
		writer.write(t.describe(), f);
	}
}