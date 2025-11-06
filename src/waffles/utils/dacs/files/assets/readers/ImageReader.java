package waffles.utils.dacs.files.assets.readers;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Reader;
import waffles.utils.dacs.files.assets.Image;

/**
 * An {@code ImageReader} loads .PNG images into an {@code Image}.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Reader
 * @see Image
 */
public class ImageReader implements Reader<Image>
{
	private static BufferedImageReader LOADER = new BufferedImageReader();
	
	@Override
	public Image read(File file)
	{
		return new Image(LOADER.read(file));
	}
}
