package waffles.utils.dacs.files.assets;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.assets.readers.BufferedImageReader;
import waffles.utils.geom.spatial.bounds.Bounds2D;
import waffles.utils.geom.spatial.bounds.owners.Bounded2D;
import waffles.utils.geom.spatial.data.Axial2D;
import waffles.utils.geomold.bounds.axial.BNDAxial2D;
import waffles.utils.sets.indexed.array.set.ByteSet;

/**
 * An {@code Image} manages a {@code .png} file as a {@code Bytes} object.
 * 
 * @author Waffles
 * @since Sep 23, 2016
 * @version 1.1
 * 
 * 
 * @see Axial2D
 * @see Bounded2D
 * @see ByteSet
 */
public class Image implements Axial2D, Bounded2D, ByteSet
{
	private BufferedImage src;
	
	/**
	 * Creates a new {@code Image}.
	 * 
	 * @param s  a source image
	 * 
	 * 
	 * @see BufferedImage
	 */
	public Image(BufferedImage s)
	{
		src = s;
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
		this(new BufferedImageReader().read(file));
	}


	@Override
	public byte[] Array()
	{
		DataBuffer b = src.getRaster().getDataBuffer();
		return ((DataBufferByte) b).getData();
	}
	
	@Override
	public int[] Dimensions()
	{
		int w = src.getWidth();
		int h = src.getHeight();
		
		return new int[]{w, h};
	}
		
	@Override
	public Bounds2D Bounds()
	{
		return new BNDAxial2D(this);
	}
	
	@Override
	public float Height()
	{
		return src.getHeight();
	}
	
	@Override
	public float Width()
	{
		return src.getWidth();
	}
	
	@Override
	public float X()
	{
		return src.getWidth() / 2;
	}
	
	@Override
	public float Y()
	{
		return src.getHeight() / 2;
	}
}