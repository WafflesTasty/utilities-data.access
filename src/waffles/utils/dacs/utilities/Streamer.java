package waffles.utils.dacs.utilities;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * A {@code Streamer} defines an {@code Iterable} over a {@code Stream}.
 * As it can throw {@code IOExceptions}, it should always be
 * handled using a try-with-resources. 
 * 
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Closeable
 * @see Iterable
 */
public class Streamer<O> implements Iterable<O>, Closeable
{
	private Stream<O> stream;
	
	/**
	 * Creates a new {@code Streamer}.
	 * 
	 * @param stream  a target stream
	 * 
	 * 
	 * @see Stream
	 */
	public Streamer(Stream<O> stream)
	{
		this.stream = stream;
	}
	
	
	@Override
	public void close() throws IOException
	{
		stream.close();
	}
	
	@Override
	public Iterator<O> iterator()
	{
		return stream.iterator();
	}
}