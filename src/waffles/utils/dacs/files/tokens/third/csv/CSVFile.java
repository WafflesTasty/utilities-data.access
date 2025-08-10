package waffles.utils.dacs.files.tokens.third.csv;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Storage;
import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVComment;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVData;
import waffles.utils.dacs.utilities.errors.FormatError;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.tools.patterns.operator.Operable;

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
public class CSVFile extends List<CSVData> implements Operable<CSVFile>, Storage
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
	

	private Hints hints;
	private CSVData header;
	private CSVKernel kernel;
		
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
		kernel = new CSVKernel(this);
		if(h.hasHeader())
		{
			header = new CSVData();
		}
	}
	
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
	 * Adds a row to the {@code CSVFile}.
	 * 
	 * @param set  an object set
	 */
	public void add(Object... set)
	{
		put(Count(), set);
	}

	/**
	 * Changes a row in the {@code CSVFile}.
	 * 
	 * @param r  a row index
	 * @param set  an object set
	 */
	public void put(int r, Object... set)
	{
		CSVData d;
		if(!contains(r))
			d = new CSVData();
		else
			d = get(r);

		for(Object o : set)
		{
			d.add(new StringToken(o));
		}
		
		put(d, r);
	}
	
	/**
	 * Changes a value in the {@code CSVFile}.
	 * 
	 * @param r  a row index
	 * @param c  a column index
	 * @param val  an object value
	 */
	public void put(int r, int c, Object val)
	{
		get(r).put(new StringToken(val), c);
	}
	
	
	/**
	 * Adds a comment to the {@code CSVFile}.
	 * 
	 * @param s  a comment line
	 */
	public void addComment(String s)
	{
		addComment(s, LineCount());
	}
	
	/**
	 * Adds a comment to the {@code CSVFile}.
	 * 
	 * @param c  a csv comment
	 * 
	 * 
	 * @see CSVComment
	 */
	public void addComment(CSVComment c)
	{
		addComment(c, LineCount());
	}
	
	/**
	 * Adds a comment to the {@code CSVFile}.
	 * 
	 * @param s  a comment line
	 * @param r  a row index
	 */
	public void addComment(String s, int r)
	{
		addComment(new CSVComment(s), r);
	}
	
	/**
	 * Adds a comment to the {@code CSVFile}.
	 * 
	 * @param c  a csv comment
	 * @param r  a row index
	 * 
	 * 
	 * @see CSVComment
	 */
	public void addComment(CSVComment c, int r)
	{
		c.setIndex(r);
		Operator().add(c);
	}
	
	/**
	 * Changes the {@code CSVFile} header.
	 * 
	 * @param n  a column name
	 * @param c  a column index
	 */
	public void setHeader(String n, int c)
	{
		header.put(new StringToken(n), c);
	}

	/**
	 * Changes the {@code CSVFile} header.
	 * 
	 * @param h  a header line
	 * 
	 * 
	 * @see CSVData
	 */
	public void setHeader(CSVData h)
	{
		header = h;
	}
	
	
	/**
	 * Returns the {@code CSVFile} line count.
	 * This includes comment lines and header.
	 * 
	 * @return  a line count
	 */
	public int LineCount()
	{
		return Operator().LineCount();
	}
	
	/**
	 * Returns the {@code CSVFile} header.
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
	 * Returns {@code CSVFile} hints.
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
	
	
	@Override
	public CSVKernel Operator()
	{
		return kernel;
	}
	
	@Override
	public void add(CSVData data)
	{
		int c1 = data.Count();
		int c2 = Hints().Columns();
		
		if(data.Count() != Hints().Columns())
		{
			throw new FormatError("The csv file expects " + c1 + " values but received " + c2 + ".");
		}

		super.add(data);
	}
	
	@Override
	public void write(File file)
	{
		Operator().write(file);
	}
	
	@Override
	public void read(File file)
	{
		Operator().read(file);
	}
	
	@Override
	public void clear()
	{
		super.clear(); Operator().clear();
	}
}