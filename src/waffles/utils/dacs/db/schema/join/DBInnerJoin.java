package waffles.utils.dacs.db.schema.join;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.db.schema.DBTable;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code DBInnerJoin} implements a {@code DBSchema} for an inner join.
 *
 * @author Waffles
 * @since 06 Nov 2025
 * @version 1.1
 *
 *
 * @param <H>  a handler type
 * @see DBHandleable
 * @see AtomicSet
 * @see DBSchema
 */
public class DBInnerJoin<H extends DBHandleable<?>> extends DBSchema<H> implements AtomicSet.Wrapper<DBTable<?>>
{
	private JavaList<DBTable<?>> list;
	
	/**
	 * A {@code Formatter} defines formatting for a {@code DBInnerJoin}.
	 * The select statement is enhanced with additional join info.
	 *
	 * @author Waffles
	 * @since 06 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see DBSchema
	 */
	public interface Formatter extends DBSchema.Formatter
	{
		@Override
		public default SQLFormat get(DBSchema<?> scm, SQLOps ops)
		{
			if(ops == SQLOps.SELECT)
			{
				DBInnerJoin<?> j = (DBInnerJoin<?>) scm;
				return new SQLJoinFormat(j);
			}
			
			return () -> scm;
		}
	}
	
	/**
	 * Creates a new {@code DBInnerJoin}.
	 * 
	 * @param mst  a master table
	 * 
	 * 
	 * @see DBTable
	 */
	public DBInnerJoin(DBTable<?> mst)
	{
		list = new JavaList<>();
		list.add(mst);
	}
	
	/**
	 * Returns the master of the {@code DBInnerJoin}.
	 * 
	 * @return  a master table
	 * 
	 * 
	 * @see DBTable
	 */
	public DBTable<?> Master()
	{
		return list.get(0);
	}


	@Override
	public JavaList<DBTable<?>> Delegate()
	{
		return list;
	}
	
	@Override
	public Formatter Formatter()
	{
		return () -> Master().Formatter().Master();
	}
}