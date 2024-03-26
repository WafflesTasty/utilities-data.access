package waffles.utils.dacs.transit.indexed;

import waffles.utils.dacs.transit.BasicIndexTransit;
import waffles.utils.dacs.transit.domain.Domain;
import waffles.utils.dacs.transit.domain.DomainTransit;

/**
 * 
 * An {@code ObjectTransit} provides a {@code BasicIndexTransit} implementation which
 * processes generic objects through a {@code DomainTransit}.
 *
 * @author Waffles
 * @since 26 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see BasicIndexTransit
 * @see DomainTransit
 */
public class ObjectTransit<O> extends BasicIndexTransit<O> implements DomainTransit<O>
{
	private Domain<O> domain;
	
	/**
	 * Changes the domain of the {@code ObjectTransit}.
	 * 
	 * @param dom  a transit domain
	 * 
	 * 
	 * @see Domain
	 */
	public void setDomain(Domain<O> dom)
	{
		domain = dom;
	}
	
	
	@Override
	public Domain<O> Domain()
	{
		return domain;
	}
}