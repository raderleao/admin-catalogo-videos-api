package com.raderleao.admin.catalogo.domain.video;

import com.raderleao.admin.catalogo.domain.castmember.CastMemberID;
import com.raderleao.admin.catalogo.domain.category.CategoryID;
import com.raderleao.admin.catalogo.domain.genre.GenreID;

import java.util.Set;

public record VideoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction,
        Set<CastMemberID> castMembers,
        Set<CategoryID> categories,
        Set<GenreID> genres
) {
}
