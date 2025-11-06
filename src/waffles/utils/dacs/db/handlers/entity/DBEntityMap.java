package waffles.utils.dacs.db.handlers.entity;

import java.util.Iterator;

import waffles.utils.dacs.db.schema.DBSchema;
import waffles.utils.dacs.utilities.db.DBGetter;
import waffles.utils.dacs.utilities.db.DBSetter;
<<<<<<< HEAD
import waffles.utils.dacs.utilities.db.format.DBLiteral;
import waffles.utils.dacs.utilities.db.format.DBToken;
import waffles.utils.dacs.utilities.db.format.maps.DBMap;
import waffles.utils.dacs.utilities.db.format.maps.DBPair;

/**
 * A {@code DBEntityMap} maps a {@code DBEntity} to database tokens.
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
	 * The {@code Pairs} class iterates over pairs in a {@code DBEntityHandler.Map}.
	 *
	 * @author Waffles
	 * @since 05 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @see DBEntity
	 * @see Iterator
	 * @see DBPair
	 */
	public class Pairs implements Iterator<DBPair>
	{
		private Iterator<DBLiteral> keys;
		
		/**
		 * Creates a new {@code Pairs}.
		 * 
		 * @param s  a database schema
		 * 
		 * 
		 * @see DBSchema
		 */
		public Pairs(DBSchema<?> s)
		{
			keys = s.Getters().iterator();
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
			DBToken val = DBEntityMap.this.get(key);
			return new DBPair(key, val);
		}
	}
	
	
	private DBEntity<?> ent;
	private DBSchema<? super E> scm;

	/**
	 * Creates a new {@code Map}.
	 * 
	 * @param e  a database entity
	 * @param s  a database schema
	 * 
	 * 
	 * @see DBEntity
	 * @see DBSchema
	 */
	public DBEntityMap(DBEntity<?> e, DBSchema<? super E> s)
	{
		ent = e;
		scm = s;
	}

	
	@Override
	public DBToken get(DBLiteral key)
	{
		DBGetter<? super E> g = scm.Getter(key);
		if(g != null)
		{
			return new DBToken(g.get((E) ent));			
		}
		
		return null;
	}
	
	@Override
	public DBToken put(DBLiteral key, DBToken val)
	{
		DBSetter<? super E> s = scm.Setter(key);
		if(s != null)
		{
			Object obj = val.Value();
			Object o = s.set((E) ent, obj);
			return new DBToken(o);
		}

		return null;
	}
	
	@Override
	public DBToken remove(DBLiteral key)
	{
		DBSetter<? super E> s = scm.Setter(key);
		if(s != null)
		{
			Object o = s.set((E) ent, null);
			return new DBToken(o);			
		}
		
		return null;
	}


	@Override
	public Iterable<DBPair> Tokens()
	{
		return Pairs();
	}
		
	@Override
	public Iterable<DBLiteral> Keys()
	{
		return scm.Getters();
=======
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;
import waffles.utils.dacs.utilities.db.tokens.maps.DBPair;

/**
 * A {@code DBEntityMap} maps a {@code DBEntity} to database tokens.
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
	 * The {@code Pairs} class iterates over pairs in a {@code DBEntityHandler.Map}.
	 *
	 * @author Waffles
	 * @since 05 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @see DBEntity
	 * @see Iterator
	 * @see DBPair
	 */
	public class Pairs implements Iterator<DBPair>
	{
		private Iterator<DBToken> keys;
		
		/**
		 * Creates a new {@code Pairs}.
		 * 
		 * @param s  a database schema
		 * 
		 * 
		 * @see DBSchema
		 */
		public Pairs(DBSchema<?> s)
		{
			keys = s.Keys().iterator();

		}
		
		
		@Override
		public boolean hasNext()
		{
			return keys.hasNext();
		}

		@Override
		public DBPair next()
		{
			DBToken key = keys.next();
			DBToken val = DBEntityMap.this.get(key);
			return new DBPair(key, val);
		}
	}
	
	
	private DBEntity<?> ent;
	private DBSchema<? super E> scm;

	/**
	 * Creates a new {@code Map}.
	 * 
	 * @param e  a database entity
	 * @param s  a database schema
	 * 
	 * 
	 * @see DBEntity
	 * @see DBSchema
	 */
	public DBEntityMap(DBEntity<?> e, DBSchema<? super E> s)
	{
		ent = e;
		scm = s;
	}

	
	@Override
	public DBToken get(DBToken key)
	{
		DBGetter<? super E> g = scm.Getter(key);
		
		return new DBToken(g.get((E) ent));
	}
	
	@Override
	public DBToken put(DBToken key, DBToken val)
	{
		DBSetter<? super E> s = scm.Setter(key);

		Object obj = val.Value();
		Object o = s.set((E) ent, obj);
		return new DBToken(o);
	}
	
	@Override
	public DBToken remove(DBToken key)
	{
		DBSetter<? super E> s = scm.Setter(key);
		
		Object o = s.set((E) ent, null);
		return new DBToken(o);
	}


	@Override
	public Iterable<DBPair> Tokens()
	{
		return Pairs();
>>>>>>> branch 'master' of https://github.com/WafflesTasty/utilities-data.access
	}
	
	@Override
	public Iterable<DBPair> Pairs()
	{
		return () -> new Pairs(scm);
	}

	@Override
	public int Count()
	{
		return scm.Count();
	}
}