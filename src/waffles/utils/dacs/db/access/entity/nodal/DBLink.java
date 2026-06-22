package waffles.utils.dacs.db.access.entity.nodal;

import waffles.utils.dacs.db.DBRow;
import waffles.utils.dacs.db.access.entity.DBTable;
import waffles.utils.dacs.db.access.entity.nodal.format.SQLParentSelect;
import waffles.utils.dacs.db.access.format.SQLAccessDelete;
import waffles.utils.dacs.db.access.format.SQLAccessExists;
import waffles.utils.dacs.db.access.format.SQLAccessInsert;
import waffles.utils.dacs.db.access.format.SQLAccessUpdate;
import waffles.utils.dacs.utilities.db.sql.SQLFormat;
import waffles.utils.dacs.utilities.db.sql.SQLOps;
import waffles.utils.dacs.utilities.db.tokens.DBLiteral;
import waffles.utils.dacs.utilities.db.tokens.DBToken;
import waffles.utils.dacs.utilities.db.tokens.maps.DBMap;

/**
 * A {@code DBLink} defines a {@code DBTable} with a parent-child relationship.
 *
 * @author Waffles
 * @since Apr 7, 2026
 * @version 1.1
 * 
 * 
 * @param <N>  a nodal type
 * @see DBNodal
 * @see DBTable
 */
public abstract class DBLink<N extends DBNodal<?>> extends DBTable<N>
{
	/**
	 * Defines a default GUID reference for a {@code DBLink}.
	 */
	public static String DEFAULT_REFERENCE = "obj_id";
	
	
	/**
	 * Creates a new {@code DBLink}.
	 * 
	 * @param n  a table name
	 */
	public DBLink(String n)
	{
		super(n);
	}
		
	/**
	 * Returns the {@code DBLink} reference.
	 * 
	 * @return  a reference column
	 */
	public String RefGUID()
	{
		return DEFAULT_REFERENCE;
	}

	
	/**
	 * Loads a {@code DBLink} child from a guid.
	 * 
	 * @param id  a unique identifier
	 * @return  a database access
	 * 
	 * 
	 * @see DBNodal
	 */
	public abstract DBNodal<?> load(String id);
		
	@Override
	public SQLFormat<?> Formatter(SQLOps ops)
	{
		switch(ops)
		{
		case DELETE:
			return new SQLAccessDelete(this);
		case EXISTS:
			return new SQLAccessExists(this);
		case INSERT:
			return new SQLAccessInsert(this);
		case SELECT:
			return new SQLParentSelect(this);
		case UPDATE:
			return new SQLAccessUpdate(this);
		default:
			return null;
		}
	}

	@Override
	public boolean load(N ndl, DBRow row)
	{
		DBNode n = ndl.Arch(this);
		
		
		n.clear();
		while(row.hasNext())
		{
			DBRow r = row.next();
			Object ref = r.get(RefGUID());
			DBNodal<?> acs = load((String) ref);
			if(acs == null)
			{
				return false;
			}
			
			n.addChild(acs);
			DBMap map = Map(acs);
			for(DBLiteral tkn : Setter().Keys())
			{
				Object o = r.get(tkn.condense());
				map.put(tkn, new DBToken(o));
			}		
		}
		
		return true;
	}
}