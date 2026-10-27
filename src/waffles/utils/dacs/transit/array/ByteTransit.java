package waffles.utils.dacs.transit.array;

import java.nio.ByteBuffer;

import waffles.utils.dacs.transit.ArrayTransit;
import waffles.utils.dacs.utilities.Volatile;
import waffles.utils.sets.indexed.array.like.ByteArray;
import waffles.utils.sets.indexed.array.set.ByteSet;

/**
 * A {@code ByteTransit} loads and unloads {@code ByteArray} data.
 *
 * @author Waffles
 * @since 16 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayTransit
 * @see ByteArray
 */
public interface ByteTransit extends ArrayTransit<ByteArray>
{
	/**
	 * A {@code Base} implements an {@code ByteTransit} as a {@code Volatile}.
	 * 
	 * @author Waffles
	 * @since Jun 22, 2026
	 * @version 1.1
	 *
	 *
	 * @see ArrayTransit
	 * @see ByteTransit
	 * @see ByteArray
	 * @see Volatile
	 */
	public abstract class Base extends ArrayTransit.Base<ByteArray> implements ByteTransit, Volatile
	{
		@Override
		public abstract ByteBuffer Delegate();
		
		
		@Override
		public void pull(ByteArray dat, int ofs, int cnt, long idx)
		{
			Delegate().get((int) idx, dat.Array(), ofs, cnt);
		}
		
		@Override
		public void push(ByteArray dat, int ofs, int cnt, long idx)
		{
			Delegate().put((int) idx, dat.Array(), ofs, cnt);
			setIndex(idx + cnt);
		}
		
		
		@Override
		public void push(byte dat, long idx)
		{
			Delegate().put((int) idx, dat);
			setIndex(idx + 1);
		}
		
		@Override
		public byte pull(long idx)
		{
			return Delegate().get((int) idx);
		}
	}
	
	
	/**
	 * Pulls a byte from the {@code ByteTransit}.
	 * 
	 * @param idx  a data index
	 * @return     a data byte
	 */
	public default byte pull(long idx)
	{
		return pull(1, idx)[0];
	}
	
	/**
	 * Pulls a byte set from the {@code ByteTransit}.
	 * 
	 * @param cnt  a data count
	 * @param idx  a data index
	 * @return     a data array
	 */
	public default byte[] pull(int cnt, long idx)
	{
		byte[] dat = new byte[cnt];
		pull(ByteSet.of(dat), idx);
		return dat;
	}

	/**
	 * Pushes a byte set into the {@code ByteTransit}.
	 * 
	 * @param dat  a data array
	 * @param idx  a data index
	 */
	public default void push(byte[] dat, long idx)
	{
		push(ByteSet.of(dat), idx);
	}
	
	/**
	 * Pushes a byte into the {@code ByteTransit}.
	 * 
	 * @param dat  a data byte
	 * @param idx  a data index
	 */
	public default void push(byte dat, long idx)
	{
		push(new byte[]{dat}, idx);
	}
	
	/**
	 * Pushes a byte into the {@code ByteTransit}.
	 * 
	 * @param dat  a data byte
	 */
	public default void push(byte dat)
	{
		push(dat, Index());
	}
}