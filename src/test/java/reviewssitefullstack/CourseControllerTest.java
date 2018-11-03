package reviewssitefullstack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CourseControllerTest {

	@InjectMocks
	private ReviewController testController;

	@Mock
	private Review review;

	@Mock
	private Review review2;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private CategoryRepository categoryRepo;

	@Mock
	private Category category;

	@Mock
	private Category category2;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewtoModel() throws ReviewNotFoundException {
		long testReviewId = 1L;
		when(reviewRepo.findById(testReviewId)).thenReturn(Optional.of(review));

		testController.findOneReview(testReviewId, model);

		verify(model).addAttribute("review", review);

	}

	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, review2);

		when(reviewRepo.findAll()).thenReturn(allReviews);

		testController.findAllReviews(model);

		verify(model).addAttribute("reviews", allReviews);
	}

	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long testCategoryId = 1L;
		when(categoryRepo.findById(testCategoryId)).thenReturn(Optional.of(category));

		testController.findOneCategory(testCategoryId, model);
		verify(model).addAttribute("categories", category);
	}

	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category, category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		testController.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	
	
	
	
	
	

}
