package com.accenture.blog.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.blog.entity.Category;
import com.accenture.blog.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	private Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Iterable<Category> list() {
		logger.debug("list");
		return categoryRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Category create(@RequestBody Category category) {
		logger.debug("create");
		return categoryRepository.save(category);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Category read(@PathVariable Long id) {
		logger.debug("read");
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return category.get();
		} else {
			return null;
		}
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@RequestBody Category category, @PathVariable Long id) {
		logger.debug("update");
		Optional<Category> findCategory = categoryRepository.findById(id);
		if (findCategory.isPresent()) {
			Category foundCategory = findCategory.get();
			foundCategory.setName(category.getName());
		}
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) {
		logger.debug("delete");
		Optional<Category> findCategory = categoryRepository.findById(id);
		if (findCategory.isPresent()) {
			Category foundCategory = findCategory.get();
			categoryRepository.delete(foundCategory);
		}
	}

}
