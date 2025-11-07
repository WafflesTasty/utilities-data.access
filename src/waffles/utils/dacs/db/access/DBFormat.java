package waffles.utils.dacs.db.access;

import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.lang.tokens.format.Format;

/**
 * A {@code DBFormat} provides SQL formatting for a {@code DBSchema}.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 * 
 * @see DBSchema
 * @see Format
 */
@FunctionalInterface
public interface DBFormat extends Format<DBSchema<?>>
{
	/**
	 * Creates a {@code Format} for an SQL operation.
	 * 
	 * @param ops  an operation type
	 * @return  an operation format
	 * 
	 * 
	 * @see SQLFormat
	 * @see SQLOps
	 */
	public abstract SQLFormat<?> create(SQLOps ops);
	
	@Override
	public default String parse(DBSchema<?> scm)
	{
		return scm.Master().Name();
	}
}
