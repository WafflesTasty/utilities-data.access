package waffles.utils.dacs.db.schema.join;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.schema.DBTable;
import waffles.utils.dacs.utilities.db.format.DBToken;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;

/**
 * A {@code SQLInnerJoin} defines formatting for an SQL inner join.
 *
 * @author Waffles
 * @since 06 Nov 2025
 * @version 1.1
 *
 * 
 * @see SQLFormat
 */
public class SQLInnerJoin implements SQLFormat
{
	private DBInnerJoin<?> scm;
	
	/**
	 * Creates a new {@code SQLInnerJoin}.
	 * 
	 * @param s  a database schema
	 * 
	 * 
	 * @see DBInnerJoin
	 */
	public SQLInnerJoin(DBInnerJoin<?> s)
	{
		scm = s;
	}
	
		
	@Override
	public String Keys(DBHandleable<?> hnd)
	{
		DBTable<?> mst = scm.Master();
		
		String s = "";
		for(DBTable<?> tbl : scm)
		{
			String m = tbl.Formatter().Master();
			for(DBToken key : scm.Getters())
			{
				if(!s.isEmpty())
				{
					s += ", ";
				}
				
				s += m + "." + key.condense();
			}
		}
		
		return s;
	}
	
	@Override
	public String Check(DBHandleable<?> hnd)
	{
		return Schema().Formatter().Master() + "." + Schema().ID() + " = '" + hnd.GUID() + "'";
	}
	
	@Override
	public String Table(DBHandleable<?> hnd)
	{
		String s = "";
		DBTable<?> old = null;
		for(DBTable<?> tbl : scm)
		{			
			if(old != null)
			{
				s += " INNER JOIN ";
			}
			
			s += tbl.Formatter().Master();
			
			if(old != null)
			{
				s += " ON " + Schema().Formatter().Master() + "." + Schema().ID() + " ";
				s += " = " + tbl.Formatter().Master() + "." + tbl.ID();
			}
			
			old = tbl;
		}
		
		return s;
	}
	
	@Override
	public DBInnerJoin<?> Schema()
	{
		return scm;
	}
}