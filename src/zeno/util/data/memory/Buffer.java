package zeno.util.data.memory;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import zeno.util.tools.Bytes;
import zeno.util.tools.Doubles;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;
import zeno.util.tools.Longs;
import zeno.util.tools.Shorts;

/**
 * The {@code Buffer} interface defines a wrapper for a {@link ByteBuffer} object.
 * <br> This interface homogenizes method names across all primitive types.
 * 
 * @author Zeno
 * @since Sep 21, 2016
 * @version 1.0
 */
public interface Buffer
{
	/**
	 * The {@code AsDouble} class encapsulates I/O of double data in a {@code Buffer}.
	 * 
	 * @author Zeno
	 * @since Sep 21, 2016
	 * @version 1.0
	 * 
	 * 
	 * @see Buffer
	 */
	public static class AsDouble
	{
		private Buffer source;
		private DoubleBuffer view;

		/**
		 * Creates a new {@code Buffer.AsDouble}.
		 * 
		 * @param buffer  a target buffer
		 * 
		 * 
		 * @see Buffer
		 */
		public AsDouble(Buffer buffer)
		{
			view = buffer.data().asDoubleBuffer();
			source = buffer;
		}
		
		
		/**
		 * Reads an array of double data from the buffer's current index.
		 * <br> This advances the current index with {@code data.length} doubles.
		 * 
		 * @param data  an array to store the data
		 * @return  an array of doubles
		 */
		public double[] read(double... data)
		{
			view.get(data);
			advance(data.length);
			return data;
		}

		/**
		 * Reads an array of double data from the buffer's current index.
		 * <br> This advances the current index with {@code count} doubles.
		 * 
		 * @param data  an array to store the data
		 * @param start  the start index in the array
		 * @param count  the amount of doubles to read
		 * @return  an array of doubles
		 */
		public double[] read(double[] data, int start, int count)
		{
			view.get(data, start, count);
			advance(count);
			return data;
		}

		
		/**
		 * Writes an array of double data to the buffer's current index.
		 * <br> This advances the current index with {@code count} doubles.
		 * 
		 * @param data  an array of doubles to write
		 * @param start  the start index in the array
		 * @param count  the amount of doubles to write
		 */
		public void write(double[] data, int start, int count)
		{
			view.put(data, start, count);
			advance(count);
		}
		
		/**
		 * Writes an array of double data to the buffer's current index.
		 * <br> This advances the current index with {@code data.length} doubles.
		 * 
		 * @param data  an array of doubles to write
		 */
		public void write(double... data)
		{
			view.put(data);
			advance(data.length);
		}				

		
		private void advance(int count)
		{
			source.moveFor(Doubles.BYTE_SIZE * count);
		}
	}
	
	/**
	 * The {@code AsFloat} class encapsulates I/O of float data in a {@code Buffer}.
	 * 
	 * @author Zeno
	 * @since Sep 21, 2016
	 * @version 1.0
	 * 
	 * 
	 * @see Buffer
	 */
	public static class AsFloat
	{
		private Buffer source;
		private FloatBuffer view;

		/**
		 * Creates a new {@code Buffer.AsFloat}.
		 * 
		 * @param buffer  a target buffer
		 * 
		 * 
		 * @see Buffer
		 */
		public AsFloat(Buffer buffer)
		{
			view = buffer.data().asFloatBuffer();
			source = buffer;
		}
		
		
		/**
		 * Reads an array of float data from the buffer's current index.
		 * <br> This advances the current index with {@code data.length} floats.
		 * 
		 * @param data  an array to store the data
		 * @return  an array of floats
		 */
		public float[] read(float... data)
		{
			view.get(data);
			advance(data.length);
			return data;
		}

