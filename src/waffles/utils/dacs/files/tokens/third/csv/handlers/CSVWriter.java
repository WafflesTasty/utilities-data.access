package waffles.utils.dacs.files.tokens.third.csv.handlers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import waffles.utils.dacs.File;
import waffles.utils.dacs.FileSystem;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.files.tokens.third.csv.CSVRow;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * A {@code CSVWriter} writes {@code CSVRows} to a {@code File}.
 * These are stored as lines of data in a {@code CSVFile}.
 *
 * @author Waffles
 * @since 04 Jun 2025
 * @version 1.1
 *
 * 
 * @see CSVRow
 * @see Iterable
 * @see Writer
 */
public class CSVWriter implements Writer<Iterable<CSVRow>>
{
	private File file;
	private BufferedWriter writer;

	@Override
	public void write(Iterable<CSVRow> rows, File f) throws AccessError
	{
		try
		{
			writer = Files.newBufferedWriter(f.Path(), FileSystem.CHAR_SET);
			for(CSVRow row : rows)
			{
				writer.write(row.parse() + "\r\n");
			}
			writer.close();
			file = f;
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}
}