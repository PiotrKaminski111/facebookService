package facebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import facebook.impl.FacebookApi;
import facebook.impl.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MainClass implements CommandLineRunner {
	
	@Autowired
	private FacebookApi facebookApi;

	@Override
	public void run(String... args) throws NotFoundException {
		System.out.println(this.facebookApi.findById("1").getFriends());
		this.facebookApi.findAll();
		this.facebookApi.findPostIdsByKeyword();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }

}