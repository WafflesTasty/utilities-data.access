package waffles.utils.dacs.utilities.db.sql;

import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;

/**
 * The {@code DBOps} enum defines {@code Database} operations.
 *
 * @author Waffles
 * @since 02 Nov 2025
 * @version 1.1
 * 
 * 
 * @see Token
 */
public enum SQLOps implements Token
{
	/**
	 * A delete operation.
	 */
	DELETE("delete"),
	/**
	 * A exists operation.
	 */
	EXISTS("exists"),
	/**
	 * A insert operation.
	 */
	INSERT("insert"),
	/**
	 * A select operation.
	 */
	SELECT("select"),
	/**
	 * A update operation.
	 */
	UPDATE("update");

	
	private String name;
	
	private SQLOps(String n)
	{
		name = n;
	}
		
	@Override
	public Format<SQLOps> Formatter()
	{
		return o -> o.Name();
	}
	
	private String Name()
	{
		return name;
	}
}
