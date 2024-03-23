package waffles.utils.dacs.transit.domain;

import waffles.utils.dacs.transit.Transit;
import waffles.utils.tools.patterns.persistence.Data;

/**
 * A {@code Domain} defines a {@code Transit} as a basic data pattern.
 *
 * @author Waffles
 * @since 27 Nov 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Transit
 * @see Data
 */
public interface Domain<O> extends Data, Transit<O>
{
	// NOT APPLICABLE
}