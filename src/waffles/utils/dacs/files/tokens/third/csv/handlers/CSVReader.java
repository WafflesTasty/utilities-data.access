package waffles.utils.dacs.files.tokens.third.csv.handlers;

import java.util.Iterator;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.files.plaintext.strings.StringReader;
import waffles.utils.dacs.files.tokens.parsers.ChoiceParser;
import waffles.utils.dacs.files.tokens.third.csv.CSVFile.Hints;
import waffles.utils.dacs.files.tokens.third.csv.CSVRow;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVComment;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVData;
import waffles.utils.lang.Strings;

/**
 * A {@code CSVReader} reads {@code CSVRows} from a {@code File}.
 * These are stored as lines of data in a {@code CSVFile}.
 *
 * @author Waffles
 * @since 04 Jun 2025
 * @version 1.1
 *
 * 
 * @see Reader
 * @see Iterable
 * @see CSVRow
 */
public class CSVReader implements Reader<Iterable<CSVRow>>
{
	/**
	 * A {@code Parser} parses text strings for a {@code CSVReader}.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see ChoiceParser
	 * @see CSVRow
	 */
	public class Parser extends ChoiceParser<CSVRow, CSVRow>
	{
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			add(new CSVComment.Parser());
			add(new CSVData.Parser(hints));
		}

		
		@Override
		public CSVRow compute(CSVRow r)
		{
			return r;
		}
	}
	
	/**
	 * The {@code Lines} class iterates rows for a {@code CSVReader}.
	 *
	 * @author Waffles
	 * @since 04 Jun 2025
	 * @version 1.1
	 *
	 * 
	 * @see Iterator
	 * @see CSVRow
	 */
	public class Lines implements Iterator<CSVRow>
	{
		private Parser parser;
		private StringReader reader;
		private Iterator<String> lines;
		
		/**
		 * Creates a new {@code Lines}.
		 * 
		 * @param file  a source file
		 * 
		 * 
		 * @see File
		 */
		public Lines(File file)
		{
			parser = new Parser();
			reader = new StringReader();
			lines = reader.read(file).iterator();
		}

		
		@Override
		public boolean hasNext()
		{
			return lines.hasNext();
		}

		@Override
		public CSVRow next()
		{
			parser.reset();
			String s = lines.next();
			for(char c : Strings.iterate(s))
			{
				parser.consume(c);
			}

			return parser.generate();
		}
		
	}

	
	private Hints hints;

	/**
	 * Creates a new {@code CSVReader}.
	 * 
	 * @param h  file hints
	 * 
	 * 
	 * @see Hints
	 */
	public CSVReader(Hints h)
	{
		hints = h;
	}


	@Override
	public Iterable<CSVRow> read(File file)
	{
		return () -> new Lines(file);
	}
}