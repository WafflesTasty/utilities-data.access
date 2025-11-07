package waffles.utils.dacs.db.access;

import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.utilities.db.DBLoader;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.Token;

/**
 * A {@code DBSchema} mediates between a {@code Database} and a {@code DBAccess}.
 * It is designed to generate SQL strings which read or write access objects.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @see DBAccess
 * @see Token
 */
public interface DBSchema<A extends DBAccess<?>> extends Token
{
	/**
	 * Returns the master of the {@code DBSchema}.
	 * 
	 * @return  a master table
	 * 
	 * 
	 * @see DBTable
	 */
	public abstract DBTable<?> Master();
	
	/**
	 * Returns the loader of the {@code DBSchema}.
	 * 
	 * @return  a data loader
	 * 
	 * 
	 * @see DBLoader
	 */
	public abstract DBLoader<A> Loader();
	
	
	/**
	 * Creates an SQL formatter from the {@code DBSchema}.
	 * 
	 * @param ops  a database operation
	 * @return  an SQL formatter
	 * 
	 * 
	 * @see SQLFormat
	 * @see SQLOps
	 */
	public default SQLFormat<?> Formatter(SQLOps ops)
	{
		return Formatter().create(ops);
	}
		
	@Override
	public abstract DBFormat Formatter();
}