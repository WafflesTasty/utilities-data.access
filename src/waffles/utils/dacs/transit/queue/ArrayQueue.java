package waffles.utils.dacs.transit.queue;

import waffles.utils.dacs.transit.BasicIndex;
import waffles.utils.dacs.transit.Transit;
import waffles.utils.sets.arrays.ArrayLike;

/**
 * An {@code ArrayQueue} can upload primitive arrays through a {@code BasicIndex}.
 *
 * @author Waffles
 * @since 29 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an array type
 * @see BasicIndex
 * @see ArrayLike
 * @see Transit
 */
public class ArrayQueue<A extends ArrayLike<?,?>> extends BasicIndex<A> implements Transit.Queue<A>
{
	private Transit<A> data;
	
	/**
	 * Creates a new {@code ArrayQueue}.
	 * 
	 * @param data  a data source
	 * 
	 * 
	 * @see Transit
	 */
	public ArrayQueue(Transit<A> data)
	{
		this.data = data;
	}
	
	/**
	 * Returns the source of the {@code ArrayQueue}.
	 * 
	 * @return  a data source
	 * 
	 * 
	 * @see Transit
	 */
	public Transit<A> Source()
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