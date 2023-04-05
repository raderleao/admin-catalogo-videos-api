package com.raderleao.admin.catalogo.application.genre.retrieve.list;

import com.raderleao.admin.catalogo.application.UseCase;
import com.raderleao.admin.catalogo.domain.pagination.Pagination;
import com.raderleao.admin.catalogo.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase
        extends UseCase<SearchQuery, Pagination<GenreListOutput>> {
}
