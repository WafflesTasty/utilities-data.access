package waffles.utils.dacs.transit.indexed;

import waffles.utils.dacs.transit.BasicIndexTransit;
import waffles.utils.dacs.transit.Transit;
import waffles.utils.sets.arrays.ArrayLike;

/**
 * An {@code ArrayTransit} provides a {@code BasicIndexTransit} implementation which
 * processes primitive arrays through a {@code Transit.Queue}.
 *
 * @author Waffles
 * @since 29 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an array type
 * @see BasicIndexTransit
 * @see ArrayLike
 * @see Transit
 */
public class ArrayTransit<A extends ArrayLike<?,?>> extends BasicIndexTransit<A> implements Transit.Queue<A>
{
	private Transit<A> data;
	
	/**
	 * Creates a new {@code ArrayTransit}.
	 * 
	 * @param data  a data source
	 * 
	 * 
	 * @see Transit
	 */
	public ArrayTransit(Transit<A> data)
	{
		this.data = data;
	}
	
	/**
	 * Returns the {@code ArrayTransit} data source.
	 * 
	 * @return  a data source
	 * 
	 * 
	 * @see Transit
	 */
	public Transit<A> Data()
	{
		return data;
	}
	
	
	@Override
	public A unload(A obj, int iTgt)
	{
		return data.unload(obj, iTgt);
	}
	
	@Override
	public void load(A obj, int iTgt)
	{
		data.load(obj, iTgt);
	}
	
	
	@Override
	public void load(A set)
	{
		int curr = Index();
		int size = set.Count();
		
		setIndex(curr + size);
		data.load(set, curr);
	}
	
	@Override
	public A unload(A set)
	{
		int curr = Index();
		int size = set.Count();
		
		A dat = data.unload(set, curr);
		setIndex(curr + size);
		return dat;
	}
}