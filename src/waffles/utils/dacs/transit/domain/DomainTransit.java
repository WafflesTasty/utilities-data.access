package waffles.utils.dacs.transit.domain;

import waffles.utils.dacs.transit.Transit;

/**
 * A {@code DomainTransit} can upload data chunks according to its {@code Domain}.
 *
 * @author Waffles
 * @since 29 Nov 2023
 * @version 1.1
 *
 *
 * @param <D>  a data type
 * @see Transit
 */
public interface DomainTransit<D> extends Transit.Index<D>
{	
	/**
	 * Returns a transit {@code Domain}.
	 * 
	 * @return  a transit domain
	 * 
	 * 
	 * @see Domain
	 */
	public abstract Domain<? super D> Domain();


	@Override
	public default void push(D dat, long idx)
	{
		int s = Domain().DataSize();
		Domain().push(dat, idx);
		setIndex(idx + s);
	}
	
	@Override
	public default int Count()
	{
		return (int) (Index() / Domain().DataSize());
	}
}