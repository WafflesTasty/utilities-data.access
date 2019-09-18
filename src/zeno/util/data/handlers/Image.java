package zeno.util.data.handlers;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.file.Path;

import com.sixlegs.png.PngImage;

import zeno.util.data.FileSystem;
import zeno.util.data.system.File;

/**
 * The {@code Image} class defines an image loaded from the hard disk.
 * <br> Currently supported images are limited to {@code PNG}.
 * 
 * @author Zeno
 * @since Sep 23, 2016
 * @version 1.0
 * 
 * 
 * @see File
 */
public class Image implements File.Handler
{
	private static PngImage loader = new PngImage();
	
	
	private BufferedImage src;
	
	/**
	 * Creates a new {@code Image}.
	 * 
	 * @param path  a file path
	 * 
	 * 
	 * @see Path
	 */
	public Image(Path path)
	{
		this(new File(path));
	}
	
	/**
	 * Creates a new {@code Image}.
	 * 
	 * @param url  a file url
	 * 
	 * 
	 * @see String
	 */
	public Image(String url)
	{
		this(new File(url));
	}
	
	/**
	 * Creates a new {@code Image}.
	 * 
	 * @param file  a file
	 * 
	 * 
	 * @see File
	 */
	public Image(File file)
	{
		read(file);
	}

	
	/**
	 * Returns the raster of the {@code Image}.
	 * 
	 * @return  the image raster
	 * 
	 * 
	 * @see WritableRaster
	 */
	public WritableRaster Raster()
	{
		return src.getRaster();
	}
	
	/**
	 * Returns the height of the {@code Image}.
	 * 
	 * @return  the image height
	 */
	public int Height()
	{
		return src.getHeight();
	}
	
	/**
	 * Returns the width of the {@code Image}.
	 * 
	 * @return  the image width
	 */
	public int Width()
	{
		return src.getWidth();
	}

	
	@Override
	public void write(File file)
	{
		throw new FileSystem.AccessError(file);
	}
	
	@Override
	public void read(File file)
	{
		try
		{
			Path path = file.Path();
			src = loader.read(path.toFile());
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(file);
		}
	}
}