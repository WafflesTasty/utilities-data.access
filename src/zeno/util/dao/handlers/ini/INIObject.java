package zeno.util.dao.handlers.ini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code INIObject} interface defines object stored in an {@code INIHandler}.
 * 
 * @since Sep 23, 2016
 * @author Zeno
 * 
 * @see Iterable
 * @see String
 */
public interface INIObject extends Iterable<String>
{
	/**
	 * The {@code Comment} class defines a single INI comment group.
	 * 
	 * @since Sep 23, 2016
	 * @author Zeno
	 * 
	 * @see INIObject
	 */
	public static class Comment implements INIObject
	{
		List<String> lines;
		
		/**
		 * Creates a new {@code Comment}.
		 */
		public Comment()
		{
			lines = new ArrayList<>();
		}
		
		/**
		 * Adds a line to the {@code Comment}.
		 * 
		 * @param line  a line to add
		 * @see String
		 */
		public void add(String line)
		{
			lines.add(line);
		}
		
		@Override
		public Iterator<String> iterator()
		{
			return lines.iterator();
		}

		
		@Override
		public String getName()
		{
			return "@COMMENT";
		}
	}
	
	/**
	 * The {@code Group} class defines a single INI settings group.
	 * 
	 * @since Sep 23, 2016
	 * @author Zeno
	 * 
	 * @see INIObject
	 */
	public static class Group implements INIObject
	{
		String group;
		Map<String, String> settings;
		
		/**
		 * Creates a new {@code Group}.
		 * 
		 * @param name  a group name
		 * @see String
		 */
		public Group(String name)
		{
			settings = new LinkedHashMap<>();
			group = name;
		}
		
		
		/**
		 * Adds a setting to the {@code Group}.
		 * 
		 * @param key  a setting key
		 * @param value  a setting value
		 * @see String
		 */
		public void add(String key, Object value)
		{
			settings.put(key, value.toString());
		}

		/**
		 * Returns a setting in the {@code Group}.
		 * 
		 * @param key  the setting key
		 * @return  the stored setting
		 * @see String
		 */
		public String get(String key)
		{
			return settings.get(key);
		}
		

		@Override
		public Iterator<String> iterator()
		{
			return new Iterator<String>()
			{
				private int index = -3;
				private List<String> keys = new ArrayList<>(settings.keySet());
				
				@Override
				public boolean hasNext()
				{
					return index < settings.size() - 1;
				}

				@Override
				public String next()
				{
					index++;
					
					if(index == -2) return "[" + group + "]";
					if(index == -1) return "";
		
					String key = keys.get(index);
					String value = settings.get(key);
					return "$" + key + "=" + value;
				}
			};
		}

		@Override
		public String getName()
		{
			return group;
		}
	}

	/**
	 * The {@code Start} class defines the INI start marker.
	 * 
	 * @since Sep 23, 2016
	 * @author Zeno
	 * 
	 * @see INIObject
	 */
	public static class Start implements INIObject
	{
		@Override
		public Iterator<String> iterator()
		{
			return null;
		}

		@Override
		public String getName()
		{
			return "@START";
		}
	}
	
	
	/**
	 * Returns the name of the {@code INIObject}.
	 * 
	 * @return  the object's name
	 * @see String
	 */
	public abstract String getName();
}
