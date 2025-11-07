package waffles.utils.dacs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.utilities.DataLink;
import waffles.utils.dacs.utilities.db.DBLoader;
import waffles.utils.dacs.utilities.db.DBLogin;
import waffles.utils.dacs.utilities.db.sql.SQLError;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;

/**
 * The {@code Database} class provides access to an entity-based SQL database.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @see DBAccess
 * @see DataLink
 * @see DBLogin
 */
public abstract class Database<A extends DBAccess<?>> implements DataLink<DBLogin, Boolean>
{
	private String prefix;
	private Connection cnc;
	private DBLogin login;
	
	/**
	 * Creates a new {@code Database}.
	 * 
	 * @param pfx  a host prefix
	 */
	public Database(String pfx)
	{
		prefix = pfx;
	}
	
	/**
	 * Queries data in the {@code Database}.
	 * 
	 * @param sql  an sql string
	 * @return  a row iterator
	 * 
	 * 
	 * @see DBRow
	 */
	public DBRow query(String sql)
	{
		try
		{
			Statement s = cnc.createStatement();
			ResultSet set = s.executeQuery(sql);
			return new DBRow(set);
		}
		catch (SQLException e)
		{
			throw new SQLError(sql);
		}
	}
	
	/**
	 * Updates data in the {@code Database}.
	 * 
	 * @param sql  an sql string
	 * @return  {@code true} if successful
	 */
	public boolean update(String sql)
	{
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
	 * Deletes a {@code DBAccessible} from the {@code Database}.
	 * 
	 * @param acs  a database access
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 */
	public <B extends A> boolean delete(B acs, DBSchema<? super B> scm)
	{
		SQLFormat<?> fmt = scm.Formatter(SQLOps.DELETE);
		return update(fmt.castAndParse(acs));
	}
	
	/**
	 * Fetches a {@code DBAccessible} from the {@code Database}.
	 * 
	 * @param acs  a database access
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 */
	public <B extends A> boolean exists(B acs, DBSchema<? super B> scm)
	{
		SQLFormat<?> fmt = scm.Formatter(SQLOps.EXISTS);
		return query(fmt.castAndParse(acs)).hasNext();
	}
	
	/**
	 * Inserts a {@code DBAccessible} into the {@code Database}.
	 * 
	 * @param acs  a database access
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 */
	public <B extends A> boolean insert(B acs, DBSchema<? super B> scm)
	{
		SQLFormat<?> fmt = scm.Formatter(SQLOps.INSERT);
		return update(fmt.castAndParse(acs));
	}
	
	/**
	 * Selects a {@code DBAccessible} from the {@code Database}.
	 * 
	 * @param acs  a database access
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 */
	public <B extends A> boolean select(B acs, DBSchema<? super B> scm)
	{
		DBLoader<? super B> loader = scm.Loader();
		SQLFormat<?> fmt = scm.Formatter(SQLOps.SELECT);
		DBRow data = query(fmt.castAndParse(acs));
		return loader.load(acs, data);
	}
	
	/**
	 * Updates a {@code DBAccessible} into the {@code Database}.
	 * 
	 * @param acs  a database access
	 * @param scm  a database schema
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBSchema
	 */
	public <B extends A> boolean update(B acs, DBSchema<? super B> scm)
	{
		SQLFormat<?> fmt = scm.Formatter(SQLOps.UPDATE);
		return update(fmt.castAndParse(acs));
	}
	
	
	/**
	 * Rolls back {@code Database} changes.
	 * 
	 * @return  {@code true} if successful
	 */
	public boolean rollback()
	{
		try
		{
			if(cnc != null)
			{
				cnc.rollback();
				return true;
			}
			
			return false;
		}
		catch (SQLException e)
		{
			throw new SQLError(login);
		}
	}
	
	/**
	 * Commits {@code Database} changes.
	 * 
	 * @return  {@code true} if successful
	 */
	public boolean commit()
	{
		try
		{
			if(cnc != null)
			{
				cnc.commit();
				return true;
			}
			
			return false;
		}
		catch (SQLException e)
		{
			throw new SQLError(login);
		}
	}


	@Override
	public boolean disconnect(DBLogin l)
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
	public Boolean connect(DBLogin l)
	{
		String db = l.Database();
		String host = l.Host();
		String user = l.User();
		String pass = l.Pass();
		
		try
		{
			login = l;
			if(cnc != null)
			{
				cnc.close();
			}
			
			cnc = DriverManager.getConnection(prefix + host + "/" + db, user, pass);
			return cnc.isValid(0);
		}
		catch(SQLException e)
		{
			return false;	
		}
	}
	
	@Override
	public Boolean connect()
	{
		return connect(login);
	}
}