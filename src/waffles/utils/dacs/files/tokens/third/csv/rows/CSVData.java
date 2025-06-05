package waffles.utils.dacs.files.tokens.third.csv.rows;

import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.files.tokens.third.csv.CSVFile.Hints;
import waffles.utils.dacs.files.tokens.third.csv.CSVRow;
import waffles.utils.dacs.utilities.errors.FormatError;
import waffles.utils.dacs.utilities.formats.ListFormat;
import waffles.utils.dacs.utilities.formats.ListFormattable;
import waffles.utils.dacs.utilities.parsers.CyclicParser;
import waffles.utils.dacs.utilities.parsers.DemiParser;
import waffles.utils.dacs.utilities.parsers.tokens.StringTokenParser;
import waffles.utils.sets.indexed.delegate.List;

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
 * @see ListFormattable
 * @see StringToken
 * @see CSVRow
 * @see List
 */
public class CSVData extends List<StringToken> implements CSVRow, ListFormattable<StringToken>
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
	 * A {@code CSVData.Parser} parses a row of csv data.
	 * Each row contains a set amount of {@code StringToken}
	 * values, which are separated by a semicolon, i.e. ';'.
	 *
	 * @author Waffles
	 * @since 22 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see DemiParser
	 * @see StringToken
	 */
	public static class Parser implements DemiParser<List<StringToken>, CSVData>
	{
		private Hints hints;
		private CyclicParser<StringToken> token;
		
		/**
		 * Creates a new {@code Parser}.
		 * 
		 * @param h  file hints
		 * 
		 * 
		 * @see Hints
		 */
		public Parser(Hints h)
		{
			hints = h;
			token = new CyclicParser<>(h.Separator())
			{
				@Override
				public StringTokenParser<StringToken> produce()
				{
					return new StringTokenParser.Base();
				}
			};
		}


		@Override
		public List<StringToken> Input()
		{
			return token.generate();
		}

		@Override
		public CSVData generate(List<StringToken> list)
		{
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


	@Override
	public List<StringToken> FormatList()
	{
		return this;
	}
		
	@Override
	public ListFormat<StringToken> Formatter(char upper, char lower)
	{
		return Formatter(upper, SEPARATOR, lower);
	}
	
	@Override
	public ListFormat<StringToken> Formatter()
	{
		return Formatter(DELIMITER, DELIMITER);
	}
		
	@Override
	public CSVRow.Type Type()
	{
		return CSVRow.Type.DATA;
	}
}