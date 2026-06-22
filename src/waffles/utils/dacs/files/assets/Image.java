package waffles.utils.dacs.files.assets;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.assets.readers.BufferedImageReader;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.sets.indexed.array.set.ByteSet;

/**
 * An {@code Image} manages a {@code .png} file as a {@code Bytes} object.
 * 
 * @author Waffles
 * @since Sep 23, 2016
 * @version 1.1
 * 
 * 
 * @see HyperCuboid2D
 * @see ByteSet
 */
public class Image implements HyperCuboid2D, ByteSet
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
	public Point Origin()
	{
		return new Point(X(), Y(), 1f);
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
	public Arrow Scale()
	{
		return new Arrow(Width(), Height());
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