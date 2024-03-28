package waffles.utils.dacs.files.plaintext.loggers;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.plaintext.BasicText;
import waffles.utils.dacs.files.plaintext.Logger;
import waffles.utils.dacs.files.plaintext.strings.StringReader;
import waffles.utils.tools.collections.Iterables;

/**
 * A {@code BasicLogger} implements a {@code Logger} in one simple file.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 * 
 * 
 * @see BasicText
 * @see Logger
 */
public class BasicLogger extends BasicText implements Logger
{
	/**
	 * Creates a new {@code BasicLogger}.
	 * 
	 * @param dat  string data
	 * 
	 * 
	 * @see Iterable
	 */
	public BasicLogger(Iterable<String> dat)
	{
		super(dat);
	}
	
	/**
	 * Creates a new {@code BasicLogger}.
	 * 
	 * @param url  a file url
	 * 
	 * 
	 * @see String
	 */
	public BasicLogger(String url)
	{
		this(new File(url));
	}
	
	/**
	 * Creates a new {@code BasicLogger}.
	 * 
	 * @param file  a file
	 * 
	 * 
	 * @see File
	 */
	public BasicLogger(File file)
	{
		this(new StringReader().read(file));
	}
	
	/**
	 * Creates a new {@code BasicLogger}.
	 */
	public BasicLogger()
	{
		this(Iterables.empty());
	}
	
	
	@Override
	public void logMessage(String msg)
	{
		add(msg);
	}
}