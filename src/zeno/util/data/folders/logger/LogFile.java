package zeno.util.data.folders.logger;

import zeno.util.data.FileSystem;
import zeno.util.data.files.File;
import zeno.util.data.handlers.TextFile;
import zeno.util.tools.patterns.manipulators.Loadable;
import zeno.util.tools.patterns.manipulators.Saveable;

/**
 * The {@code LogFile} defines a single file in a {@code LogGroup}.
 *
 * @author Zeno
 * @since Sep 18, 2019
 * @version 1.0
 * 
 * 
 * @see Loadable
 * @see Saveable
 * @see TextFile
 */
public class LogFile extends TextFile implements Loadable, Saveable
{
	private File file;
	private LogGroup group;
	
	/**
	 * Creates a new {@code LogFile}.
	 * 
	 * @param grp  a parent group
	 * @param name  a file name
	 * 
	 * 
	 * @see LogGroup
	 * @see String
	 */
	public LogFile(LogGroup grp, String name)
	{
		file = new File(grp.Folder(), name);
		group = grp;
	}

	/**
	 * Logs a message into the {@code LogFile}.
	 * 
	 * @param level  a priority level
	 * @param text  a text message
	 * 
	 * 
	 * @see String
	 */
	public void logMessage(int level, String text)
	{
		if(level <= group.Logger().Priority())
		{
			add(text);
		}
	}
	
	
	/**
	 * Returns the group of the {@code LogFile}.
	 * 
	 * @return  a parent group
	 * 
	 * 
	 * @see LogGroup
	 */
	public LogGroup Group()
	{
		return group;
	}
	
	/**
	 * Returns the name of the {@code LogFile}.
	 * 
	 * @return  a file name
	 * 
	 * 
	 * @see String
	 */
	public String Name()
	{
		return file.Name();
	}
	
	/**
	 * Returns the file of the {@code LogFile}.
	 * 
	 * @return  a log file
	 * 
	 * 
	 * @see File
	 */
	public File File()
	{
		return file;
	}
	
	
	@Override
	public boolean load()
	{
		try
		{
			read(file);
		}
		catch(FileSystem.AccessError e)
		{
			return false;
		}

		return true;
	}
	
	@Override
	public boolean save()
	{
		try
		{
			write(file);
		}
		catch(FileSystem.AccessError e)
		{
			return false;
		}

		return true;
	}
}