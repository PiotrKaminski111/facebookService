package facebook.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import facebook.profile.Facebook;

public class FacebookApiTest {

	@Test(expected=NotFoundException.class)
	public void whenNotExistingIdGivenNotFoundExceptionThrown() throws NotFoundException {
		FacebookApi facebookApi = new FacebookApi();	
		facebookApi.findById("12333");
		
	}
	
	@Test
	public void shouldReturnInstanceOfFacebookObject() throws NotFoundException {
		FacebookApi facebookApi = new FacebookApi();	
		Facebook profile = facebookApi.findById("1");

		assertTrue(profile instanceof Facebook);
		
	}

}
