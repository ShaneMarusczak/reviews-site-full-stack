package reviewssitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long Id;

	private String name;
	private String image;
	private String content;

	@ManyToOne
	private Category category;
	
	@ManyToMany(mappedBy = "reviews")
	private Collection<Tag> tags;
	
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;
	

	// JPA empty constructor
	Review() {
	}

	Review(String name, String image, String content, Category category) {
		this.name = name;
		this.image = image;
		this.content = content;
		this.category = category;
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

	public Category getCategory() {
		return category;
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
