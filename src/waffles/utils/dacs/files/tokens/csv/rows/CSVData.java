package waffles.utils.dacs.files.tokens.csv.rows;

import waffles.utils.dacs.files.tokens.csv.CSVFile;
import waffles.utils.dacs.files.tokens.csv.CSVRow;
import waffles.utils.dacs.utilities.files.FormatError;
import waffles.utils.lang.tokens.ListToken;
import waffles.utils.lang.tokens.format.ListFormat;
import waffles.utils.lang.tokens.parsers.Parsable;
import waffles.utils.lang.tokens.parsers.cyclic.CyclicParser;
import waffles.utils.lang.tokens.parsers.cyclic.CyclicParser.Hints;
import waffles.utils.lang.tokens.values.primitive.StringToken;
import waffles.utils.sets.countable.wrapper.JavaList;
import waffles.utils.sets.indexed.IndexedSet;

/**
 * A {@code CSVData} defines a {@code CSVRow} containing a single line of csv data.
 * The row is implemented as a formattable {@code List} of {@code StringToken} objects.
 * Formatting is done through the {@code ListFormattable} interface, which makes
 * use of an empty delimiter and a semicolon ';' as a separator.
 *
 * @author Waffles
 * @since 04 Jun 2025
 * @version 1.1
 *
 * 
 * @see StringToken
 * @see IndexedSet
 * @see ListToken
 */
public class CSVData extends CSVRow implements IndexedSet<StringToken>, ListToken<StringToken>
{
	/**
	 * Defines a default delimiter for a {@code CSVData}.
	 */
	public static final char DELIMITER = ' ';
	/**
	 * Defines a default separator for a {@code CSVData}.
	 */
	public static final char SEPARATOR = ';';
	
		
	/**
	 * The {@code FormatHints} define format hints for {@code CSVData}.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @see ListFormat
	 */
	public static class FormatHints implements ListFormat.Hints
	{
		@Override
		public char Separator()
		{
			return SEPARATOR;
		}

		@Override
		public Character LowerBound()
		{
			return DELIMITER;
		}

		@Override
		public Character UpperBound()
		{
			return DELIMITER;
		}
	}

	/**
	 * The {@code ParserHints} define parser hints for {@code CSVData}.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @see CyclicParser
	 * @see StringToken
	 */
	public static class ParserHints implements CyclicParser.Hints<StringToken>
	{
		private CSVFile.Hints hints;
		
		/**
		 * Creates a new {@code ParserHints}.
		 * 
		 * @param h  file hints
		 * 
		 * 
		 * @see CSVFile
		 */
		public ParserHints(CSVFile.Hints h)
		{
			hints = h;
		}
		
		
		@Override
		public char Separator()
		{
			return hints.Separator();
		}
		
		@Override
		public StringToken.Parser Parser()
		{
			return new StringToken.Parser();
		}
				
		@Override
		public int Maximum()
		{
			return hints.Columns();
		}
	}
	
	/**
	 * A {@code CSVData.Parser} parses a row of csv data.
	 * Each row contains a set amount of {@code StringToken}
	 * values, which are separated by a semicolon, i.e. ';'.
	 *
	 * @author Waffles
	 * @since 22 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see Parsable
	 * @see CSVData
	 */
	public static class Parser implements Parsable<CSVData>
	{
		private CSVFile.Hints hints;
		private CyclicParser<StringToken> token;
		
		/**
		 * Creates a new {@code Parser}.
		 * 
		 * @param h  file hints
		 * 
		 * 
		 * @see Hints
		 */
		public Parser(CSVFile.Hints h)
		{
			token = new CyclicParser<>(new ParserHints(h));
			hints = h;
		}


		@Override
		public CSVData generate()
		{
			JavaList<StringToken> list = token.generate();
			int count = hints.Columns();
			if(list.Count() != count)
			{
				throw new FormatError("The csv file expects " + count + " values but received " + list.Count() + ".");
			}
			
			CSVData data = new CSVData();
			for(int i = 0; i < list.Count(); i++)
			{
				data.add(list.get(i));
			}
			
			return data;
		}
		
		@Override
		public boolean consume(Character c)
		{
			return token.consume(c);
		}
		
		@Override
		public void reset()
		{
			token.reset();
		}
	}
	
	
	private JavaList<StringToken> data;
	
	/**
	 * Creates a new {@code CSVData}.
	 */
	public CSVData()
	{
		data = new JavaList<>();
	}
	
	/**
	 * Adds a token to the {@code CSVData}.
	 * 
	 * @param tkn  a string token
	 * 
	 * 
	 * @see StringToken
	 */
	public void add(StringToken tkn)
	{
		data.add(tkn);
	}
	
	/**
	 * Puts a token into the {@code CSVData}.
	 * 
	 * @param tkn  a string token
	 * @param col  a column index
	 */
	public void put(StringToken tkn, int col)
	{
		data.put(tkn, col);
	}


	@Override
	public StringToken get(int... coords)
	{
		return data.get(coords);
	}
	
	@Override
	public ListFormat<StringToken> Formatter()
	{
		return new ListFormat<>(new FormatHints());
	}
	
	@Override
	public Iterable<StringToken> Tokens()
	{
		return data;
	}
	
	@Override
	public int[] Dimensions()
	{
		return data.Dimensions();
	}
	
	@Override
	public Type Type()
	{
		return Type.DATA;
	}
}