package waffles.utils.dacs.db.access.entity;

import java.util.Iterator;

import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBSetter;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.dacs.utilities.db.tokens.maps.DBPair;

/**
 * A {@code DBEntityMap} maps a {@code DBEntity} into a {@code Database}.
 *
 * @author Waffles
 * @since 05 Nov 2025
 * @version 1.1
 *
 * 
 * @param <E>  an entity type
 * @see DBEntity
 * @see DBMap
 */
public class DBEntityMap<E extends DBEntity<?>> implements DBMap
{
	/**
	 * The {@code Pairs} class iterates over {@code DBEntityMap} pairs.
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
		private DBEntityMap<?> map;
		private Iterator<DBLiteral> keys;
		
		/**
		 * Creates a new {@code Pairs}.
		 * 
		 * @param m  an entity map
		 * 
		 * 
		 * @see DBTable
		 */
		public Pairs(DBEntityMap<?> m)
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
	
	
	private DBTable<E> tbl;
	private DBEntity<?> ent;

	/**
	 * Creates a new {@code DBEntityMap}.
	 * 
	 * @param t  a database table
	 * @param e  a database entity
	 * 
	 * 
	 * @see DBEntity
	 * @see DBTable
	 */
	public DBEntityMap(DBTable<E> t, DBEntity<?> e)
	{
		ent = e;
		tbl = t;
	}

	
	@Override
	public DBToken get(DBLiteral key)
	{
		DBGetter<? super E> g = tbl.Getter(key);
		if(g != null)
		{
			return new DBToken(g.get((E) ent));			
		}
		
		return null;
	}
	
	@Override
	public DBToken remove(DBLiteral key)
	{
		DBSetter<? super E> s = tbl.Setter(key);
		if(s != null)
		{
			Object o = s.set((E) ent, null);
			return new DBToken(o);			
		}
		
		return null;
	}
	
	@Override
	public DBToken put(DBLiteral key, DBToken val)
	{
		DBSetter<? super E> s = tbl.Setter(key);
		if(s != null)
		{
			Object obj = val.Value();
			Object o = s.set((E) ent, obj);
			return new DBToken(o);
		}

		return null;
	}
	
	
	@Override
	public Iterable<DBLiteral> Keys()
	{
		return tbl.Getter().Keys();
	}
	
	@Override
	public Iterable<DBPair> Pairs()
	{
		return () -> new Pairs(this);
	}

	@Override
	public int Count()
	{
		return tbl.Getter().Count();
	}
}