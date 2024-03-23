package waffles.utils.dacs.transit;

/**
 * A {@code BasicIndex} is a basic {@code Transit.Indexed} superclass.
 * It defines a current and starting index, which govern the position
 * of the data objects being loaded and unloaded.
 *
 * @author Waffles
 * @since 01 Dec 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Transit
 */
public abstract class BasicIndex<O> implements Transit.Indexed<O>
{
	private int curr, start;

	/**
	 * Returns the size of the {@code BasicIndex}.
	 * 
	 * @return  an index size
	 */
	public int Size()
	{
		return curr - start;
	}
	
	/**
	 * Changes the start of the {@code BasicIndex}.
	 * 
	 * @param s  an initial index
	 */
	public void setStart(int s)
	{
		start = s;
	}
	
	/**
	 * Resets to a start of the {@code BasicIndex}.
	 * 
	 * @param s  a start index
	 */
	public void reset(int s)
	{
		setStart(s);
		reset();
	}
	
	/**
	 * Resets to the start of the {@code BasicIndex}.
	 */
	public void reset()
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