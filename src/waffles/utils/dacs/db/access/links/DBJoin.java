package waffles.utils.dacs.db.access.links;

import waffles.utils.dacs.db.access.DBFormat;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.DBEntity;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.entity.format.SQLEntityInsert;
import waffles.utils.dacs.db.access.entity.format.SQLEntitySelect;
import waffles.utils.dacs.db.access.entity.format.SQLEntityUpdate;
import waffles.utils.dacs.db.access.format.SQLAccessDelete;
import waffles.utils.dacs.db.access.format.SQLAccessExists;
import waffles.utils.dacs.db.access.links.format.SQLJoinSelect;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code DBJoin} defines a {@code DBSchema} as an SQL inner join.
 * The GUID of a master table is related to that of its slaves.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <E>  a child type
 * @see DBEntity
 * @see DBSchema
 * @see DBLink
 */
public abstract class DBJoin<E extends DBLink<?>> implements DBSchema<E>
{
	/**
	 * A {@code Format} generates SQL for a {@code DBJoin}.
	 *
	 * @author Waffles
	 * @since 07 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see DBFormat
	 */
	@FunctionalInterface
	public static interface Format extends DBFormat
	{
		/**
		 * Returns a join for the {@code Format}.
		 * 
		 * @return  a database join
		 * 
		 * 
		 * @see DBJoin
		 */
		public abstract DBJoin<?> Join();
		
		
		@Override
		public default SQLFormat<?> create(SQLOps ops)
		{
			switch(ops)
			{
			case DELETE:
				return new SQLAccessDelete(Join().Master());
			case EXISTS:
				return new SQLAccessExists(Join().Master());
			case INSERT:
				return new SQLEntityInsert(Join().Master());				
			case SELECT:
			{
				if(Join().Slaves().isEmpty())
				{
					return new SQLEntitySelect(Join().Master());
				}
				
				return new SQLJoinSelect(Join());
			}
			case UPDATE:
				return new SQLEntityUpdate(Join().Master());
			default:
				return null;
			}
		}
	}

	
	private JavaList<DBTable<?>> slaves;
	
	/**
	 * Creates a new {@code DBJoin}.
	 */
	public DBJoin()
	{
		slaves = new JavaList<>();
	}
		
	/**
	 * Adds a slave to the {@code DBJoin}.
	 * 
	 * @param s  a slave table
	 * 
	 * 
	 * @see DBTable
	 */
	public void add(DBTable<?> s)
	{
		slaves.add(s);
	}
	
	/**
	 * Returns the {@code DBJoin} slaves.
	 * 
	 * @return  a slave iterable
	 * 
	 * 
	 * @see JavaList
	 * @see DBTable
	 */
	public JavaList<DBTable<?>> Slaves()
	{
		return slaves;
	}


	@Override
	public Format Formatter()
	{
		return () -> this;
	}
}