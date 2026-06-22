package waffles.utils.dacs.files.plaintext;

import waffles.utils.lang.utilities.ISO;

/**
 * A {@code Logger} is designed to log messages for debugging.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 */
@FunctionalInterface
public interface Logger
{
	public static final int DEPTH = 8;
	
	
	/**
	 * Logs a message into the {@code Logger}.
	 * 
	 * @param msg  a message string
	 */
	public abstract void logMessage(String msg);

	/**
	 * Logs an exception into the {@code Logger}.
	 * 
	 * @param e  an exception
	 * @param d  a recursion depth
	 * 
	 * 
	 * @see Throwable
	 */
	public default void logException(Throwable e, int d)
	{
		String date = ISO.Date.now().condense(ISO.Format.LONG);
		String time = ISO.Time.now().condense(ISO.Format.LONG);
		
		logMessage(e + " at " + time + ", " + date + ":");
		for(StackTraceElement emt : e.getStackTrace())
		{
			logMessage(emt.toString());
		}
		
		if(d > 0)
		{
			Throwable c = e.getCause();
			if(c != null)
			{
				logException(c, d - 1);
			}
		}
	}
	
	/**
	 * Logs an exception into the {@code Logger}.
	 * 
	 * @param e  an exception
	 * 
	 * 
	 * @see Throwable
	 */
	public default void logException(Throwable e)
	{
		logException(e, DEPTH);
	}
}