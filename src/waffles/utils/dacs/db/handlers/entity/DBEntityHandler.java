package waffles.utils.dacs.db.handlers.entity;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.DBHandler;
import waffles.utils.dacs.db.schema.DBSchema;
<<<<<<< HEAD
import waffles.utils.dacs.utilities.db.DBRow;
import waffles.utils.dacs.utilities.db.format.DBLiteral;
import waffles.utils.dacs.utilities.db.format.DBToken;
import waffles.utils.dacs.utilities.db.format.maps.DBMap;

/**
 * A {@code DBEntityHandler} connects database rows to a {@code DBEntity} one-to-one.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 *
 * @see DBHandler
 */
public class DBEntityHandler implements DBHandler
{
	private DBEntity<?> ent;
	
	/**
	 * Creates a new {@code DBEntityHandler}.
	 * 
	 * @param e  a database entity
	 * 
	 * 
	 * @see DBEntity
	 */
	public DBEntityHandler(DBEntity<?> e)
	{
		ent = e;
	}

	
	@Override
	public boolean load(DBRow row, DBSchema<?> scm)
	{
		DBMap map = map(ent, scm);
		while(row.hasNext())
		{
			for(DBLiteral tkn : map.Keys())
=======
import waffles.utils.dacs.utilities.db.data.DBRow;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * A {@code DBEntityHandler} connects database rows to a {@code DBEntity} one-to-one.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 *
 * @see DBHandler
 */
public class DBEntityHandler implements DBHandler
{
	private DBEntity<?> ent;
	
	/**
	 * Creates a new {@code DBEntityHandler}.
	 * 
	 * @param e  a database entity
	 * 
	 * 
	 * @see DBEntity
	 */
	public DBEntityHandler(DBEntity<?> e)
	{
		ent = e;
	}

	
	@Override
	public boolean load(DBRow row, DBSchema<?> scm)
	{
		DBMap map = map(ent, scm);
		while(row.hasNext())
		{
			for(DBToken tkn : map.Keys())
>>>>>>> branch 'master' of https://github.com/WafflesTasty/utilities-data.access
			{
				Object o = row.get(tkn.condense());
				map.put(tkn, new DBToken(o));
			}
			
			return true;				
		}
		
		return false;
	}	
	
	@Override
	public DBMap map(DBHandleable<?> ent, DBSchema<?> scm)
	{
		return new DBEntityMap<>((DBEntity<?>) ent, scm);
	}
}