package reviewssitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewSitePopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRepository categoryRepo;

	@Override
	public void run(String... args) throws Exception {
		Category test1 = new Category("Dogs");
		test1 = categoryRepo.save(test1);
		Category test2 = new Category("Cats");
		test2 = categoryRepo.save(test2);
		Category test3 = new Category("Birds");
		test3 = categoryRepo.save(test3);
		
		Review testReview1 = new Review("Spot", "spot.png", "Spot is a carefree puppy that likes to play!", test1);
		testReview1 = reviewRepo.save(testReview1);
		Review testReview2 = new Review("Garfield", "garfield.jpg", "Garfield is a very, very lazy and slow cat that loves lasagna.", test2);
		testReview2 = reviewRepo.save(testReview2);
		Review testReview3 = new Review("Tweety", "tweety.jpg", "Tweety is a smart bird that is always staying just out of reach of Sylvester.", test3);
		testReview3 = reviewRepo.save(testReview3);
	}
	
	
		
}
