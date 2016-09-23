package zeno.util.dao.files;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sixlegs.png.PngImage;

import zeno.util.dao.File;

/**
 * The {@code Image} class defines an image loaded from the hard disk.
 * <br> Currently supported images are limited to {@code PNG}.
 * 
 * @since Sep 23, 2016
 * @author Zeno
 * 
 * @see File
 */
public class Image extends File
{
	private static PngImage loader = new PngImage();
	
	
	private BufferedImage source;
	
	/**
	 * Creates a new {@code Image}.
	 * 
	 * @param url  an image url
	 * @see String
	 */
	public Image(String url)
	{
		super(Paths.get(url));
		load();
	}

	/**
	 * Creates a new {@code Image}.
	 * 
	 * @param path  an image path
	 * @see Path
	 */
	public Image(Path path)
	{
		super(path);
		load();
	}
	

	/**
	 * Returns the raster of the {@code Image}.
	 * 
	 * @return  the image raster
	 */
	public WritableRaster Raster()
	{
		return source.getRaster();
	}
	
	/**
	 * Returns the height of the {@code Image}.
	 * 
	 * @return  the image height
	 */
	public int Height()
	{
		return source.getHeight();
	}
	
	/**
	 * Returns the width of the {@code Image}.
	 * 
	 * @return  the image width
	 */
	public int Width()
	{
		return source.getWidth();
	}
	
	
	@Override
	public boolean load()
	{
		if(!super.load())
		{
			return false;
		}
		
		try
		{
			source = loader.read(Path().toFile());
		}
		catch (IOException e)
		{
			return false;
		}
		
		return true;
	}
}
