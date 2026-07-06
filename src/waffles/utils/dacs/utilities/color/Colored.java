package waffles.utils.dacs.utilities.color;

import waffles.utils.tools.patterns.properties.Immutable;

/**
 * A {@code Colored} object defines its own {@code Color}.
 *
 * @author Waffles
 * @since May 16, 2026
 * @version 1.1
 * 
 * 
 * @see Immutable
 */
@FunctionalInterface
public interface Colored extends Immutable
{
	/**
	 * A {@code Colored.Mutable} can change its own {@code Color}.
	 *
	 * @author Waffles
	 * @since May 16, 2026
	 * @version 1.1
	 *
	 * 
	 * @see Immutable
	 * @see Colored
	 */
	public static interface Mutable extends Colored, Immutable.Mutable
	{
		/**
		 * Changes the base {@code Color}.
		 * 
		 * @param c  a color
		 * 
		 * 
		 * @see Color
		 */
		public abstract void setColor(Color c);
		
		/**
		 * Changes the base {@code Color}.
		 * 
		 * @param h  a hexcode
		 */
		public default void setColor(int h)
		{
			setColor(new Color(h));
		}
	}
	
	
	/**
	 * Returns the base {@code Color}.
	 * 
	 * @return  a color
	 * 
	 * 
	 * @see Color
	 */
	public abstract Color Color();
}