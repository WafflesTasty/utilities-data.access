package waffles.utils.dacs.files.plaintext;

import waffles.utils.lang.time.iso.ISODate;
import waffles.utils.lang.time.iso.ISOTime;
import waffles.utils.lang.util.ISO;

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
	 * 
	 * 
	 * @see Throwable
	 */
	public default void logException(Throwable e)
	{
		String date = ISODate.now().parse(ISO.Format.LONG);
		String time = ISOTime.now().parse(ISO.Format.LONG);
		
		logMessage(e + " at " + time + ", " + date + ":");
		for(StackTraceElement emt : e.getStackTrace())
		{
			logMessage(emt.toString());
		}
	}
}