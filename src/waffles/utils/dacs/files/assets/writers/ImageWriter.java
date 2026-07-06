package waffles.utils.dacs.files.assets.writers;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.files.assets.Image;

/**
 * An {@code ImageWriter} saves an {@code Image} to a {@code File}.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Writer
 * @see Image
 */
public class ImageWriter implements Writer<Image>
{
	private static BufferedImageWriter WRITER = new BufferedImageWriter();
	
	@Override
	public void write(Image img, File file)
	{
		WRITER.write(img.Buffer(), file);
	}
}
