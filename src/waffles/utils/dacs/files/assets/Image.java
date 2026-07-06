package waffles.utils.dacs.files.assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;

import waffles.utils.dacs.File;
import waffles.utils.dacs.files.assets.readers.BufferedImageReader;
import waffles.utils.dacs.files.assets.writers.ImageWriter;
import waffles.utils.dacs.utilities.color.Color;
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
	 * @param file  a file
	 * 
	 * 
	 * @see File
	 */
	public Image(File file)
	{
		this(new BufferedImageReader().read(file));
	}
	
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
	 * Returns a {@code BufferedImage}.
	 * 
	 * @return  a source image
	 * 
	 * 
	 * @see BufferedImage
	 */
	public BufferedImage Buffer()
	{
		return src;
	}
	
	
	/**
	 * Saves the {@code Image} to file.
	 * 
	 * @param file  a file
	 * 
	 * 
	 * @see File
	 */
	public void save(File file)
	{
		new ImageWriter().write(this, file);
	}
	
	/**
	 * Changes a pixel in the {@code Image}.
	 * 
	 * @param c  a pixel color
	 * @param x  a pixel x-coordinate
	 * @param y  a pixel y-coordinate
	 * 
	 * 
	 * @see Color
	 */
	public void set(Color c, int x, int y)
	{
		set(c, x, y, 1, 1);
	}

	/**
	 * Changes a rectangle in the {@code Image}.
	 * 
	 * @param c  a pixel color
	 * @param x  a rectangle x-coordinate
	 * @param y  a rectangle y-coordinate
	 * @param w  a rectangle width
	 * @param h  a rectangle height
	 * 
	 * 
	 * @see Color
	 */
	public void set(Color c, int x, int y, int w, int h)
	{
		Graphics2D g = src.createGraphics();
		g.setColor(new java.awt.Color(c.R(), c.G(), c.B(), c.A()));
		g.fillRect(x, y, w, h);
	}
		
	/**
	 * Fills the {@code Image} in a color.
	 * 
	 * @param c  a pixel color
	 * 
	 * 
	 * @see Color
	 */
	public void fill(Color c)
	{
		set(c, 0, 0, (int) Width(), (int) Height());
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
		int w = (int) Width();
		int h = (int) Height();
		
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
		return Width() / 2;
	}
	
	@Override
	public float Y()
	{
		return Height() / 2;
	}
}