package waffles.utils.dacs.utilities;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import waffles.utils.dacs.files.assets.Image;

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
	 * Byte data is loaded from an {@code Image}.
	 * 
	 * @param img  a source image
	 * @return  a byte buffer
	 * 
	 * 
	 * @see ByteBuffer
	 * @see Image
	 */
	public static ByteBuffer create(Image img)
	{
		byte[] data = img.Array();
		int[] dims  = img.Dimensions();
		int l = data.length;

		ByteBuffer b = Buffers.create(l);
		return b.put(data).flip();
	}
	
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