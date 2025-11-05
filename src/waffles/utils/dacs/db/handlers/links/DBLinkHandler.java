package waffles.utils.dacs.db.handlers.links;

import waffles.utils.dacs.db.handlers.DBHandleable;
import waffles.utils.dacs.db.handlers.DBHandler;
import waffles.utils.dacs.db.handlers.entity.DBEntity;
import waffles.utils.dacs.db.handlers.entity.DBEntityMap;
import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.data.DBRow;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * A {@code DBLinkHandler} connects database rows to children of a {@code DBLink} one-to-one.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 *
 * @see DBHandler
 */
public interface DBLinkHandler extends DBHandler
{
	/**
	 * Creates a new child entity for the {@code DBLinkHandler}.
	 * 
	 * @param row  a database row
	 * @param scm  a database schema
	 * @return  a child entity
	 * 
	 * 
	 * @see DBEntity
	 * @see DBSchema
	 * @see DBRow
	 */
	public abstract DBEntity<?> create(DBRow row, DBSchema<?> scm);
	
	
	@Override
	public default DBMap map(DBHandleable<?> ent, DBSchema<?> scm)
	{
		return new DBEntityMap<>((DBEntity<?>) ent, scm);
	}
	
	@Override
	public default boolean load(DBRow row, DBSchema<?> scm)
	{
		while(row.hasNext())
		{
			DBEntity<?> ent = create(row, scm);
			DBMap map = map(ent, scm);
			
			for(DBToken tkn : map.Keys())
			{
				Object o = row.get(tkn.condense());
				map.put(tkn, new DBToken(o));
			}
		}
		
		return true;
	}
}