package waffles.utils.dacs.transit;

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
 * @see Transit
 */
public abstract class BasicIndexTransit<O> implements Transit.Indexed<O>
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
	 * Resets to a start of the {@code BasicIndexTransit}.
	 * 
	 * @param s  a start index
	 */
	public void reset(int s)
	{
		setStart(s);
		reset();
	}
	
	/**
	 * Resets to the start of the {@code BasicIndexTransit}.
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