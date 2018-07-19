package zeno.util.dao.folders;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.function.Consumer;

import zeno.util.dao.Folder;
import zeno.util.dao.handlers.TextHandler;
import zeno.util.lang._deprecated.time1.Date;
import zeno.util.tools.Integers;
import zeno.util.tools.helper.Array;
import zeno.util.tools.helper.iterators.ArrayIterator;

/**
 * The {@code DebugFolder} class defines a folder containing debug files.
 * <br> It can contain multiple files bound by an index and a name, and
 * a separate error handler for unique exception behavior.
 * 
 * @since Sep 23, 2016
 * @author Zeno
 * 
 * @see TextHandler
 * @see Iterable
 * @see Folder
 */
public class DebugFolder extends Folder implements Iterable<TextHandler>
{
	private TextHandler[] files;
	private Consumer<Exception> exhandler;
	
	/**
	 * Creates a new {@code DebugFolder}.
	 * 
	 * @param url  a file url
	 * @see String
	 */
	public DebugFolder(String url)
	{
		super(url);
	}
	
	/**
	 * Creates a new {@code DebugFolder}.
	 * 
	 * @param path  a file path
	 * @see Path
	 */
	public DebugFolder(Path path)
	{
		super(path);
	}
	
	
	/**
	 * Changes the error handler of the {@code DebugFolder}.
	 * 
	 * @param handler  an error handler
	 * @see Exception
	 * @see Consumer
	 */
	public void setErrorHandler(Consumer<Exception> handler)
	{
		exhandler = handler;
	}
	
	/**
	 * Logs an exception into the {@code DebugFolder}.
	 * 
	 * @param i  a file index to use
	 * @param e  an exception to log
	 * @see Exception
	 */
	public void logException(int i, Exception e)
	{
		logMessage(i, e + " at " + Date.now() + ":");
		for(StackTraceElement emt : e.getStackTrace())
		{
			logMessage(i, emt.toString());
		}
		
		if(exhandler != null)
		{
			exhandler.accept(e);
		}
	}
	
	/**
	 * Logs a message into the {@code DebugFolder}.
	 * 
	 * @param i  a file index to use
	 * @param text  a message to log
	 * @see String
	 */
	public void logMessage(int i, String text)
	{
		files[i].add(text);
	}
	
	/**
	 * Adds a file to the {@code DebugFolder}.
	 * 
	 * @param i  the file's index
	 * @param name  the file's name
	 * @see String
	 */
	public void addFile(int i, String name)
	{
		if(files.length <= i)
		{
			files = Array.copy.of(files, i + 1);
		}
		
		files[i] = new TextHandler(Path().resolve(i + "_" + name));
	}

	
	@Override
	public Iterator<TextHandler> iterator()
	{
		return new ArrayIterator<>(files);
	}

	@Override
	public boolean load()
	{
		files = new TextHandler[0];
		if(!super.load())
		{
			return false;
		}

		for(Path path : paths(1))
		{
			String name = path.getFileName().toString();
			int i = Integers.parse(name.substring(0, 1));
			addFile(i, name.substring(2));
			if(!files[i].load())
			{
				return false;
			}
		}

		return true;
	}
	
	@Override
	public boolean save()
	{
		if(!super.save())
		{
			return false;
		}
		
		for(TextHandler file : files)
		{
			file.save();
		}

		return true;
	}
}