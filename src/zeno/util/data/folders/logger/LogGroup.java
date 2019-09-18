package zeno.util.data.folders.logger;

import java.util.HashMap;
import java.util.Map;

import zeno.util.data.files.Folder;
import zeno.util.data.folders.Logger;
import zeno.util.tools.patterns.manipulators.Loadable;
import zeno.util.tools.patterns.manipulators.Saveable;

/**
 * The {@code LogGroup} class defines a subfolder in a {@code Logger}.
 * 
 * @author Zeno
 * @since Sep 18, 2019
 * @version 1.0
 * 
 * 
 * @see Loadable
 * @see Saveable
 */
public class LogGroup implements Loadable, Saveable
{
	private Folder folder;
	private Logger logger;
	
	private Map<String, LogFile> files;
	
	/**
	 * Creates a new {@code LogGroup}.
	 * 
	 * @param log  a parent logger
	 * @param name  a group name
	 * 
	 * 
	 * @see Logger
	 * @see String
	 */
	public LogGroup(Logger log, String name)
	{
		folder = new Folder(log.Folder(), name);
		files = new HashMap<>();
		logger = log;
	}
	
	/**
	 * Logs a message into the {@code LogGroup}.
	 * 
	 * @param level  a priority level
	 * @param text  a text message
	 * 
	 * 
	 * @see String
	 */
	public void logMessage(int level, String text)
	{
		if(level <= logger.Priority())
		{
			for(LogFile file : files.values())
			{
				file.logMessage(level, text);
			}
		}
	}
	
	/**
	 * Adds a log file to the {@code LogGroup}.
	 * 
	 * @param file  a log file
	 * 
	 * 
	 * @see LogFile
	 */
	public void addFile(LogFile file)
	{
		files.put(file.Name(), file);
	}
	
	
	/**
	 * Returns the folder of the {@code LogGroup}.
	 * 
	 * @return  a group folder
	 * 
	 * 
	 * @see Folder
	 */
	public Folder Folder()
	{
		return folder;
	}
	
	/**
	 * Returns a log file in the {@code LogGroup}.
	 * 
	 * @param name  a file name
	 * @return  a log file
	 * 
	 * 
	 * @see LogFile
	 * @see String
	 */
	public LogFile File(String name)
	{
		return files.get(name);
	}
	
	/**
	 * Returns the logger of the {@code LogGroup}.
	 * 
	 * @return  a parent logger
	 * 
	 * 
	 * @see Logger
	 */
	public Logger Logger()
	{
		return logger;
	}
	
	/**
	 * Returns the name of the {@code LogGroup}.
	 * 
	 * @return  a group name
	 * 
	 * 
	 * @see String
	 */
	public String Name()
	{
		return folder.Name();
	}
	

	@Override
	public boolean load()
	{
		for(LogFile file : files.values())
		{
			if(!file.load())
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean save()
	{
		for(LogFile file : files.values())
		{
			if(!file.save())
			{
				return false;
			}
		}
		
		return true;
	}
}