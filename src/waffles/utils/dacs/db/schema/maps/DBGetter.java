package waffles.utils.dacs.db.schema.maps;

import java.util.Iterator;

import waffles.utils.dacs.db.DBEntity;
import waffles.utils.dacs.db.DBEntity.Getter;
import waffles.utils.dacs.db.schema.DBMap;
import waffles.utils.lang.tokens.ListToken;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.primitive.LiteralToken;
import waffles.utils.lang.tokens.primitive.wrapper.ValueToken;
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
	 * @see ValueToken
	 * @see Iterator
	 */
	public class Tokens implements Iterator<ValueToken<?>>
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
		public ValueToken<?> next()
		{
			Getter<E> g = get(keys.next());
			Valuable<?> v = () -> g.get(entity);
			return new ValueToken<>(v);
		}
	}
	
	/**
	 * Returns a {@code ListToken} of entity values.
	 * 
	 * @param e  an entity
	 * @return  a list token
	 * 
	 * 
	 * @see ValueToken
	 * @see ListToken
	 */
	public ListToken<ValueToken<?>> Values(E e)
	{
		return new ListToken<>()
		{
			@Override
			public Iterable<ValueToken<?>> Tokens()
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