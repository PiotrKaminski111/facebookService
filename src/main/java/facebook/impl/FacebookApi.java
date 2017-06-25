package facebook.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;

import facebook.profile.Facebook;

@Component
public class FacebookApi implements FacebookService {

	@Override
	public Facebook findById(String id) throws NotFoundException {
		HashMap<String, Facebook> fbProfilesList = LoadFbProfiles.loadFbProfilesFromJsonFiles();
	    
		if (fbProfilesList.containsKey(id) == true) {
			return fbProfilesList.get(id);
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public Map<String, Long> findMostCommonWords() {
		HashMap<String, Facebook> fbProfilesList = LoadFbProfiles.loadFbProfilesFromJsonFiles();
			
//		fbProfilesList.forEach(profile->{
//			profile.getPosts();
//			
//			profile.stream()
//			
//		});
		
	}

	@Override
	public Set<String> findPostIdsByKeyword(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Facebook> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
