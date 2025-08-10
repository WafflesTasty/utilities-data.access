package waffles.utils.dacs.transit;

import waffles.utils.sets.arrays.ArrayLike;
import waffles.utils.tools.patterns.properties.counters.data.Persistible;

/**
 * An {@code MultiTransit} loads and unloads data arrays into a {@code ByteBuffer}.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 *
 * 
 * @param <A>  an array type
 * @see ArrayLike
 * @see Transit
 */
public interface MultiTransit<A extends ArrayLike<?,?>> extends Transit<A>
{
	/**
	 * Unloads an array from the {@code MultiTransit}.
	 * 
	 * @param dat   an object to unload
	 * @param iSrc  an array index
	 * @param iTgt  a buffer index
	 * @param count  an object count
	 * @return  a loaded object
	 */
	public abstract A unload(A dat, int iSrc, int iTgt, int count);
	
	/**
	 * Loads an array into the {@code MultiTransit}.
	 * 
	 * @param dat   an object to load
	 * @param iSrc  an array index
	 * @param iTgt  a buffer index
	 * @param count  an object count
	 */
	public abstract void load(A dat, int iSrc, int iTgt, int count);
	
	/**
	 * Unloads data from the {@code MultiTransit}.
	 * 
	 * @param p     a persistible object
	 * @param iTgt  a buffer index
	 * 
	 * 
	 * @see Persistible
	 */
	public default void unload(Persistible<? extends A> p, int iTgt)
	{
		unload(p.Data(), iTgt);
	}
	
	/**
	 * Loads data into the {@code MultiTransit}.
	 * 
	 * @param p     a persistible object
	 * @param iTgt  a buffer index
	 * 
	 * 
	 * @see Persistible
	 */
	public default void load(Persistible<? extends A> p, int iTgt)
	{
		load(p.Data(), iTgt);
	}
	
		
	@Override
	public default void load(A dat, int iTgt)
	{
		load(dat, 0, iTgt, dat.Count());
	}

	@Override
	public default A unload(A dat, int iTgt)
	{
		return unload(dat, 0, iTgt, dat.Count());
	}
}