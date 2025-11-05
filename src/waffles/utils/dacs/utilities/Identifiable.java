package waffles.utils.dacs.utilities;

import java.util.UUID;

/**
 * An {@code Identifiable} defines a globally unique identifier.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 */
@FunctionalInterface
public interface Identifiable
{
	/**
	 * Defines the unique null identifier.
	 */
	public static final UUID NULL_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
	
	
	/**
	 * Returns the uid of the {@code Identifiable}.
	 * 
	 * @return  a unique identifier
	 * 
	 * 
	 * @see UUID
	 */
	public abstract UUID GUID();
}