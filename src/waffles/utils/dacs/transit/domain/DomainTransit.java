package waffles.utils.dacs.transit.domain;

import waffles.utils.dacs.transit.Transit;

/**
 * A {@code DomainTransit} can upload sequential chunks of data according to a {@code Domain}.
 *
 * @author Waffles
 * @since 29 Nov 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Transit
 */
public interface DomainTransit<O> extends Transit.Queue<O>, Transit.Indexed<O>
{	
	/**
	 * Returns the count of the {@code DomainTransit}.
	 * 
	 * @return  a domain count
	 */
	public default int Count()
	{
		return Index() / Domain().DataSize();
	}
	
	/**
	 * Returns the domain of the {@code DomainTransit}.
	 * 
	 * @return  a data domain
	 * 
	 * 
	 * @see Domain
	 */
	public abstract Domain<O> Domain();
	
		
	@Override
	public default void load(O obj, int iTgt)
	{
		Domain().load(obj, iTgt);
	}
	
	@Override
	public default O unload(O obj, int iTgt)
	{
		return Domain().unload(obj, iTgt);
	}
	
	
	@Override
	public default void load(O obj)
	{
		int curr = Index();
		Domain<O> dom = Domain();
		int size = dom.DataSize();
		
		setIndex(curr + size);
		dom.load(obj, curr);
	}
	
	@Override
	public default O unload(O obj)
	{
		int curr = Index();
		Domain<O> dom = Domain();
		int size = dom.DataSize();
		
		O dat = dom.unload(obj, curr);
		setIndex(curr + size);
		return dat;
	}
}