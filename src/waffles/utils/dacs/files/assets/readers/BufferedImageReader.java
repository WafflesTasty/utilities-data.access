package waffles.utils.dacs.files.assets.readers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import com.sixlegs.png.PngImage;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.utilities.errors.AccessError;

/**
 * A {@code BufferedImageReader} loads .PNG images into a {@code BufferedImage}.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 * 
 * 
 * @see BufferedImage
 * @see Reader
 */
public class BufferedImageReader implements Reader<BufferedImage>
{
	private static PngImage LOADER = new PngImage();
	
	
	private BufferedImage src;
	
	@Override
	public BufferedImage read(File file)
	{
		try
		{
			Path path = file.Path();
			src = LOADER.read(path.toFile());
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
		
		return src;
	}
}
