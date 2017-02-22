package io.frontline.shozab;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**  
 * Main
 * @param
 */
public class Solution {
    public static void main(String[] args) {
    	
    	System.out.println("+------------------------------+"); 
    	System.out.println("|   Frontline Code Challenge   |");
    	System.out.println("+------------------------------+");
    	System.out.println("\n");
    	System.out.println("Enter input (Default: (id,created,employee(id,firstname,employeeType(id), lastname),location): ");
    	
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        //Default value
        if (input.length()==0)
        	input = "(id,created,employee(id,firstname,employeeType(id), lastname),location)";

    	System.out.println("\n");
        System.out.println("Results in Alphabetical Order");
    	System.out.println("-----------------------------");    	
        
        JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(convertToJson(input.trim()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
		try {
			printSortJsonObject(jsonObj, 0);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
        in.close();
    }

    /**  
     * Print JSON in sorted alphabetically by keys
     * @param
     * 
     */      
    private static void printSortJsonObject(JSONObject jsonObject, int level) throws JSONException, JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper(); 
    	JsonNode jsonNode = mapper.readTree(jsonObject.toString());
    	
    	//GSON is needed because it keeps order in JSON
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(convertNode(jsonNode)).getAsJsonObject();
    	
    	print (json,0);
    }
    
    /**  
     * Print json keys only in required format
     * @param
     * 
     */      
    private static void print(JsonObject json, int level) throws JSONException {    
    	String dash = "";

    	for (Entry<String, JsonElement> e : json.entrySet()) {
        	for (int x=0; x<level; x++)
	        {
	        	dash += "-";
	        }
	    	if (level==0)
	    		System.out.println(dash + "" + e.getKey().trim());
	    	else { 
	    		System.out.println(dash + " " + e.getKey().trim());
	    	}
        
	    	if ( e.getValue() instanceof JsonObject ) {
	        		print(e.getValue().getAsJsonObject(), level+1);
	    	}
	    	dash="";
        }
    }    
    
    /**  
     * Converting input string into json
     * @param
     * 
     */  
    private static String convertToJson(String input) {
    	String ret = input.replace("(", " { ");
    	ret = ret.replace(")", " } ");

    	List<String> items = Arrays.asList(ret.split("\\s*,\\s*"));
   	
    	String tmp = "";

    	for (int x=0; x<items.size(); x++)
    	{
    		items.set(x, doubleQuotes(items.get(x).trim()));
    		String s = items.get(x);
    		if (x == 0) {
    			s = removeFirstChar (s);
    			s = s.replace("{", "{ \"");
    		} 
    		
    		else {    		
    			s = s.replace("{", "\": { \"");
    		}
    		if (x == items.size()-1) {
    			s = removeLastChar (s);
    			s = s.replace("}", "\":\"\" }");
    		}

    		else 
    			s = s.replace("}", "\":\"\" },");

    		if (s.contains("},"))
    			if (s.startsWith("\"\""))
    			  tmp += removeFirstChar(s);
    			else 
    				tmp += s;
    		else if (x != items.size()-1)
    			tmp += s + ":\"\" ,";
    		else 
    			tmp += s;
    		
        	if (tmp.substring(tmp.length() - 1).contains("\"")) {
        		tmp = tmp.substring(0, tmp.length()-1);

        	}
    	}

    	//remove white space
    	tmp = tmp.replaceAll("\\s+","");

    	//exception
    	// -- Better approach would be to use POJOs! :)
    	tmp = tmp.replace("}\":\"\"}" , "}}");
    	tmp = tmp.replace("},\":\"\"}" , "}}");
    	tmp = tmp.replace("}\":\"\"}" , "}}");
    	tmp = tmp.replace("},\":\"\"}" , "}}");
    
    	return tmp;
    }
    
    //---------------------------------------------------    
    //--------- H E L P E R  M E T H O D S --------------
    //---------------------------------------------------
    
    private static String doubleQuotes(String str) {
    	
        return (str != null ? "\"" + str + "\"" : null);
    }
    
    private static String removeLastChar(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='x') {
          str = str.substring(0, str.length()-1);
        }
        return str;
    }
    
    private static String removeFirstChar(String str) {
    	 return str.substring(1);
    }

    private static final ObjectMapper SORTED_MAPPER = new ObjectMapper();
    static {
        SORTED_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    private static String convertNode(final JsonNode node) throws JsonProcessingException {
        final Object obj = SORTED_MAPPER.treeToValue(node, Object.class);
        
        final String json = SORTED_MAPPER.writeValueAsString(obj);
        return json;
    }

    
}

