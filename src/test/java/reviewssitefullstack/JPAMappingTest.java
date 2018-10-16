package reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingTest {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TestEntityManager entityManager;

	@Test
	public void shouldSaveAndLoadReview() {
		Category testCategory = categoryRepo.save(new Category("Category"));
		Review testReview = reviewRepo.save(new Review("Review", testCategory));
		long reviewId = testReview.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		Review finalResult = result.get();
		assertThat(finalResult.getTitle(), is("Review"));

	}

	@Test
	public void shouldGenerateReviewId() {
		Category testCategory = categoryRepo.save(new Category("Category"));
		Review testReview = reviewRepo.save(new Review("Review", testCategory));
		long reviewId = testReview.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(reviewId, is(greaterThan(0L)));

	}

	@Test
	public void shouldSaveAndLoadCategory() {
		Category testCategory = categoryRepo.save(new Category("Category"));
		long categoryId = testCategory.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		Category finalResult = result.get();
		assertThat(finalResult.getName(), is("Category"));
	}

	@Test
	public void shouldGenerateCategoryId() {
		Category testCategory = categoryRepo.save(new Category("Category"));
		long categoryId = testCategory.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(categoryId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishReviewToCategoryRelationship() {
		Category testCategory = categoryRepo.save(new Category("testtest"));
		Category testCategory2 = categoryRepo.save(new Category("testtesttest"));

		Review testReview = new Review("test", testCategory, testCategory2);
		testReview = reviewRepo.save(testReview);
		long reviewId = testReview.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		testReview = result.get();

		assertThat(testReview.getCategories(), containsInAnyOrder(testCategory, testCategory2));

	}

}
