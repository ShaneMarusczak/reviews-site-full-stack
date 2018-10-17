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
		Category test1 = new Category("category1");
		test1 = categoryRepo.save(test1);
		Category test2 = new Category("category2");
		test1 = categoryRepo.save(test2);
		Category test3 = new Category("category3");
		test1 = categoryRepo.save(test3);
		
		Review testReview1 = new Review("review1", "review1", "review1", test1, test2);
		testReview1 = reviewRepo.save(testReview1);
		Review testReview2 = new Review("review2", "review2", "review2", test2);
		testReview2 = reviewRepo.save(testReview2);
		Review testReview3 = new Review("review3", "review3", "review3", test1, test3);
		testReview3 = reviewRepo.save(testReview3);
	}
	
	
		
}
