package waffles.utils.dacs.files.plaintext;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.plaintext.strings.StringReader;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.tools.collections.Iterables;

/**
 * A {@code BasicText} defines a text file as a {@code List} of strings.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see List
 */
public class BasicText extends List<String>
{
	/**
	 * Creates a new {@code BasicText}.
	 * 
	 * @param dat  string data
	 * 
	 * 
	 * @see Iterable
	 */
	public BasicText(Iterable<String> dat)
	{
		for(String line : dat)
		{
			add(line);
		}
	}
	
	/**
	 * Creates a new {@code BasicText}.
	 * 
	 * @param url  a file url
	 * 
	 * 
	 * @see String
	 */
	public BasicText(String url)
	{
		this(new File(url));
	}
	
	/**
	 * Creates a new {@code BasicText}.
	 * 
	 * @param file  a file
	 * 
	 * 
	 * @see File
	 */
	public BasicText(File file)
	{
		this(new StringReader().read(file));
	}
	
	/**
	 * Creates a new {@code BasicText}.
	 */
	public BasicText()
	{
		this(Iterables.empty());
	}
}