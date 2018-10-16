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
	private long Id;

	private String name;
	private String image;
	private String content;

	@ManyToMany
	private Collection<Category> categories;

	// JPA empty constructor
	Review() {
	}

	Review(String name, String image, String content, Category... categories) {
		this.name = name;
		this.image = image;
		this.content = content;
		Set<Category> set = new HashSet<Category>();
		for (Category cat : categories) {
			set.add(cat);
		}
		this.categories = set;
	}

	public long getId() {
		return Id;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getContent() {
		return content;
	}

	public String getName() {
		return name;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
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
		if (Id != other.Id)
			return false;
		return true;
	}
	
	

}
