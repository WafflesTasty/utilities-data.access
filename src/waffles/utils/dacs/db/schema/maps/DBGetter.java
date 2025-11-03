package waffles.utils.dacs.db.schema.maps;

import java.util.Iterator;

import waffles.utils.dacs.db.DBEntity;
import waffles.utils.dacs.db.DBEntity.Getter;
import waffles.utils.dacs.db.DBEntity.Value;
import waffles.utils.dacs.db.schema.DBMap;
import waffles.utils.lang.tokens.ListToken;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.primitive.LiteralToken;
import waffles.utils.lang.tokens.primitive.StringToken;
import waffles.utils.tools.patterns.properties.values.Valuable;

/**
 * A {@code DBGetter} maps table columns to {@code Getter} objects.
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 *
 * @param <E>  an entity type
 * @see DBEntity
 * @see Getter
 * @see DBMap
 */
public class DBGetter<E extends DBEntity<?>> extends DBMap<Getter<E>>
{
	/**
	 * The {@code Tokens} iterator traverses all values of a {@code DBEntity}.
	 *
	 * @author Waffles
	 * @since 03 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see Iterator
	 * @see Token
	 */
	public class Tokens implements Iterator<Token>
	{
		private E entity;
		private Iterator<LiteralToken> keys;
		
		/**
		 * Creates new {@code Tokens}.
		 * 
		 * @param e  an entity
		 */
		public Tokens(E e)
		{
			keys = Keys().iterator();
			entity = e;
		}

				
		@Override
		public boolean hasNext()
		{
			return keys.hasNext();
		}

		@Override
		public Token next()
		{
			Getter<E> g = get(keys.next());
			return g.get(entity);
		}
	}

	
	/**
	 * Puts a key-value pair into the {@code DBGetter}.
	 * A {@code Valuable} object is wrapped in a {@code ValueToken}.
	 * 
	 * @param key  a map key
	 * @param val  a map value
	 * @return  an old value
	 * 
	 * 
	 * @see Getter
	 * @see Value
	 */
	public Getter<E> put(String key, Value<E> val)
	{
		return put(key, (Getter<E>) e -> new StringToken(val.get(e))
		{
			@Override
			public Format<? extends Valuable<?>> Formatter()
			{
				return Formatter(s -> true, '\'');
			}	
		});
	}
	
	/**
	 * Returns a {@code ListToken} of entity values.
	 * 
	 * @param e  an entity
	 * @return  a list token
	 * 
	 * 
	 * @see ListToken
	 * @see Token
	 */
	public ListToken<Token> Values(E e)
	{
		return new ListToken<>()
		{
			@Override
			public Iterable<Token> Tokens()
			{
				return () -> new Tokens(e);
			}
			
			@Override
			public Format<?> Formatter()
			{
				return Formatter(',');
			}
		};
	}
}