		/**
		 * Reads an array of float data from the buffer's current index.
		 * <br> This advances the current index with {@code count} floats.
		 * 
		 * @param data  an array to store the data
		 * @param start  the start index in the array
		 * @param count  the amount of floats to read
		 * @return  an array of floats
		 */
		public float[] read(float[] data, int start, int count)
		{
			view.get(data, start, count);
			advance(count);
			return data;
		}

		
		/**
		 * Writes an array of float data to the buffer's current index.
		 * <br> This advances the current index with {@code count} floats.
		 * 
		 * @param data  an array of floats to write
		 * @param start  the start index in the array
		 * @param count  the amount of floats to write
		 */
		public void write(float[] data, int start, int count)
		{
			view.put(data, start, count);
			advance(count);
		}
		
		/**
		 * Writes an array of float data to the buffer's current index.
		 * <br> This advances the current index with {@code data.length} floats.
		 * 
		 * @param data  an array of floats to write
		 */
		public void write(float... data)
		{
			view.put(data);
			advance(data.length);
		}				

		
		private void advance(int count)
		{
			source.moveFor(Floats.BYTE_SIZE * count);
		}
	}
	
	/**
	 * The {@code AsShort} class encapsulates I/O of short data in a {@code Buffer}.
	 * 
	 * @author Zeno
	 * @since Sep 21, 2016
	 * @version 1.0
	 * 
	 * 
	 * @see Buffer
	 */
	public static class AsShort
	{
		private Buffer source;
		private ShortBuffer view;

		/**
		 * Creates a new {@code Buffer.AsShort}.
		 * 
		 * @param buffer  a target buffer
		 * 
		 * 
		 * @see Buffer
		 */
		public AsShort(Buffer buffer)
		{
			view = buffer.data().asShortBuffer();
			source = buffer;
		}
		
		
		/**
		 * Reads an array of short data from the buffer's current index.
		 * <br> This advances the current index with {@code data.length} shorts.
		 * 
		 * @param data  an array to store the data
		 * @return  an array of shorts
		 */
		public short[] read(short... data)
		{
			view.get(data);
			advance(data.length);
			return data;
		}

		/**
		 * Reads an array of short data from the buffer's current index.
		 * <br> This advances the current index with {@code count} shorts.
		 * 
		 * @param data  an array to store the data
		 * @param start  the start index in the array
		 * @param count  the amount of shorts to read
		 * @return  an array of shorts
		 */
		public short[] read(short[] data, int start, int count)
		{
			view.get(data, start, count);
			advance(count);
			return data;
		}

		
		/**
		 * Writes an array of short data to the buffer's current index.
		 * <br> This advances the current index with {@code count} shorts.
		 * 
		 * @param data  an array of shorts to write
		 * @param start  the start index in the array
		 * @param count  the amount of shorts to write
		 */
		public void write(short[] data, int start, int count)
		{
			view.put(data, start, count);
			advance(count);
		}
		
		/**
		 * Writes an array of short data to the buffer's current index.
		 * <br> This advances the current index with {@code data.length} shorts.
		 * 
		 * @param data  an array of shorts to write
		 */
		public void write(short... data)
		{
			view.put(data);
			advance(data.length);
		}
		
		
		private void advance(int count)
		{
			source.moveFor(Shorts.BYTE_SIZE * count);
		}
	}
	
	/**
	 * The {@code AsByte} class encapsulates I/O of byte data in a {@code Buffer}.
	 * 
	 * @author Zeno
	 * @since Sep 21, 2016
	 * @version 1.0
	 * 
	 * 
	 * @see Buffer
	 */
	public static class AsByte
	{
		private Buffer source;
		private ByteBuffer view;

		/**
		 * Creates a new {@code Buffer.AsByte}.
		 * 
		 * @param buffer  a target buffer
		 * 
		 * 
		 * @see Buffer
		 */
		public AsByte(Buffer buffer)
		{
			view = buffer.data();
			source = buffer;
		}
		
		
		/**
		 * Reads an array of byte data from the buffer's current index.
		 * <br> This advances the current index with {@code data.length} bytes.
		 * 
		 * @param data  an array to store the data
		 * @return  an array of bytes
		 */
		public byte[] read(byte... data)
		{
			view.get(data);
			advance(data.length);
			return data;
		}

