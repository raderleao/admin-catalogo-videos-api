package com.raderleao.admin.catalogo.infrastructure.api.controllers;

import com.raderleao.admin.catalogo.application.category.create.CreateCategoryCommand;
import com.raderleao.admin.catalogo.application.category.create.CreateCategoryOutput;
import com.raderleao.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.raderleao.admin.catalogo.domain.pagination.Pagination;
import com.raderleao.admin.catalogo.domain.validation.handler.Notification;
import com.raderleao.admin.catalogo.infrastructure.api.CategoryAPI;
import com.raderleao.admin.catalogo.infrastructure.category.models.CreateCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, int sort, int dir) {
        return null;
    }
}
