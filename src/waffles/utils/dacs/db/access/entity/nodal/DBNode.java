package waffles.utils.dacs.db.access.entity.nodal;

import waffles.utils.sets.utilities.arboreal.Node;

/**
 * A {@code DBNode} defines a {@code Node} for a {@code DBNodal}.
 *
 * @author Waffles
 * @since Apr 7, 2026
 * @version 1.1
 *
 * 
 * @see Node
 */
public class DBNode extends Node
{
	/**
	 * Creates a new {@code DBNode}.
	 * 
	 * @param n  a source nodal
	 * 
	 * 
	 * @see DBNodal
	 */
	public DBNode(DBNodal<?> n)
	{
		super(n);
	}
	
	
	@Override
	public DBNodal<?> Root()
	{
		return (DBNodal<?>) super.Root();
	}
	
	@Override
	public DBNodal<?> Delegate()
	{
		return (DBNodal<?>) super.Delegate();
	}
	
	@Override
	public DBNodal<?> Child(int i)
	{
		// TODO Auto-generated method stub
		return (DBNodal<?>) super.Child(i);
	}
		
	@Override
	public DBNodal<?> Parent()
	{
		return (DBNodal<?>) super.Parent();
	}

	@Override
	public DBNode Arch()
	{
		return this;
	}
}