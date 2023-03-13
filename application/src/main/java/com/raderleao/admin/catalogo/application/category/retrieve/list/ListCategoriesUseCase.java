package com.raderleao.admin.catalogo.application.category.retrieve.list;

import com.raderleao.admin.catalogo.application.UseCase;
import com.raderleao.admin.catalogo.domain.category.CategorySearchQuery;
import com.raderleao.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
    extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
