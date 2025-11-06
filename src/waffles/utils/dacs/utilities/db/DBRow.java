package waffles.utils.dacs.utilities.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

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
			return set.next();			
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