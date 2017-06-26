package facebook.impl;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import facebook.profile.Facebook;
import facebook.profile.Post;

@Component
public class FacebookApi implements FacebookService {
	
	HashMap<String, Facebook> fbProfilesMap = LoadFbProfiles.loadFbProfilesFromJsonFiles();

	@Override
	public Facebook findById(String id) throws NotFoundException {
		if (fbProfilesMap.containsKey(id) == true) {
			return fbProfilesMap.get(id);
		}
		
		throw new NotFoundException();
		
	}

	@Override
	public Map<String, Long> findMostCommonWords() {
		Map<String, Long> WordCount = new HashMap<>();

		fbProfilesMap.forEach((id,profile)->{
			List<Post> Posts = profile.getPosts();

			Map<String, Long> TempWordCount = Posts
					.stream()
					.map(post -> post.getMessage().split("[\\s\\.!():]+"))
					.flatMap(array->Arrays.stream(array))
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			TempWordCount
				.entrySet()
				.stream()
				.forEach(
					e -> WordCount.put(e.getKey(), WordCount.containsKey(e.getKey()) == true ? WordCount.get(e.getKey()) + 1 : e.getValue()));

		});

		return WordCount;

	}

	@Override
	public Set<String> findPostIdsByKeyword(String word) {
		Set<String> IdOfPostsContaintsKeyword = new HashSet<>();

		fbProfilesMap.values().forEach(profile->{
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
		Set<Facebook> sortedFbProfilesList = fbProfilesMap.values().stream()
				.sorted((Comparator.comparing(Facebook::getFirstname).thenComparing(Facebook::getLastname))).collect(Collectors.toSet());

		return sortedFbProfilesList;

	}

}