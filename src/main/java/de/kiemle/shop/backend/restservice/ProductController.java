/**
 * 
 */
package de.kiemle.shop.backend.restservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander Kiemle
 *
 */
@RestController()
@RequestMapping("/api")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository repo;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "productId") String sort) {
		logger.info("find all products");

		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<Product> pageProducts = repo.findAll(paging);

		Map<String, Object> response = new HashMap<>();
		response.put("products", pageProducts.getContent());
		response.put("currentPage", pageProducts.getNumber());
		response.put("totalItems", pageProducts.getTotalElements());
		response.put("totalPages", pageProducts.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProduct(@PathVariable("id") long id) {
		logger.info("get product with id " + id);
		Product result = repo.findById(id).orElse(new Product());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/product/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> getProductCount() {
		long count = repo.count();
		logger.info("product count is " + count);
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode result = mapper.createObjectNode();
//		result.put("count", count);
		Map<String, Long> response = new HashMap<>();
		response.put("count", count);
		return new ResponseEntity<>(response, HttpStatus.OK);

//		try {
//			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
//		} catch (JsonProcessingException e) {
//			logger.error("error while creating json", e);
//			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
//		}
	}

	@PostMapping("/product")
	@CrossOrigin(origins = "http://localhost:4200")
	public Product create(@RequestBody Map<String, String> body) {

		logger.info("create new product");

		Product tmpProduct = new Product();
		tmpProduct.setProductName(body.get("productId"));
		tmpProduct.setProductCode(body.get("productCode"));
		tmpProduct.setReleaseDate(body.get("releaseDate"));
		tmpProduct.setDescription(body.get("description"));
		tmpProduct.setPrice(Double.parseDouble(body.get("price")));
		tmpProduct.setStarRating(Double.parseDouble(body.get("starRating")));
		tmpProduct.setImageUrl(body.get("imageUrl"));

		return repo.save(tmpProduct);
	}

}
