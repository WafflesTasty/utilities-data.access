package waffles.utils.dacs.db.sql;

import java.sql.SQLException;
import java.sql.Statement;

import waffles.utils.dacs.db.Connection;
import waffles.utils.dacs.utilities.errors.SQLError;

/**
 * An {@code SQLConnection} provides a basic connection interface for an SQL server.
 *
 * @author Waffles
 * @since 03 Dec 2023
 * @version 1.1
 * 
 * 
 * @see java.sql.Connection
 * @see Connection
 * @see SQLAccess
 */
public abstract class SQLConnection implements Connection<SQLAccess, java.sql.Connection>
{
	private SQLAccess acs;
	private java.sql.Connection cnc;
	
	/**
	 * Creates a new {@code SQLConnection}.
	 * 
	 * @param acs  an sql access token
	 * 
	 * 
	 * @see SQLAccess
	 */
	public SQLConnection(SQLAccess acs)
	{
		this.acs = acs;
	}
	
	/**
	 * Opens and returns a SQL {@code Connection}.
	 * 
	 * @return  an sql connection
	 * 
	 * 
	 * @see java.sql.Connection
	 */
	public java.sql.Connection connect()
	{
		try
		{
			if(cnc != null)
			{
				if(cnc.isValid(0))
				{
					return cnc;
				}	
			}
		}
		catch (SQLException e)
		{
			cnc = null;
		}

		cnc = connect(acs);
		return cnc;
	}
	
	/**
	 * Creates an SQL {@code Statement}.
	 * 
	 * @return  an sql statement
	 * 
	 * 
	 * @see Statement
	 */
	public Statement Statement()
	{
		try
		{
			return connect().createStatement();
		}
		catch (SQLException e)
		{
			throw new SQLError(acs);
		}
	}	

	
	/**
	 * Rolls back database changes.
	 */
	public void rollback()
	{
		try
		{
			connect().rollback();
		}
		catch (SQLException e)
		{
			throw new SQLError(acs);
		}
	}
	
	/**
	 * Commits database changes.
	 */
	public void commit()
	{
		try
		{
			connect().commit();
		}
		catch (SQLException e)
		{
			throw new SQLError(acs);
		}
	}

	
	@Override
	public boolean disconnect(SQLAccess acs)
	{
		try
		{
			if(cnc != null)
			{
				cnc.close();
			}
			
			cnc = null;
		}
		catch(SQLException e)
		{
			return false;
		}
		
		return true;
	}
}