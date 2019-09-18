package zeno.util.data.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import zeno.util.data.handlers.logger.LogGroup;
import zeno.util.data.system.File;
import zeno.util.data.system.Folder;
import zeno.util.lang._deprecated.time1.Date;
import zeno.util.tools.patterns.manipulators.Loadable;
import zeno.util.tools.patterns.manipulators.Saveable;

/**
 * The {@code Logger} class defines a folders used to perform information logging.
 *
 * @author Zeno
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see Loadable
 * @see Saveable
 */
public class Logger implements Loadable, Saveable
{	
	private int priority;
	private Consumer<Exception> handler;
	private Map<String, LogGroup> groups;
	private Folder folder;

	/**
	 * Creates a new {@code Logger}.
	 * 
	 * @param url  a folder url
	 * 
	 * 
	 * @see String
	 */
	public Logger(String url)
	{
		this(new Folder(url));
	}
	
	/**
	 * Creates a new {@code Logger}.
	 * 
	 * @param p  a parent folder
	 * 
	 * 
	 * @see Folder
	 */
	public Logger(Folder p)
	{
		groups = new HashMap<>();
		folder = p;
	}
	
	
	/**
	 * Logs an exception into the {@code Logger}.
	 * 
	 * @param e  an exception to log
	 * 
	 * 
	 * @see Exception
	 */
	public void logException(Exception e)
	{
		TextFile err = new TextFile();
		err.add(e + " at " + Date.now() + ":");
		for(StackTraceElement emt : e.getStackTrace())
		{
			err.add(emt.toString());
		}
		
		File errfile = Error();
		err.write(errfile);
		if(handler != null)
		{
			handler.accept(e);
		}
	}
		
	/**
	 * Changes the error handler of the {@code Logger}.
	 * This is executed in addition to writing the error
	 * to a standard text file in the log folder.
	 * 
	 * @param h  an error handler
	 * 
	 * 
	 * @see Exception
	 * @see Consumer
	 */
	public void setHandler(Consumer<Exception> h)
	{
		handler = h;
	}

	/**
	 * Logs a message into the {@code Logger}.
	 * </br> This message is broadcasted across all groups.
	 * 
	 * @param level  a message severity level
	 * @param text   a message to log
	 * 
	 * 
	 * @see String
	 */
	public void logMessage(int level, String text)
	{
		if(level <= priority)
		{
			for(LogGroup grp : groups.values())
			{
				grp.logMessage(level, text);
			}
		}
	}

	
	/**
	 * Returns the error log of the {@code Logger}.
	 * 
	 * @return  an error file
	 * 
	 *  
	 * @see File
	 */
	public File Error()
	{
		return new File(folder, "ERROR.TXT");
	}
	
	/**
	 * Returns the base folder of the {@code Logger}.
	 * 
	 * @return  a log folder
	 * 
	 * 
	 * @see Folder
	 */
	public Folder Folder()
	{
		return folder;
	}
	
	
	/**
	 * Returns a subgroup of the {@code Logger}.
	 * 
	 * @param name  a group name
	 * @return  a log group
	 * 
	 * 
	 * @see LogGroup
	 * @see String
	 */
	public LogGroup Group(String name)
	{
		return groups.get(name);
	}
	
	/**
	 * Adds a log subgroup to the {@code Logger}.
	 * 
	 * @param grp  a new group
	 * 
	 * 
	 * @see LogGroup
	 */
	public void addGroup(LogGroup grp)
	{
		groups.put(grp.Name(), grp);
	}
	
	/**
	 * Changes the priority of the {@code Logger}.
	 * Highest priority is denoted by zero.
	 * 
	 * @param p  a new priority
	 */
	public void setPriority(int p)
	{
		priority = p;
	}
		
	/**
	 * Returns the priority of the {@code Logger}.
	 * 
	 * @return  the logger priority
	 */
	public int Priority()
	{
		return priority;
	}

	
	@Override
	public boolean load()
	{
		for(LogGroup grp : groups.values())
		{
			if(!grp.load())
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean save()
	{
		for(LogGroup grp : groups.values())
		{
			if(!grp.save())
			{
				return false;
			}
		}
		
		return true;
	}

}