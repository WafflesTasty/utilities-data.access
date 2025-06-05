package waffles.utils.dacs.files.tokens.third.csv;

import java.util.Iterator;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Storage;
import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.files.tokens.third.csv.handlers.CSVReader;
import waffles.utils.dacs.files.tokens.third.csv.handlers.CSVWriter;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVComment;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVData;
import waffles.utils.dacs.utilities.errors.AccessError;
import waffles.utils.dacs.utilities.errors.FormatError;
import waffles.utils.dacs.utilities.errors.ParserError;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.sets.queues.ordered.BSQueue;

/**
 * A {@code CSVFile} defines a data structure for the {@code CSV} file format.
 *
 * @author Waffles
 * @since 05 Jun 2025
 * @version 1.1
 *
 * 
 * @see CSVData
 * @see Storage
 * @see List
 */
public class CSVFile extends List<CSVData> implements Storage
{
	/**
	 * The {@code Hints} interface defines settings for a {@code CSVFile}.
	 *
	 * @author Waffles
	 * @since 04 Jun 2025
	 * @version 1.1
	 */
	public static interface Hints
	{
		/**
		 * Returns the column count of the {@code Hints}.
		 * 
		 * @return  a column count
		 */
		public abstract int Columns();
		
		/**
		 * Returns the header state of the {@code Hints}.
		 * 
		 * @return  a header state
		 */
		public default boolean hasHeader()
		{
			return false;
		}
		
		/**
		 * Returns the separator of the {@code Hints}.
		 * 
		 * @return  a separator
		 */
		public default char Separator()
		{
			return CSVData.SEPARATOR;
		}
	}
	
	/**
	 * The {@code Rows} class iterates over all rows in a {@code CSVFile}.
	 *
	 * @author Waffles
	 * @since 05 Jun 2025
	 * @version 1.1
	 *
	 * 
	 * @see Iterator
	 * @see CSVRow
	 */
	public class Rows implements Iterator<CSVRow>
	{
		private CSVComment cNext;
		private Iterator<CSVComment> cmts;
		private int dNext, iNext;
		
		/**
		 * Creates a new {@code Rows}.
		 */
		public Rows()
		{
			dNext = iNext = 0;
			cmts = comms.iterator();
			if(cmts.hasNext())
			{
				cNext = cmts.next();
			}
		}


		@Override
		public boolean hasNext()
		{
			return dNext < Count() || cNext != null;
		}

		@Override
		public CSVRow next()
		{
			if(cNext != null)
			{
				if(iNext == cNext.Line())
				{
					CSVRow next = cNext;
					if(cmts.hasNext())
						cNext = cmts.next();
					else
						cNext = null;
					
					iNext++;
					return next;
				}
			}
			
			if(dNext < Count())
			{
				iNext++;
				return get(dNext++);
			}

			return new CSVComment("");
		}
	}


	private Hints hints;
	private BSQueue<CSVComment> comms;
	private CSVData header;
	
	/**
	 * Creates a new {@code CSVFile}.
	 * 
	 * @param cols  a column count
	 */
	public CSVFile(int cols)
	{
		this(() -> cols);
	}
	
	/**
	 * Creates a new {@code CSVFile}.
	 * 
	 * @param h  file hints
	 * 
	 * 
	 * @see Hints
	 */
	public CSVFile(Hints h)
	{
		hints = h;
		
		comms = new BSQueue<>();
		if(h.hasHeader())
		{
			header = new CSVData();
		}
	}
	

	
	/**
	 * Returns a row count of the {@code CSVFile}.
	 * 
	 * @return  a row count
	 */
	public int RowCount()
	{
		if(Hints().hasHeader())
			return comms.Count() + Count() + 1;
		return comms.Count() + Count();
	}

	/**
	 * Adds a comment line to the {@code CSVFile}.
	 * 
	 * @param s  a comment line
	 */
	public void comment(String s)
	{
		CSVComment cmt = new CSVComment(s);
		cmt.setLine(RowCount());
		comms.push(cmt);
	}

	/**
	 * Adds an object row to the {@code CSVFile}.
	 * 
	 * @param row  an object row
	 * @throws FormatError  if the row is of wrong length
	 * 
	 * 
	 * @see FormatError
	 */
	public void add(Object... row) throws FormatError
	{
		int count = Hints().Columns();
		if(row.length != count)
		{
			throw new FormatError("The csv file expects " + count + " values but received " + row.length + ".");
		}
		
		
		CSVData data = new CSVData();
		for(int i = 0; i < count; i++)
		{
			data.add(new StringToken(row[i]));
		}
		
		add(data);
	}
	
	/**
	 * Returns the header of the {@code CSVFile}.
	 * 
	 * @return  a data header
	 * 
	 * 
	 * @see CSVData
	 */
	public CSVData Header()
	{
		return header;
	}
	
	/**
	 * Returns the hints of the {@code CSVFile}.
	 * 
	 * @return  file hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return hints;
	}

	
	boolean validate(int line, CSVData row)
	{
		int curr = row.Count();
		int cols = Hints().Columns();
		if(curr != cols)
		{
			throw new ParserError("Line " + line + " has " + curr + " values instead of " + cols + ".");
		}
		
		return true;
	}

	@Override
	public void write(File file) throws AccessError
	{
		CSVWriter writer = new CSVWriter();
		writer.write(() -> new Rows(), file);
	}
	
	@Override
	public void read(File file) throws AccessError, ParserError
	{
		clear();
		
		int line = 0;
		CSVReader reader = new CSVReader(hints);
		for(CSVRow row : reader.read(file))
		{
			switch(row.Type())
			{
			case COMMENT:
			{
				CSVComment cmt = (CSVComment) row;
				
				cmt.setLine(line);
				comms.push(cmt);
				
				break;
			}
			case DATA:
			{
				CSVData data = (CSVData) row;
				if(isEmpty())
				{
					
					if(Hints().hasHeader())
					{
						header = (CSVData) row;
						validate(line, header);
						break;
					}
				}
				
				validate(line, data);
				add(data);
				break;
			}
			default:
				break;
			}
			
			line++;
		}
	}
	
	@Override
	public void clear()
	{
		super.clear();
		comms.clear();
	}
}