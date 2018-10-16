package reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	ReviewRepository reviewRepo;

	@MockBean
	CategoryRepository categoryRepo;

	@Mock
	private Review review;

	@Mock
	private Review review2;
	
	@Mock
	private Category category;
	
	@Mock
	private Category category2;

	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long testReviewId = 1L;
		when(reviewRepo.findById(testReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));

	}

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long testReviewId = 1L;
		when(reviewRepo.findById(testReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());

	}

	@Test
	public void shouldNotBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("review", is(review)));

	}

	@Test
	public void shouldRouteToAllReviewViews() throws Exception {
		mvc.perform(get("/all-reviews")).andExpect(view().name(is("reviews")));

	}

	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/all-reviews")).andExpect(status().isOk());

	}

	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/all-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
		
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long testCategoryId = 1;
		when(categoryRepo.findById(testCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
