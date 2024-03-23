package waffles.utils.dacs.utilities;

import java.nio.Buffer;
import waffles.utils.sets.CountableSet;

/**
 * A {@code Volatile} object maintains data in a {@code ByteBuffer}.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 */
public interface Volatile extends CountableSet
{
	/**
	 * Returns the buffer of the {@code Volatile}.
	 * 
	 * @return  a data buffer
	 * 
	 * 
	 * @see Buffer
	 */
	public abstract Buffer Data();
	
	@Override
	public default int Count()
	{
		return Data().capacity();
	}
}