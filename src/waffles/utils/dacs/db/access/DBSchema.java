package waffles.utils.dacs.db.access;

import waffles.utils.dacs.db.DBRow;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.format.SQLAccessDelete;
import waffles.utils.dacs.db.access.format.SQLAccessExists;
import waffles.utils.dacs.db.access.format.SQLAccessInsert;
import waffles.utils.dacs.db.access.format.SQLAccessSelect;
import waffles.utils.dacs.db.access.format.SQLAccessUpdate;
import waffles.utils.dacs.db.access.maps.DBAccessMap;
import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBSetter;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * A {@code DBSchema} mediates between a {@code Database} and a {@code DBAccess}.
 * It is designed to generate SQL strings which read or write access objects.
 *
 * @author Waffles
 * @since 07 Nov 2025
 * @version 1.1
 *
 *
 * @param <A>  an access type
 * @see DBAccess
 */
public interface DBSchema<A extends DBAccess<?>>
{
	/**
	 * Returns the master of the {@code DBSchema}.
	 * 
	 * @return  a master table
	 * 
	 * 
	 * @see DBTable
	 */
	public abstract DBTable<?> Master();

	/**
	 * Returns the getter of the {@code DBSchema}.
	 * 
	 * @return  a database getter map
	 * 
	 * 
	 * @see DBGetter
	 */
	public abstract DBGetter.Map<A> Getter();
	
	/**
	 * Returns the setter of the {@code DBSchema}.
	 * 
	 * @return  a database setter map
	 * 
	 * 
	 * @see DBSetter
	 */
	public abstract DBSetter.Map<A> Setter();
	
	
	/**
	 * Loads an object with data in a {@code DBSchema}.
	 * 
	 * @param acs  a database access
	 * @param row  a database row
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see DBRow
	 */
	public default boolean load(A acs, DBRow row)
	{
		DBMap map = Map(acs);
		if(row.hasNext())
		{
			DBRow r = row.next();
			for(DBLiteral tkn : Setter().Keys())
			{
				Object o = r.get(tkn.condense());
				map.put(tkn, new DBToken(o));
			}
			
			return true;				
		}
		
		return false;
	}
	
	/**
	 * Creates an SQL formatter from the {@code DBSchema}.
	 * 
	 * @param ops  a database operation
	 * @return  an SQL formatter
	 * 
	 * 
	 * @see SQLFormat
	 * @see SQLOps
	 */
	public default SQLFormat<?> Formatter(SQLOps ops)
	{
		switch(ops)
		{
		case DELETE:
			return new SQLAccessDelete(this);
		case EXISTS:
			return new SQLAccessExists(this);
		case INSERT:
			return new SQLAccessInsert(this);				
		case SELECT:
			return new SQLAccessSelect(this);
		case UPDATE:
			return new SQLAccessUpdate(this);
		default:
			return null;
		}
	}
		
	/**
	 * Creates a database map for the {@code DBSchema}.
	 * 
	 * @param acs  a database access
	 * @return  a database map
	 * 
	 * 
	 * @see DBAccess
	 * @see DBMap
	 */
	public default DBMap Map(DBAccess<?> acs)
	{
		return new DBAccessMap<>(this, acs);
	}
}