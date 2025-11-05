package waffles.utils.dacs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.DBHandler;
import waffles.utils.dacs.db.operations.SQLDelete;
import waffles.utils.dacs.db.operations.SQLExists;
import waffles.utils.dacs.db.operations.SQLInsert;
import waffles.utils.dacs.db.operations.SQLSelect;
import waffles.utils.dacs.db.operations.SQLUpdate;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.DataLink;
import waffles.utils.dacs.utilities.db.data.DBRow;
import waffles.utils.dacs.utilities.db.errors.SQLError;

/**
 * The {@code Database} class provides access to an entity-based SQL database.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @param <H>  a handler type
 * @see DBHandleable
 * @see DataLink
 * @see DBLogin
 */
public abstract class Database<H extends DBHandleable<?>> implements DataLink<DBLogin, Boolean>
{
	private DBLogin login;
	private Connection cnc;

	@Override
	public Boolean connect()
	{
		return connect(login);
	}
	
	@Override
	public Boolean connect(DBLogin log)
	{
		String pfx = Prefix();
		String db = log.Database();
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
			
			cnc = DriverManager.getConnection(pfx + host + "/" + db, user, pass);
			return cnc.isValid(0);
		}
		catch(SQLException e)
		{
			return false;	
		}
	}
	
	@Override
	public boolean disconnect(DBLogin log)
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


	/**
	 * Deletes an entity from the {@code Database}.
	 * 
	 * @param ent  a database entity
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 */
	public <G extends H> boolean delete(G ent, DBSchema<? super G> scm)
	{
		SQLDelete del = new SQLDelete(ent);
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
	 * Finds an entity within the {@code Database}.
	 * 
	 * @param ent  a database entity
	 * @param scm  a database schema
	 * @return  {@code true} if exists
	 * 
	 * 
	 * @see DBSchema
	 */
	public <G extends H> boolean exists(G ent, DBSchema<? super G> scm)
	{
		SQLExists exi = new SQLExists(ent);
		String sql = exi.parse(scm);
		
		try
		{
			Statement s = cnc.createStatement();
			ResultSet r = s.executeQuery(sql);
			return r.next();
		}
		catch (SQLException e)
		{
			throw new SQLError(sql);
		}
	}
	
	/**
	 * Inserts an entity into the {@code Database}.
	 * 
	 * @param ent  a database entity
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 */
	public <G extends H> boolean insert(G ent, DBSchema<? super G> scm)
	{
		SQLInsert ins = new SQLInsert(ent);
		String sql = ins.parse(scm);
		
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
	 * 
	 * 
	 * @see DBSchema
	 */
	public <G extends H> boolean select(G ent, DBSchema<? super G> scm)
	{
		SQLSelect sel = new SQLSelect(ent);
		String sql = sel.parse(scm);
		
		try
		{
			Statement s = cnc.createStatement();
			ResultSet set = s.executeQuery(sql);
			
			DBHandler h = ent.Handler();
			DBRow row = new DBRow(set);
			return h.load(row, scm);
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
	 * 
	 * 
	 * @see DBSchema
	 */
	public <G extends H> boolean update(G ent, DBSchema<? super G> scm)
	{
		SQLUpdate upd = new SQLUpdate(ent);
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

	
	/**
	 * Returns the prefix of the {@code Database}.
	 * 
	 * @return  a host prefix
	 */
	public abstract String Prefix();
	

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
}