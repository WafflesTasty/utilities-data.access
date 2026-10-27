package waffles.utils.dacs.files.streams.bytes;

import waffles.utils.dacs.files.streams.ByteStream;
import waffles.utils.sets.indexed.array.index.ByteIndex;

/**
 * A {@code LilEndianStream} reads little endian values from an {@code InputStream}.
 *
 * @author Waffles
 * @since Oct 21, 2026
 * @version 1.1
 * 
 * 
 * @see ByteStream
 */
public interface LilEndianStream extends ByteStream
{
	/**
	 * Reads an integer from the {@code ByteStream}.
	 * 
	 * @return  an integer value
	 */
	public default int readInt()
	{
		ByteIndex dat = readBytes(4);
		
		int val = 0;
		for(int k = 3; k >= 0; k--)
		{
			int b = dat.get(k) & 0xFF;
			val = (val << 8) + b;
		}
		
		return val;
	}
	
	/**
	 * Reads a short from the {@code ByteStream}.
	 * 
	 * @return  a short value
	 */
	public default short readShort()
	{
		ByteIndex dat = readBytes(2);
		
		int val = 0;
		for(int k = 3; k >= 0; k--)
		{
			int b = dat.get(k) & 0xFF;
			val = (val << 8) + b;
		}
		
		return (short) val;
	}

	/**
	 * Reads a float from the {@code ByteStream}.
	 * 
	 * @return  a float value
	 */
	public default float readFloat()
	{
		return Float.intBitsToFloat(readInt());
	}
}
