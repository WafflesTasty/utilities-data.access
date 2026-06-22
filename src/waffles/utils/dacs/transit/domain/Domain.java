package waffles.utils.dacs.transit.domain;

import waffles.utils.dacs.utilities.Transport;
import waffles.utils.tools.patterns.properties.counters.data.Data;

/**
 * A {@code Domain} defines a {@code Transport} with a data size.
 *
 * @author Waffles
 * @since 27 Nov 2023
 * @version 1.1
 *
 *
 * @param <D>  a data type
 * @see Transport
 * @see Data
 */
public interface Domain<D> extends Data, Transport<D>
{
	@Override
	public default int DataSize()
	{
		return 0;
	}
}