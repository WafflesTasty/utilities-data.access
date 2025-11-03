package waffles.utils.dacs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.db.schema.format.SQLDelete;
import waffles.utils.dacs.db.schema.format.SQLSelect;
import waffles.utils.dacs.db.schema.format.SQLUpdate;
import waffles.utils.dacs.db.schema.maps.DBSetter;
import waffles.utils.dacs.utilities.DataLink;
import waffles.utils.dacs.utilities.encrypt.Login;
import waffles.utils.dacs.utilities.errors.SQLError;

/**
 * The {@code Database} class provides access to an entity-based SQL database.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @param <E>  an entity type
 * @see DataLink
 * @see DBEntity
 * @see Login
 */
public abstract class Database<E extends DBEntity<?>> implements DataLink<Login, Boolean>
{
	private Connection cnc;
	private Login login;
	
	/**
	 * Commits {@code Database} changes.
	 */
	public void commit()
	{
		try
		{
			if(cnc != null)
			{
				cnc.commit();
			}
		}
		catch (SQLException e)
		{
			throw new SQLError(login);
		}
	}
	
	/**
	 * Rolls back {@code Database} changes.
	 */
	public void rollback()
	{
		try
		{
			if(cnc != null)
			{
				cnc.rollback();
			}
		}
		catch (SQLException e)
		{
			throw new SQLError(login);
		}
	}

	
	/**
	 * Returns the prefix of the {@code Database}.
	 * 
	 * @return  a host prefix
	 */
	public abstract String Prefix();

	
	/**
	 * Deletes an entity from the {@code Database}.
	 * 
	 * @param ent  a database entity
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 */
	public boolean delete(E ent, DBSchema<E> scm)
	{
		SQLDelete<E> del = new SQLDelete<>(ent);
		String sql = del.parse(scm);
		
		try
		{
			Statement s = cnc.createStatement();			
			return s.executeUpdate(sql) > 0;
		}
		catch (SQLException e)
		{
			throw new SQLError(sql);
		}
	}
	
	/**
	 * Selects an entity from the {@code Database}.
	 * 
	 * @param ent  a database entity
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 */
	public boolean select(E ent, DBSchema<E> scm)
	{
		SQLSelect<E> sel = new SQLSelect<>(ent);
		String sql = sel.parse(scm);
		
		try
		{
			Statement s = cnc.createStatement();
			ResultSet r = s.executeQuery(sql);
			if(r.next())
			{
				DBSetter<E> set = scm.Setter();
				set.update(ent, r);
				return true;
			}
			
			return false;
		}
		catch (SQLException e)
		{
			throw new SQLError(sql);
		}
	}
	
	/**
	 * Updates an entity into the {@code Database}.
	 * 
	 * @param ent  a database entity
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 */
	public boolean update(E ent, DBSchema<E> scm)
	{
		SQLUpdate<E> upd = new SQLUpdate<>(ent);
		String sql = upd.parse(scm);
		
		try
		{
			Statement s = cnc.createStatement();
			return s.executeUpdate(sql) > 0;
		}
		catch (SQLException e)
		{
			throw new SQLError(sql);
		}
	}
	
	
	@Override
	public boolean disconnect(Login log)
	{
		try
		{
			if(cnc != null)
				cnc.close();
			cnc = null;
		}
		catch(SQLException e)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public Boolean connect(Login log)
	{
		String pfx = Prefix();
		String host = log.Host();
		String user = log.User();
		String pass = log.Pass();
		
		try
		{
			login = log;
			if(cnc != null)
			{
				cnc.close();
			}
			
			cnc = DriverManager.getConnection(pfx + host, user, pass);
			return cnc.isValid(0);
		}
		catch(SQLException e)
		{
			return false;
		}
	}
}