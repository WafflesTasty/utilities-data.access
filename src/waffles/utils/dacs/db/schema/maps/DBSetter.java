package waffles.utils.dacs.db.schema.maps;

import java.sql.ResultSet;

import waffles.utils.dacs.db.entities.DBEntity;
import waffles.utils.dacs.db.entities.DBEntity.Setter;
import waffles.utils.dacs.db.schema.DBMap;
import waffles.utils.lang.tokens.primitive.LiteralToken;

/**
 * A {@code DBSetter} maps table columns to {@code Setter} objects.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @param <E>  an entity type
 * @see DBEntity
 * @see Setter
 * @see DBMap
 */
public class DBSetter<E extends DBEntity<?>> extends DBMap<Setter<E>>
{
	/**
	 * Updates a {@code DBEntity} with a {@code ResultSet}.
	 * 
	 * @param e  an entity
	 * @param r  a result set
	 * @return  {@code true} if successful
	 * 
	 * 
	 * @see ResultSet
	 * @see DBEntity
	 */
	public boolean update(E e, ResultSet r)
	{
		for(LiteralToken tkn : Keys())
		{
			if(!get(tkn).set(e, r))
			{
				return false;
			}
		}
		
		return true;
	}
}