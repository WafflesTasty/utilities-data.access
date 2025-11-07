package waffles.utils.dacs.db.access.links;

import waffles.utils.dacs.db.access.DBFormat;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.entity.format.SQLEntityInsert;
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
 * @param <L>  a link type
 * @see DBSchema
 * @see DBLink
 */
public abstract class DBJoin<L extends DBLink<?>> implements DBSchema<L>
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
				return new SQLJoinSelect(Join());
			case UPDATE:
				return new SQLEntityUpdate(Join().Master());
			default:
				return null;
			}
		}
	}

	
	private DBTable<?> master;
	private JavaList<DBTable<?>> slaves;
	
	/**
	 * Creates a new {@code DBJoin}.
	 * 
	 * @param mst  a master table
	 * 
	 * 
	 * @see DBTable
	 */
	public DBJoin(DBTable<?> mst)
	{
		slaves = new JavaList<>();
		master = mst;
	}
	
	/**
	 * Returns the slaves of the {@code DBJoin}.
	 * 
	 * @return  a slave iterable
	 * 
	 * 
	 * @see Iterable
	 * @see DBTable
	 */
	public Iterable<DBTable<?>> Slaves()
	{
		return slaves;
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
	
			
	@Override
	public DBTable<?> Master()
	{
		return master;
	}

	@Override
	public Format Formatter()
	{
		return () -> this;
	}
}