package com.ofonesie.ofonesie.models.data;

import com.ofonesie.ofonesie.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Integer> {
}
