package zeno.util.dao.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import zeno.util.dao.File;
import zeno.util.dao.handlers.ini.INIObject;
import zeno.util.dao.handlers.ini.INIObject.Comment;
import zeno.util.dao.handlers.ini.INIObject.Group;
import zeno.util.dao.handlers.ini.INIObject.Start;

/**
 * The {@code INIHandler} class reads text files in a predefined INI format.
 * <br> An INI file contains two possible text groups:
 * <ul>
 * <li> A {@code Comment} is a sequence of lines each starting with an {@code @}. </li>
 * <li> A {@code Group} is a sequence of settings formatted {@code $key=value}. </li>
 * </ul>
 * 
 * @since Sep 23, 2016
 * @author Zeno
 * 
 * @see INIObject
 * @see File
 */
public class INIHandler extends File.Handler<INIObject>
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
	
	
	/**
	 * Creates a new {@code INIHandler}.
	 * 
	 * @param url  a file url
	 * @see String
	 */
	public INIHandler(String url)
	{
		super(url);
	}
	
	/**
	 * Creates a new {@code INIHandler}.
	 * 
	 * @param path  a file path
	 * @see Path
	 */
	public INIHandler(Path path)
	{
		super(path);
	}

	
	/**
	 * Changes a setting stored in the {@code INIHandler}.
	 * 
	 * @param group  a setting group
	 * @param key  a setting key
	 * @param value  a setting value
	 * @see String
	 */
	public void addSetting(String group, String key, Object value)
	{
		for(INIObject object : Objects())
		{
			if(group.equals(object.getName()))
			{
				((Group) object).add(key, value);
			}
		}
	}
	
	/**
	 * Returns a setting stored in the {@code INIHandler}.
	 * 
	 * @param group  a setting group
	 * @param key  a setting key
	 * @return  a setting value
	 * @see String
	 */
	public String getSetting(String group, String key)
	{
		for(INIObject object : Objects())
		{
			if(group.equals(object.getName()))
			{
				return ((Group) object).get(key);
			}
		}
		
		return null;
	}
	
	
	@Override
	protected boolean write(List<INIObject> objects)
	{
		try(BufferedWriter writer = Files.newBufferedWriter(Path()))
		{
			for(INIObject object : objects)
			{
				for(String line : object)
				{
					writer.write(line + "\r\n");
				}
				
				writer.write("\r\n");
			}
		}
		catch (IOException e)
		{
			return false;
		}
		
		return true;
	}

	@Override
	protected List<INIObject> read()
	{
		List<INIObject> objects = new ArrayList<>();
		try(BufferedReader reader = Files.newBufferedReader(Path()))
		{
			String line = "";
			INIObject object = new Start();
			while(line != null)
			{
				line = reader.readLine();
				if(line == null)
				{
					continue;
				}
				
				if(isComment(line))
				{
					if(!"@COMMENT".equals(object.getName()))
					{
						object = new Comment();
						objects.add(object);
					}

					((Comment) object).add(line);
					continue;
				}
				
				if(isSetting(line))
				{
					String[] setting = toSetting(line);
					((Group) object).add(setting[0], setting[1]);
					continue;
				}
				
				if(isGroup(line))
				{
					object = new Group(toGroup(line));
					objects.add(object);
					continue;
				}
			}
		}
		catch (IOException e)
		{
			return null;
		}

		return objects;
	}
}