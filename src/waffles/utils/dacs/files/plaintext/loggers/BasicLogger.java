package waffles.utils.dacs.files.plaintext.loggers;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.plaintext.BasicText;
import waffles.utils.dacs.files.plaintext.Logger;
import waffles.utils.dacs.files.plaintext.strings.StringWriter;
import waffles.utils.tools.patterns.semantics.Saveable;

/**
 * A {@code BasicLogger} implements a {@code Logger} in one simple file.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 * 
 * 
 * @see Saveable
 * @see Logger
 */
public class BasicLogger implements Logger, Saveable
{
	private File file;
	private BasicText log;
	
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
	 * @param f  a logger file
	 * 
	 * 
	 * @see File
	 */
	public BasicLogger(File f)
	{
		log = new BasicText();
		file = f;
	}

	
	@Override
	public void logMessage(String msg)
	{
		log.add(msg);
	}

	@Override
	public boolean load()
	{
		log = new BasicText(file);
		return true;
	}

	@Override
	public boolean save()
	{
		StringWriter writer = new StringWriter();
		writer.write(log, file);
		return true;
	}
}