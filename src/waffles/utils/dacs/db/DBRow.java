package waffles.utils.dacs.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import waffles.utils.lang.utilities.enums.Confirmation;

/**
 * A {@code DBRow} iterates over rows in a database query.
 *
 * @author Waffles
 * @since 04 Nov 2025
 * @version 1.1
 *
 * 
 * @see Iterator
 */
public class DBRow implements Iterator<DBRow>
{
	private ResultSet set;
	private Confirmation cnf;
	
	/**
	 * Creates a new {@code DBRow}.
	 * 
	 * @param s  a result set
	 * 
	 * 
	 * @see ResultSet
	 */
	public DBRow(ResultSet s)
	{
		cnf = Confirmation.PENDING;
		set = s;
	}
	
	/**
	 * Returns an object in the {@code DBRow}.
	 * 
	 * @param c  a column name
	 * @return   a database object
	 */
	public Object get(String c)
	{
		try
		{
			return set.getObject(c);
		}
		catch (SQLException e)
		{
			return null;
		}
	}
	
	/**
	 * Returns an object in the {@code DBRow}.
	 * 
	 * @param c  a column index
	 * @return   a database object
	 */
	public Object get(int c)
	{
		try
		{
			return set.getObject(c);
		}
		catch (SQLException e)
		{
			return null;
		}
	}


	@Override
	public boolean hasNext()
	{
		try
		{
			switch(cnf)
			{
			case PENDING:
			{
				if(set.next())
				{
					cnf = Confirmation.CONFIRMED;
					return true;
				}			
			}
			case CONFIRMED:
			{
				if(!set.next())
				{
					cnf = Confirmation.CANCELLED;
					return false;
				}
				
				return true;
			}
			case CANCELLED:
			default:
				return false;
			}
		}
		catch(SQLException e)
		{
			return false;
		}
	}

	@Override
	public DBRow next()
	{
		return this;
	}
}