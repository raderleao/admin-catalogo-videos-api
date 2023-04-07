package com.raderleao.admin.catalogo.infrastructure.genre.presenters;

import com.raderleao.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.raderleao.admin.catalogo.application.category.retrieve.list.CategoryListOutput;
import com.raderleao.admin.catalogo.application.genre.retrieve.get.GenreOutput;
import com.raderleao.admin.catalogo.application.genre.retrieve.list.GenreListOutput;
import com.raderleao.admin.catalogo.infrastructure.category.models.CategoryListResponse;
import com.raderleao.admin.catalogo.infrastructure.category.models.CategoryResponse;
import com.raderleao.admin.catalogo.infrastructure.genre.models.GenreListResponse;
import com.raderleao.admin.catalogo.infrastructure.genre.models.GenreResponse;

public interface GenreApiPresenter {

    static GenreResponse present(final GenreOutput output) {
        return new GenreResponse(
                output.id(),
                output.name(),
                output.categories(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static GenreListResponse present(final GenreListOutput output) {
        return new GenreListResponse(
                output.id(),
                output.name(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
