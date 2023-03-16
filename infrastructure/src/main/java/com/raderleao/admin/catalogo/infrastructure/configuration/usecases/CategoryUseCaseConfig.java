package com.raderleao.admin.catalogo.infrastructure.configuration.usecases;

import com.raderleao.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.raderleao.admin.catalogo.application.category.create.DefaultCreateCategoryUseCase;
import com.raderleao.admin.catalogo.application.category.delete.DefaultDeleteCategoryUseCase;
import com.raderleao.admin.catalogo.application.category.delete.DeleteCategoryUseCase;
import com.raderleao.admin.catalogo.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.raderleao.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.raderleao.admin.catalogo.application.category.retrieve.list.DefaultListCategoriesUseCase;
import com.raderleao.admin.catalogo.application.category.retrieve.list.ListCategoriesUseCase;
import com.raderleao.admin.catalogo.application.category.update.DefaultUpdateCategoryUseCase;
import com.raderleao.admin.catalogo.application.category.update.UpdateCategoryUseCase;
import com.raderleao.admin.catalogo.domain.category.CategoryGateway;
import com.raderleao.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import com.raderleao.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {

        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

}
