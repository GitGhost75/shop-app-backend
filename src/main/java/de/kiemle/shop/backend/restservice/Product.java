package de.kiemle.shop.backend.restservice;
import javax.persistence.*;
/**
 * 
 * @author Alexander Kiemle
 * 
 * "productId": 1,
 * "productName": "Leaf Rake",
 * "productCode": "GDN-0011",
 * "releaseDate": "March 19, 2021",
 * "description": "Leaf rake with 48-inch wooden handle.",
 * "price": 19.95,
 * "starRating": 3.2,
 * "imageUrl": "assets/images/leaf_rake.png"
 *
 */
@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long productId;
	private String productName;
	private String productCode;
	private String releaseDate;
	private String description;
	private double price;
	private double starRating;
	private String imageUrl;
	

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getStarRating() {
		return starRating;
	}

	public void setStarRating(double starRating) {
		this.starRating = starRating;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	  @Override
	  public String toString() {
	    return String.format(
	        "Product[id=%d, productName='%s', productCode='%s', releaseDate='%s', description='%s', price='%d', starRating='%d', imageUrl='%s']",
	        productId, productName, productCode, releaseDate, description, price, starRating, imageUrl);
	  }
}


