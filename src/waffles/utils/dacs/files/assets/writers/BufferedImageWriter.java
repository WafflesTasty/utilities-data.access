package waffles.utils.dacs.files.assets.writers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.Writer;
import waffles.utils.dacs.utilities.files.AccessError;

/**
 * A {@code BufferedImageWriter} saves .PNG images to a {@code File}.
 *
 * @author Waffles
 * @since Jul 6, 2026
 * @version 1.1
 *
 * 
 * @see BufferedImage
 * @see Writer
 */
public class BufferedImageWriter implements Writer<BufferedImage>
{
	private OutputStream stream;

	@Override
	public void write(BufferedImage img, File file) throws AccessError
	{
		try
		{
			stream = Files.newOutputStream(file.Path());
			ImageIO.write(img, "png", stream);
		}
		catch (IOException e)
		{
			throw new AccessError(file);
		}
	}

}