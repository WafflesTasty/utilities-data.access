package waffles.utils.dacs.files.tokens.third.csv;

import java.util.Iterator;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Storage;
import waffles.utils.dacs.files.tokens.third.csv.CSVFile.Hints;
import waffles.utils.dacs.files.tokens.third.csv.handlers.CSVReader;
import waffles.utils.dacs.files.tokens.third.csv.handlers.CSVWriter;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVComment;
import waffles.utils.dacs.files.tokens.third.csv.rows.CSVData;
import waffles.utils.lang.tokens.parsers.Parsable;
import waffles.utils.sets.queues.ordered.BSQueue;
import waffles.utils.tools.patterns.basic.Clearable;
import waffles.utils.tools.patterns.operator.Operator;

/**
 * A {@code CSVKernel} manages the data structure of a {@code CSVFile}.
 *
 * @author Waffles
 * @since 05 Jun 2025
 * @version 1.1
 *
 * 
 * @see CSVFile
 * @see Clearable
 * @see Operator
 * @see Storage
 */
public class CSVKernel implements Clearable, Storage, Operator<CSVFile>
{
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
			cmts = Comments().iterator();
			if(cmts.hasNext())
			{
				cNext = cmts.next();
			}
		}


		@Override
		public boolean hasNext()
		{
			return dNext < Operable().Count() || cNext != null;
		}

		@Override
		public CSVRow next()
		{
			if(cNext != null)
			{
				if(iNext == cNext.Index())
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
			
			if(dNext < Operable().Count())
			{
				iNext++;
				return Operable().get(dNext++);
			}

			return new CSVComment("");
		}
	}


	private CSVFile file;
	private BSQueue<CSVComment> coms;
	
	/**
	 * Creates a new {@code CSVKernel}.
	 * 
	 * @param f  a csv file
	 */
	public CSVKernel(CSVFile f)
	{
		coms = new BSQueue<>();
		file = f;
	}
		
	/**
	 * Adds a comment to the {@code CSVKernel}.
	 * 
	 * @param cmt  a csv comment
	 * 
	 * 
	 * @see CSVComment
	 */
	public void add(CSVComment cmt)
	{
		coms.push(cmt);
	}
	
	
	/**
	 * Returns the lines in the {@code CSVKernel}.
	 * This includes data, comments and header.
	 * 
	 * @return  a line count
	 */
	public int LineCount()
	{
		int cnt = 0;
		
		cnt += file.Count();
		cnt += coms.Count();
		
		if(Hints().hasHeader())
		{
			cnt += 1;
		}
		
		return cnt;
	}
	
	/**
	 * Returns the comments in the {@code CSVKernel}.
	 * Comments are ordered in a {@code BSTree}
	 * by comparing them on their row index.
	 * 
	 * @return  a comment queue
	 * 
	 * 
	 * @see CSVComment
	 * @see BSQueue
	 */
	public BSQueue<CSVComment> Comments()
	{
		return coms;
	}
	
	/**
	 * Iterates the rows in the {@code CSVKernel}.
	 * 
	 * @return  a row iterable
	 * 
	 * 
	 * @see Iterable
	 * @see CSVRow
	 */
	public Iterable<CSVRow> Rows()
	{
		return () -> new Rows();
	}
			
	/**
	 * Returns the {@code CSVFile} hints.
	 * 
	 * @return  file hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return Operable().Hints();
	}
	
	
	@Override
	public CSVFile Operable()
	{
		return file;
	}
	
	void check(CSVData d, int i)
	{
		Hints h = Operable().Hints();
		
		int c1 = h.Columns();
		int c2 = d.Count();
		
		if(c1 != c2)
		{
			throw new Parsable.Error("Line " + i + " has " + c2 + " values instead of " + c1 + ".");
		}
	}
		
	@Override
	public void write(File file)
	{
		CSVWriter writer = new CSVWriter();
		writer.write(Rows(), file);
	}
	
	@Override
	public void read(File file)
	{
		int curr = -1;
		Operable().clear();
		Hints h = Operable().Hints();
		
		CSVReader reader = new CSVReader(h);
		for(CSVRow row : reader.read(file))
		{
			row.setIndex(++curr);
			switch(row.Type())
			{
			case COMMENT:
			{
				CSVComment c = (CSVComment) row;
				Operable().addComment(c);
				break;
			}
			case DATA:
			{
				CSVData d = (CSVData) row;
				if(Operable().isEmpty())
				{
					if(h.hasHeader())
					{
						check(d, curr);
						Operable().setHeader(d);
						break;
					}
				}
				
				check(d, curr);
				Operable().add(d);
				break;
			}
			default:
				break;
			}
		}
	}
		
	@Override
	public void clear()
	{
		coms.clear();
	}
}