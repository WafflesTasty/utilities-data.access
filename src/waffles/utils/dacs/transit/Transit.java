package waffles.utils.dacs.transit;

/**
 * A {@code Transit} can load and unload data into a {@code ByteBuffer}.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 */
public interface Transit<O>
{	
	/**
	 * A {@code Queue} is a transit which can load and unload data sequentially.
	 *
	 * @author Waffles
	 * @since 20 Nov 2023
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see Transit
	 */
	public static interface Queue<O> extends Transit<O>
	{		
		/**
		 * Loads data into the {@code Transit Queue}.
		 * This operation advances the queue pointer.
		 * 
		 * @param obj  an object to load
		 */
		public abstract void load(O obj);
		
		/**
		 * Unloads data from the {@code Transit Queue}.
		 * This operation advances the queue pointer.
		 * 
		 * @param obj  an object to load
		 * @return  a loaded object
		 */
		public abstract O unload(O obj);
	}
	
	/**
	 * An {@code Indexed Transit} defines an index to load and unload from.
	 *
	 * @author Waffles
	 * @since 02 Dec 2023
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see Transit
	 */
	public static interface Indexed<O> extends Transit<O>
	{
		/**
		 * Returns the index of the {@code Indexed Transit}.
		 * 
		 * @return  a target index
		 */
		public abstract int Index();
		
		/**
		 * Changes the index of the {@code Indexed Transit}.
		 * 
		 * @param iTgt  a target index
		 */
		public abstract void setIndex(int iTgt);
	}
	

	/**
	 * Loads data into the {@code Transit}.
	 * 
	 * @param obj  an object to load
	 * @param iTgt  a buffer index
	 */
	public abstract void load(O obj, int iTgt);
	
	/**
	 * Unloads data from the {@code Transit}.
	 * 
	 * @param obj  an object to load
	 * @param iTgt  a buffer index
	 * @return  a loaded object
	 */
	public abstract O unload(O obj, int iTgt);
}