package waffles.utils.dacs.files.streams.bytes;

import waffles.utils.dacs.files.streams.ByteStream;
import waffles.utils.lang.Characters;
import waffles.utils.sets.indexed.array.index.ByteIndex;

/**
 * A {@code UCodeStream} reads characters in 2-byte {@code Unicode} format.
 *
 * @author Waffles
 * @since Oct 21, 2026
 * @version 1.1
 * 
 * 
 * @see ByteStream
 */
public interface UCodeStream extends ByteStream
{
	/**
	 * Reads a string from the {@code ByteStream}.
	 * 
	 * @param c  a character count
	 * @return   a string value
	 */
	public default String readString(int c)
	{
		String s = "";
		for(int k = 0; k < c; k++)
		{
			s += readChar();
		}
		
		return s;
	}
	
	/**
	 * Reads a character from the {@code ByteStream}.
	 * 
	 * @return  a character
	 */
	public default char readChar()
	{
		ByteIndex dat = readBytes(2);
		
		int val = 0;
		for(byte b : dat.Array())
		{
			val = (val << 8) | (b & 0xFF);
		}
		
		return Characters.fromUCode(val);
	}
}
