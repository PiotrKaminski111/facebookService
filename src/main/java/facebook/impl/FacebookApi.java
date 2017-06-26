package facebook.impl;


import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

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
		Set<String> IdOfPostsContaintsKeyword = new HashSet<>();
		
		fbProfilesList.forEach((k,profile)->{
			List<Post> Posts = profile.getPosts();
					
			IdOfPostsContaintsKeyword.addAll(
				Posts
					.stream()
		            .filter(p -> p.getMessage().contains(word))
		            .map(Post::getId)
		            .collect(Collectors.toSet())
			);
        
		});
		
		return IdOfPostsContaintsKeyword;
	}

	@Override
	public Set<Facebook> findAll() {
		Set<Facebook> sortedFbProfilesList = fbProfilesList.values().stream()
				.sorted((Comparator.comparing(Facebook::getFirstname).thenComparing(Facebook::getLastname))).collect(Collectors.toSet());
		
		return sortedFbProfilesList;
	
	}

}