		/**
		 * Reads an array of byte data from the buffer's current index.
		 * <br> This advances the current index with {@code count} bytes.
		 * 
		 * @param data  an array to store the data
		 * @param start  the start index in the array
		 * @param count  the amount of bytes to read
		 * @return  an array of bytes
		 */
		public byte[] read(byte[] data, int start, int count)
		{
			view.get(data, start, count);
			advance(count);
			return data;
		}

		
		/**
		 * Writes an array of byte data to the buffer's current index.
		 * <br> This advances the current index with {@code count} bytes.
		 * 
		 * @param data  an array of bytes to write
		 * @param start  the start index in the array
		 * @param count  the amount of bytes to write
		 */
		public void write(byte[] data, int start, int count)
		{
			view.put(data, start, count);
			advance(count);
		}
		
		/**
		 * Writes an array of byte data to the buffer's current index.
		 * <br> This advances the current index with {@code data.length} bytes.
		 * 
		 * @param data  an array of bytes to write
		 */
		public void write(byte... data)
		{
			view.put(data);
			advance(data.length);
		}				
	
		
		private void advance(int count)
		{
			source.moveFor(Bytes.BYTE_SIZE * count);
		}
	}
	
	/**
	 * The {@code AsLong} class encapsulates I/O of long data in a {@code Buffer}.
	 * 
	 * @author Zeno
	 * @since Sep 21, 2016
	 * @version 1.0
	 * 
	 * 
	 * @see Buffer
	 */
	public static class AsLong
	{
		private Buffer source;
		private LongBuffer view;

		/**
		 * Creates a new {@code Buffer.AsLong}.
		 * 
		 * @param buffer  a target buffer
		 * 
		 * 
		 * @see Buffer
		 */
		public AsLong(Buffer buffer)
		{
			view = buffer.data().asLongBuffer();
			source = buffer;
		}
		
		
		/**
		 * Reads an array of long data from the buffer's current index.
		 * <br> This advances the current index with {@code data.length} longs.
		 * 
		 * @param data  an array to store the data
		 * @return  an array of longs
		 */
		public long[] read(long... data)
		{
			view.get(data);
			advance(data.length);
			return data;
		}

		/**
		 * Reads an array of long data from the buffer's current index.
		 * <br> This advances the current index with {@code count} longs.
		 * 
		 * @param data  an array to store the data
		 * @param start  the start index in the array
		 * @param count  the amount of longs to read
		 * @return  an array of longs
		 */
		public long[] read(long[] data, int start, int count)
		{
			view.get(data, start, count);
			advance(count);
			return data;
		}

		
		/**
		 * Writes an array of long data to the buffer's current index.
		 * <br> This advances the current index with {@code count} longs.
		 * 
		 * @param data  an array of longs to write
		 * @param start  the start index in the array
		 * @param count  the amount of longs to write
		 */
		public void write(long[] data, int start, int count)
		{
			view.put(data, start, count);
			advance(count);
		}
		
		/**
		 * Writes an array of long data to the buffer's current index.
		 * <br> This advances the current index with {@code data.length} longs.
		 * 
		 * @param data  an array of longs to write
		 */
		public void write(long... data)
		{
			view.put(data);
			advance(data.length);
		}				
	
		
		private void advance(int count)
		{
			source.moveFor(Longs.BYTE_SIZE * count);
		}
	}
	
	/**
	 * The {@code AsInt} class encapsulates I/O of int data in a {@code Buffer}.
	 * 
	 * @author Zeno
	 * @since Sep 21, 2016
	 * @version 1.0
	 * 
	 * 
	 * @see Buffer
	 */
	public static class AsInt
	{
		private Buffer source;
		private IntBuffer view;

