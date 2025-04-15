package waffles.utils.dacs.transit;

import waffles.utils.tools.patterns.semantics.Clearable;

/**
 * A {@code BasicIndexTransit} provides a default {@code Transit.Indexed} implementation.
 * It defines a current and starting index, which govern the position
 * of the data objects being loaded and unloaded.
 *
 * @author Waffles
 * @since 01 Dec 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Clearable
 * @see Transit
 */
public abstract class BasicIndexTransit<O> implements Transit.Indexed<O>, Clearable
{
	private int curr, start;

	/**
	 * Returns the size of the {@code BasicIndexTransit}.
	 * 
	 * @return  an index size
	 */
	public int Size()
	{
		return curr - start;
	}
	
	/**
	 * Changes the start of the {@code BasicIndexTransit}.
	 * 
	 * @param s  an initial index
	 */
	public void setStart(int s)
	{
		start = s;
	}
	
	/**
	 * Clears to the start of the {@code BasicIndexTransit}.
	 * 
	 * @param s  a start index
	 */
	public void clear(int s)
	{
		setStart(s);
		clear();
	}
	
	/**
	 * Clears to the start of the {@code BasicIndexTransit}.
	 */
	@Override
	public void clear()
	{
		setIndex(start);
	}
	

	@Override
	public void setIndex(int iTgt)
	{
		curr = iTgt;
	}
	
	@Override
	public int Index()
	{
		return curr;
	}
}