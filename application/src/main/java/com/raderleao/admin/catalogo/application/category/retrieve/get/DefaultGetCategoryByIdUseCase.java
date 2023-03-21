package com.raderleao.admin.catalogo.application.category.retrieve.get;

import com.raderleao.admin.catalogo.domain.category.Category;
import com.raderleao.admin.catalogo.domain.category.CategoryGateway;
import com.raderleao.admin.catalogo.domain.category.CategoryID;
import com.raderleao.admin.catalogo.domain.exceptions.DomainException;
import com.raderleao.admin.catalogo.domain.exceptions.NotFoundException;
import com.raderleao.admin.catalogo.domain.validation.Error;

import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public CategoryOutput execute(final String anIn) {
        final var anCategoryId = CategoryID.from(anIn);

        return this.categoryGateway.findById(anCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(anCategoryId));
    }

    private Supplier<NotFoundException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
