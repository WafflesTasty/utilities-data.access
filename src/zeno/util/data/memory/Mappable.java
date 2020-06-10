package zeno.util.data.memory;

/**
 * The {@code Mappable} interface defines a mappable chunk of memory.
 *
 * @author Zeno
 * @since 10 Jun 2020
 * @version 1.0
 */
public interface Mappable
{
	/**
	 * Unmaps the {@code Mappable}.
	 */
	public abstract void unmap();
	
	/**
	 * Maps the {@code Mappable}.
	 */
	public abstract void map();
}