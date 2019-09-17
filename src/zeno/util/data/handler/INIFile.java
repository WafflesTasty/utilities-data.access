package zeno.util.data.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import zeno.util.data.FileSystem;
import zeno.util.data.files.File;

/**
 * The {@code INIFile} class defines a {@code File Handler} for ini files.
 * All settings are grouped under a common tag which is formatted as {@code [group]}.
 * The settings themselves are formatted as {@code $key=value}. Additional comments can be
 * formatted with a '@' prefix which are ignored by the file parser.
 * 
 * @author Zeno
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see File
 */
public class INIFile implements File.Handler
{
	private static final String COMMENT = "@";
	private static final String ASSIGNMENT = "=";
	private static final String DEFINITION = "$";
	
	private static String[] toSetting(String line)
	{
		return line.substring(1, line.length()).split(ASSIGNMENT);
	}
			
	private static boolean isComment(String line)
	{
		return line.trim().startsWith(COMMENT);
	}
	
	private static boolean isSetting(String line)
	{
		return line.startsWith(DEFINITION) 
			&& line.contains(ASSIGNMENT);
	}
	
	private static boolean isGroup(String line)
	{
		return line.trim().startsWith("[")
			&& line.trim().endsWith("]");
	}
	
	private static String toGroup(String line)
	{
		return line.trim().substring(1, line.length() - 1);
	}
	
	private static class Group
	{
		private String name;
		private Map<String, String> values;
		
		public Group(String n)
		{
			values = new LinkedHashMap<>();
			name = n;
		}
		
		public String Name()
		{
			return name;
		}
		
		public String get(String key)
		{
			return values.get(key);
		}
		
		public void set(String key, String val)
		{
			if(val != null)
				values.put(key, val);
			else
				values.remove(key);
		}
		
		@Override
		public String toString()
		{
			String text = "[" + name + "]\r\n\r\n";
			for(Entry<String, String> entry : values.entrySet())
			{
				text += "$" + entry.getKey();
				text += "=" + entry.getValue();
				text += "\r\n";
			}
			
			return text;
		}
	}
	
	
	private Map<String, Group> groups;
	
	/**
	 * Creates a new {@code INIFile}.
	 */
	public INIFile()
	{
		groups = new LinkedHashMap<>();
	}
	
	
	/**
	 * Returns a value in the {@code INIFile}.
	 * 
	 * @param grp  a setting group
	 * @param key  a setting key
	 * @return  a setting value
	 * 
	 * 
	 * @see String
	 */
	public String get(String grp, String key)
	{
		return groups.get(grp).get(key);
	}
	
	/**
	 * Changes a value in the {@code INIFile}.
	 * 
	 * @param grp  a setting group
	 * @param key  a setting key
	 * @param val  a setting value
	 * 
	 * 
	 * @see String
	 */
	public void put(String grp, String key, Object val)
	{
		Group g = groups.get(grp);
		if(g == null)
		{
			g = new Group(grp);
			groups.put(grp, g);
		}
		
		g.set(key, val.toString());
	}
	
	
	@Override
	public void write(File file)
	{
		File tmp = null;
		if(file.exists())
			tmp = file.renameTo(file.Name() + ".tmp");
		else
		{
			file.create();
			tmp = file;
		}
		
		
		try(BufferedReader reader = File.Reader(tmp);
			BufferedWriter writer = File.Writer(file))
		{
			Map<String, Group> copy = new LinkedHashMap<>(groups);
			
			String line = "";
			while(line != null)
			{
				line = reader.readLine();
				if(line == null) continue;
				if(isSetting(line)) continue;
				if(isComment(line))
				{
					writer.write(line + "\r\n");
					continue;
				}
				
				if(isGroup(line))
				{
					Group g = copy.get(toGroup(line));
					
					writer.write("\r\n");
					writer.write(g.toString());
					copy.remove(g.Name());
				}
			}
			
			for(Group g : copy.values())
			{
				writer.write("\r\n");
				writer.write(g.toString());
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(file);
		}
	}
	
	@Override
	public void read(File file)
	{
		if(!file.exists())
		{
			throw new FileSystem.FileNotFoundError(file);
		}
		
		Group g = null;
		try(BufferedReader reader = File.Reader(file))
		{
			String line = "";
			while(line != null)
			{
				line = reader.readLine();
				if(line == null) continue;
				if(isComment(line)) continue;
				if(isSetting(line))
				{
					if(g == null)
					{
						throw new FileSystem.MalformedFileError(file, line);
					}
					
					String[] setting = toSetting(line);
					g.set(setting[0], setting[1]);
					continue;
				}
				
				if(isGroup(line))
				{
					g = new Group(toGroup(line));
					groups.put(g.name, g);
					continue;
				}
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(file);
		}
	}
}