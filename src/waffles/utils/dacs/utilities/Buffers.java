package waffles.utils.dacs.utilities;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * The {@code Buffers} class provides static-access utilities to manage buffers.
 *
 * @author Waffles
 * @since 17 Mar 2025
 * @version 1.1
 */
public final class Buffers
{
	/**
	 * Defines the byte order of a {@code ByteBuffer}.
	 */
	public static final ByteOrder ORDER = ByteOrder.nativeOrder();
	
	/**
	 * Creates a new {@code ByteBuffer}.
	 * 
	 * @param size  a buffer size
	 * @return  a byte buffer
	 * 
	 * 
	 * @see ByteBuffer
	 */
	public static ByteBuffer create(int size)
	{
		return ByteBuffer.allocateDirect(size).order(ORDER);
	}
	
	
	private Buffers()
	{
		// NOT APPLICABLE
	}
}