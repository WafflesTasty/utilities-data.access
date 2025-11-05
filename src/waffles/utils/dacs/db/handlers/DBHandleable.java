package waffles.utils.dacs.db.handlers;

import waffles.utils.dacs.db.Database;
import waffles.utils.dacs.utilities.Identifiable;

/**
 * A {@code DBHandleable} defines SQL database operations.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 *
 * @param <D>  a database type
 * @see Identifiable
 * @see Database
 */
public interface DBHandleable<D extends Database<?>> extends Identifiable
{
	/**
	 * Creates a handler for the {@code DBHandleable}.
	 * 
	 * @return  a database handler
	 * 
	 * 
	 * @see DBHandler
	 */
	public abstract DBHandler Handler();
	
	/**
	 * Deletes the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if deleted
	 */
	public abstract boolean delete(D db);

	/**
	 * Fetches the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if exists
	 */
	public abstract boolean exists(D db);
	
	/**
	 * Inserts the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if inserted
	 */
	public abstract boolean insert(D db);
	
	/**
	 * Selects the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if selected
	 */
	public abstract boolean select(D db);
	
	/**
	 * Updates the {@code DBHandleable}.
	 * 
	 * @param db  a target database
	 * @return  {@code true} if updated
	 */
	public abstract boolean update(D db);
}
