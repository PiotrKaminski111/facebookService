package facebook.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import facebook.profile.Facebook;

public class LoadFbProfiles {
	
	public static HashMap<String, Facebook> loadFbProfilesFromJsonFiles() {
		HashMap<String, Facebook> fbProfilesList = new HashMap<String, Facebook>();
		
		ClassLoader classLoader = LoadFbProfiles.class.getClassLoader();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	List<String> listOfResources = Arrays.asList("f1", "f2", "f3", "f4", "f5");
    		
    	listOfResources.forEach(item->{
			try {
				Facebook facebook = mapper.readValue(new File(classLoader.getResource(item + ".json").getFile()), Facebook.class);
				fbProfilesList.put(facebook.getId(), facebook);
				
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
		});
		
		return fbProfilesList;
	}
}
