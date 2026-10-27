package waffles.utils.dacs.files.streams;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

import waffles.utils.dacs.transit.array.ByteTransit;
import waffles.utils.dacs.utilities.files.AccessError;
import waffles.utils.sets.indexed.array.index.ByteIndex;
import waffles.utils.sets.indexed.array.like.ByteArray;
import waffles.utils.tools.patterns.properties.counters.Indexed;

/**
 * A {@code ByteStream} reads primitive types from an {@code InputStream}.
 *
 * @author Waffles
 * @since Oct 21, 2026
 * @version 1.1
 * 
 * 
 * @see Indexed
 */
public interface ByteStream extends ByteTransit
{	
	/**
	 * Returns a {@code ByteStream} buffer.
	 * 
	 * @return  a byte buffer
	 * 
	 * 
	 * @see ByteBuffer
	 */
	public abstract ByteBuffer Buffer();
	
	/**
	 * Returns an active {@code ByteChannel}.
	 * 
	 * @return  a byte channel
	 * 
	 * 
	 * @see SeekableByteChannel
	 */
	public abstract SeekableByteChannel Channel();

	/**
	 * Loads a {@code ByteIndex} from the stream.
	 * 
	 * @param cnt  a byte count
	 * @return     a byte index
	 * 
	 * 
	 * @see ByteIndex
	 */
	public default ByteIndex readBytes(int cnt)
	{
		ByteIndex idx = new ByteIndex(cnt);
		pull(idx); return idx;
	}
	
	
	@Override
	public default void pull(ByteArray dat, int ofs, int cnt, long idx)
	{
		try
		{
			setIndex(idx);
			Buffer().clear();
			Buffer().limit(cnt);
			Channel().read(Buffer());
			Buffer().flip();
			
			for(int k = ofs; k < ofs + cnt; k++)
			{
				byte b = Buffer().get();
				dat.put(b, k);
			}
		} 
		catch (IOException e)
		{
			throw new AccessError();
		}
	}
	
	@Override
	public default void push(ByteArray dat, int ofs, int cnt, long idx)
	{
		try
		{
			setIndex(idx);
			Buffer().clear();
			Buffer().limit(cnt);
			
			for(int k = ofs; k < ofs + cnt; k++)
			{
				byte b = dat.get(k);
				Buffer().put(b);
			}
			
			Buffer().flip();
			Channel().write(Buffer());
		} 
		catch (IOException e)
		{
			throw new AccessError();
		}
	}
	
	
	@Override
	public default Long Index()
	{
		try
		{
			return Channel().position();
		}
		catch (IOException e)
		{
			throw new AccessError();
		}
	}
	
	@Override
	public default void setIndex(Long idx)
	{
		try
		{
			Channel().position(idx);
		}
		catch (IOException e)
		{
			throw new AccessError();
		}
	}
	
	@Override
	public default int Count()
	{
		long s = 0;
		try
		{
			s = Channel().size();
		}
		catch (IOException e)
		{
			throw new AccessError();
		}
		
		return (int) s;
	}
}