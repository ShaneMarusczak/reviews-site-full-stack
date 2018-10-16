package reviewssitefullstack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String title;

	@ManyToMany
	private Collection<Category> categories;

	// JPA empty constructor
	Review() {
	}

	Review(String title, Category... categories) {
		this.title = title;
		Set<Category> set = new HashSet<Category>();
		for (Category cat : categories) {
			set.add(cat);
		}
		this.categories = set;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