		/**
		 * Creates a new {@code Buffer.AsInt}.
		 * 
		 * @param buffer  a target buffer
		 * 
		 * 
		 * @see Buffer
		 */
		public AsInt(Buffer buffer)
		{
			view = buffer.data().asIntBuffer();
			source = buffer;
		}
		
		
		/**
		 * Reads an array of integer data from the buffer's current index.
		 * <br> This advances the current index with {@code data.length} integers.
		 * 
		 * @param data  an array to store the data
		 * @return  an array of integers
		 */
		public int[] read(int... data)
		{
			view.get(data);
			advance(data.length);
			return data;
		}

		/**
		 * Reads an array of integer data from the buffer's current index.
		 * <br> This advances the current index with {@code count} integers.
		 * 
		 * @param data  an array to store the data
		 * @param start  the start index in the array
		 * @param count  the amount of integers to read
		 * @return  an array of integers
		 */
		public int[] read(int[] data, int start, int count)
		{
			view.get(data, start, count);
			advance(count);
			return data;
		}

		
		/**
		 * Writes an array of integer data to the buffer's current index.
		 * <br> This advances the current index with {@code count} integers.
		 * 
		 * @param data  an array of integers to write
		 * @param start  the start index in the array
		 * @param count  the amount of integers to write
		 */
		public void write(int[] data, int start, int count)
		{
			view.put(data, start, count);
			advance(count);
		}
		
		/**
		 * Writes an array of integer data to the buffer's current index.
		 * <br> This advances the current index with {@code data.length} integers.
		 * 
		 * @param data  an array of integers to write
		 */
		public void write(int... data)
		{
			view.put(data);
			advance(data.length);
		}				
	
		
		private void advance(int count)
		{
			source.moveFor(Integers.BYTE_SIZE * count);
		}
	}
	

	/**
	 * Returns the data source of the {@code Wrapper}.
	 * 
	 * @return  the buffer's data source
	 * 
	 * 
	 * @see ByteBuffer
	 */
	public abstract ByteBuffer data();
	
	
	/**
	 * Changes the current index of the {@code Wrapper}.
	 * 
	 * @param index  a buffer index offset
	 */
	public default void moveFor(int index)
	{
		moveTo(data().position() + index);
	}
	
	/**
	 * Changes the current index of the {@code Wrapper}.
	 * 
	 * @param index  a new buffer index
	 */
	public default void moveTo(int index)
	{
		data().position(index);
	}
	
	
	/**
	 * Returns double data functionality for the {@code Buffer}.
	 * 
	 * @return  a double buffer
	 * 
	 * 
	 * @see AsDouble
	 */
	public default AsDouble asDouble()
	{
		return new AsDouble(this);
	}
	
	/**
	 * Returns float data functionality for the {@code Buffer}.
	 * 
	 * @return  a float buffer
	 * 
	 * 
	 * @see AsFloat
	 */
	public default AsFloat asFloat()
	{
		return new AsFloat(this);
	}
	
	/**
	 * Returns short data functionality for the {@code Buffer}.
	 * 
	 * @return  a short buffer
	 * 
	 * 
	 * @see AsShort
	 */
	public default AsShort asShort()
	{
		return new AsShort(this);
	}
	
	/**
	 * Returns byte data functionality for the {@code Buffer}.
	 * 
	 * @return  a byte buffer
	 * 
	 * 
	 * @see AsByte
	 */
	public default AsByte asByte()
	{
		return new AsByte(this);
	}
	
	/**
	 * Returns long data functionality for the {@code Buffer}.
	 * 
	 * @return  a long buffer
	 * 
	 * 
	 * @see AsLong
	 */
	public default AsLong asLong()
	{
		return new AsLong(this);
	}
	
	/**
	 * Returns int data functionality for the {@code Buffer}.
	 * 
	 * @return  an int buffer
	 * 
	 * 
	 * @see AsInt
	 */
	public default AsInt asInt()
	{
		return new AsInt(this);
	}
}