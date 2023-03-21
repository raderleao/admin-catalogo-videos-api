package com.raderleao.admin.catalogo.application.category.update;

import com.raderleao.admin.catalogo.domain.category.Category;
import com.raderleao.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(
        String id
) {
    public static UpdateCategoryOutput from(final String anId) {
        return new UpdateCategoryOutput(anId);
    }
    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId().getValue());
    }

}
