package waffles.utils.dacs.files.plaintext.chars;

import java.util.Iterator;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.files.plaintext.strings.StringReader;

/**
 * A {@code CharReader} reads text files by iterating over each character in order.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 *
 * 
 * @see Iterable
 * @see Iterator
 * @see Reader
 */
public class CharReader implements Iterator<Character>, Reader<Iterable<Character>>
{	
	private Iterator<String> lines;

	@Override
	public Iterable<Character> read(File file)
	{
		index = 0;
		
		lines = new StringReader().read(file).iterator();
		return () -> this;
	}	

	
	private int index;
	private String curr;

	@Override
	public boolean hasNext()
	{
		return curr != null || lines.hasNext();
	}

	@Override
	public Character next()
	{
		if(curr == null)
		{
			curr = lines.next();
			index = 0;
		}
		
		char next = curr.charAt(index++);
		if(index == curr.length())
		{
			index = 0;
		}

		return next;
	}
}