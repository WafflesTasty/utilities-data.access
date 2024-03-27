package waffles.utils.dacs.utilities;

import java.nio.Buffer;
import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.semantics.Decorator;

/**
 * A {@code Volatile} object maintains data in a {@code ByteBuffer}.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 * @see Decorator
 */
public interface Volatile extends CountableSet, Decorator
{
	@Override
	public abstract Buffer Delegate();
	
	@Override
	public default int Count()
	{
		return Delegate().capacity();
	}
}