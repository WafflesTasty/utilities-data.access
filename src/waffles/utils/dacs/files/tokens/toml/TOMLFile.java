package waffles.utils.dacs.files.tokens.toml;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.tokens.toml.tree.TOMLData;
import waffles.utils.dacs.files.tokens.toml.tree.TOMLTree;
import waffles.utils.dacs.files.tokens.toml.tree.nodes.TOMLComment;
import waffles.utils.dacs.files.tokens.toml.tree.nodes.TOMLHeader;

/**
 * A {@code TOMLFile} defines a data structure for the {@code TOML} file format.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 * 
 * 
 * @see TOMLTree
 */
public class TOMLFile extends TOMLTree
{
	/**
	 * Creates a new {@code TOMLFile}.
	 * 
	 * @param url  a file url
	 * 
	 * 
	 * @see String
	 */
	public TOMLFile(String url)
	{
		this(new File(url));
	}

	/**
	 * Creates a new {@code TOMLFile}.
	 * 
	 * @param file  a file
	 * 
	 * 
	 * @see File
	 */
	public TOMLFile(File file)
	{
		setRoot(new TOMLReader().read(file));
	}
	
	/**
	 * Creates a new {@code TOMLFile}.
	 */
	public TOMLFile()
	{
		setRoot(new TOMLHeader(""));
	}

		
	/**
	 * Changes a header node in the {@code TOMLFile}.
	 * 
	 * @param keys  a key set
	 * @return  a data tree
	 * 
	 * 
	 * @see TOMLData
	 */
	public TOMLData add(String... keys)
	{
		TOMLHeader node = Root();
		for(String key : keys)
		{
			TOMLHeader child = node.Child(key);
			if(child == null)
			{
				child = new TOMLHeader(key);
				node.addChild(child);
				node = child;
			}
		}
		
		return node.Data();
	}
	
	/**
	 * Returns a data tree in the {@code TOMLFile}.
	 * 
	 * @param keys  a key set
	 * @return  a data tree
	 * 
	 * 
	 * @see TOMLData
	 */
	public TOMLData get(String... keys)
	{
		TOMLHeader node = Root();
		for(String key : keys)
		{
			node = node.Child(key);
			if(node == null)
			{
				return null;
			}
		}
		
		return node.Data();
	}
	
	/**
	 * Adds a comment to the {@code TOMLFile}.
	 * 
	 * @param cmt  a comment line
	 */
	public void comment(String cmt)
	{
		Root().addChild(new TOMLComment(cmt));
	}
	
	
	@Override
	public TOMLHeader Root()
	{
		return (TOMLHeader) super.Root();
	}
}