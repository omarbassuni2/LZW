import java.util.HashMap;

public class compressDecompress {

	private static HashMap<String, Integer> compDictionary = new HashMap<String, Integer>();
	private static HashMap<Integer, String> decompDictionary = new HashMap<Integer, String>();
	
	//the index in which i will append my new key
	private static int lastVal = 128;
	
	public static void main(String[] args) {
		//initializes the dictionary to chars
		for(int i = 65;i < 91;i++)
		{
			compDictionary.put(((char) i) + "", i);
			decompDictionary.put(i, ((char) i) + "");
		}
		for(int i = 97;i < 122;i++)
		{
			compDictionary.put(((char) i) + "", i);
			decompDictionary.put(i, ((char) i) + "");
		}
		compress("ABBABBABABABABABABABABBBAAAAAA");
		decompress("65 66 66 128 130 132 128 134 133 132 129 65 139 140");

	}
	public static void compress(String X)
	{
		String temp = "";
		String tags = "";

		int value;
		for(int i = 0; i < X.length();i++)
		{
			temp += X.charAt(i);
			if(compDictionary.containsKey(temp))
			{
				continue;
			}
			
			else
			{
				value = compDictionary.get(temp.substring(0, temp.length()-1));
				
				//adds the value of the string that exists to the tags string and separates the tags with a space
				tags += value + " ";
				
				//adds the new key to the map
				compDictionary.put(temp, lastVal);
				
				//to make a room for the next new reference
				lastVal++;
				
				//to make a room for the next string 
				temp = temp.substring(temp.length()-1);
				
				
			}
		}
		
		value = compDictionary.get(temp);
		
		//adds the value of the string that exists to the tags string and separates the tags with a space
		tags += value;
		
		
		System.out.println("Tags: " + tags);
	}
	
	
	public static void decompress(String X)
	{
		
		lastVal = 128;
		
		String previousKey, currentKey = "", decompressedTag = "";		
		String[] convert = X.split(" ");
	
		for(int i = 0;i < convert.length;i++)
		{
			if(i == 0)
			{
				currentKey = decompDictionary.get(Integer.parseInt(convert[0]));
				decompressedTag += currentKey;
				continue;
			}
			
			previousKey = decompDictionary.get(Integer.parseInt(convert[i - 1]));
			
			if(!decompDictionary.containsKey(Integer.parseInt(convert[i])))
			{
				decompDictionary.put(lastVal, previousKey + previousKey.charAt(0));
				decompressedTag += previousKey + previousKey.charAt(0);
				lastVal++;
				continue;
			}
			
			currentKey = decompDictionary.get(Integer.parseInt(convert[i]));
			decompressedTag += currentKey;
			
			if(decompDictionary.containsValue(previousKey + currentKey.charAt(0)))
			{
				continue;
			}
			
			decompDictionary.put(lastVal, previousKey + currentKey.charAt(0));
			lastVal++;
			
		}
		System.out.println("DecompressedTag: " + decompressedTag);
	}
}
