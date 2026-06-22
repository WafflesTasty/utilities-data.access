package waffles.utils.dacs.db.access.maps;

import java.util.Iterator;

import waffles.utils.dacs.db.access.DBAccess;
import waffles.utils.dacs.db.access.DBSchema;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBSetter;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.dacs.utilities.db.tokens.maps.DBPair;
import waffles.utils.sets.utilities.keymaps.queries.MapQuery;

/**
 * A {@code DBAccessMap} maps a {@code DBAccess} into a {@code Database}.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 * 
 * @param <A>  an access type
 * @see DBAccess
 * @see DBMap
 */
public class DBAccessMap<A extends DBAccess<?>> implements DBMap
{
	/**
	 * The {@code Pairs} class iterates over {@code DBAccessMap} pairs.
	 *
	 * @author Waffles
	 * @since 05 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @see Iterator
	 * @see DBPair
	 */
	public static class Pairs implements Iterator<DBPair>
	{
		private DBAccessMap<?> map;
		private Iterator<DBLiteral> keys;
		
		/**
		 * Creates a new {@code Pairs}.
		 * 
		 * @param m  an entity map
		 * 
		 * 
		 * @see DBTable
		 */
		public Pairs(DBAccessMap<?> m)
		{
			keys = m.Keys().iterator();
			map = m;
		}
		
		
		@Override
		public boolean hasNext()
		{
			return keys.hasNext();
		}

		@Override
		public DBPair next()
		{
			DBLiteral key = keys.next();
			DBToken val = map.get(key);
			return new DBPair(key, val);
		}
	}
	
	
	private DBAccess<?> acs;
	private DBSchema<A> scm;

	/**
	 * Creates a new {@code DBAccessMap}.
	 * 
	 * @param s  a database schema
	 * @param a  a database access
	 * 
	 * 
	 * @see DBAccess
	 * @see DBSchema
	 */
	public DBAccessMap(DBSchema<A> s, DBAccess<?> a)
	{
		scm = s;
		acs = a;
	}

	
	@Override
	public MapQuery<DBLiteral, DBToken> Query()
	{
		return () -> new Pairs(this);
	}
		
	@Override
	public Iterable<DBLiteral> Keys()
	{
		return scm.Getter().Keys();
	}
	

	@Override
	public DBToken remove(DBLiteral k)
	{
		DBSetter.Map<? super A> map = scm.Setter();
		DBSetter<? super A> s = map.get(k);
		if(s != null)
		{
			Object o = s.set((A) acs, null);
			return new DBToken(o);			
		}
		
		return null;
	}
				
	@Override
	public DBToken put(DBLiteral k, DBToken v)
	{
		DBSetter.Map<? super A> map = scm.Setter();
		DBSetter<? super A> s = map.get(k);
		if(s != null)
		{
			Object obj = v.Value();
			Object o = s.set((A) acs, obj);
			return new DBToken(o);
		}

		return null;
	}
		
	@Override
	public DBToken get(DBLiteral k)
	{
		DBGetter.Map<? super A> map = scm.Getter();
		DBGetter<? super A> g = map.get(k);
		if(g != null)
		{
			return new DBToken(g.get((A) acs));			
		}
		
		return null;
	}
	
	@Override
	public int Count()
	{
		return scm.Getter().Count();
	}
}