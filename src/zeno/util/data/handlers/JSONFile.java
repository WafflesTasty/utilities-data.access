package zeno.util.data.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import zeno.util.coll.indices.List;
import zeno.util.data.FileSystem;
import zeno.util.data.system.File;

/**
 * The {@code JSONFile} class defines a {@code File Handler} for JSON data.
 *
 * @author Waffles
 * @since Sep 12, 2021
 * @version 1.0
 * 
 * 
 * @see File
 */
public class JSONFile implements File.Handler
{
	private JSONObject json;
	
	/**
	 * Creates a new {@code JSONFile}.
	 */
	public JSONFile()
	{
		json = new JSONObject();
	}
	
	/**
	 * Creates a new {@code JSONFile}.
	 * 
	 * @param json  an json object
	 * 
	 * 
	 * @see JSONObject
	 */
	public JSONFile(JSONObject json)
	{
		this.json = json;
	}
		
	/**
	 * Changes a value in the {@code JSONFile}.
	 * 
	 * @param <O>  a value type
	 * @param key  a value key
	 * @param value  a json value
	 * 
	 * 
	 * @see String
	 */
	public <O> void put(String key, O value)
	{
		json.put(key, value);
	}
	
	/**
	 * Returns a value in the {@code JSONFile}.
	 * </br> A value can have one of following possible types:
	 * <ul>
	 * <li>Boolean</li>
	 * <li>JSONFile</li>
	 * <li>List</li>
	 * <li>Null</li>
	 * <li>Number</li>
	 * <li>String</li>
	 * </ul>
	 * 
	 * @param <O>  a value type
	 * @param key  a value key
	 * @return  a json value
	 * 
	 * 
	 * @see String
	 */
	public <O> O get(String key)
	{
		Object obj = json.opt(key);
		if(obj instanceof JSONObject)
		{
			obj = new JSONFile((JSONObject) obj);
		}
		if(obj instanceof JSONArray)
		{
			List<Object> list = new List<>();
			for(int i = 0; i < ((JSONArray) obj).length(); i++)
			{
				list.put(((JSONArray) obj).get(i), i);
			}

			obj = list;
		}
		
		return (O) obj;
	}
	
	
	@Override
	public void write(File file)
	{
		try(BufferedWriter writer = File.Writer(file))
		{
			writer.write(json.toString());
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
		
		String text = "";
		try(BufferedReader reader = File.Reader(file))
		{
			String line = "";
			while(line != null)
			{
				line = reader.readLine();
				if(line != null)
				{
					text += line;
				}
			}
		}
		catch (IOException e)
		{
			throw new FileSystem.AccessError(file);
		}
		
		json = new JSONObject(text);
	}
	
	@Override
	public String toString()
	{
		if(json != null)
			return json.toString();
		return "";
	}
}