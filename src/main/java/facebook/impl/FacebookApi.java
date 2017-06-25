package facebook.impl;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;

import facebook.profile.Facebook;
import facebook.profile.Post;

@Component
public class FacebookApi implements FacebookService {
	
	HashMap<String, Facebook> fbProfilesList = LoadFbProfiles.loadFbProfilesFromJsonFiles();

	@Override
	public Facebook findById(String id) throws NotFoundException {
		if (fbProfilesList.containsKey(id) == true) {
			return fbProfilesList.get(id);
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public Map<String, Long> findMostCommonWords() {
		return null;
	}

	@Override
	public Set<String> findPostIdsByKeyword(String word) {
		
		fbProfilesList.forEach((k,profile)->{
			List<Post> Posts = profile.getPosts();
			
			Posts.stream().filter(line -> !word.equals(line))
            .collect(Collectors.toSet());   	
		});
		
		return Posts;
	}

	@Override
	public Set<Facebook> findAll() {
		Set<Facebook> sortedFbProfilesList = fbProfilesList.values().stream().sorted((e1,e2)->
        	e1.getFirstname().compareTo(e2.getFirstname())
        ).collect(Collectors.toSet());
		
		return sortedFbProfilesList;
	
	}

}