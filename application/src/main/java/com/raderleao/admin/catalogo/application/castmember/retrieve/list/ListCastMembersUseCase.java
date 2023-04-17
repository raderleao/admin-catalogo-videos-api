package com.raderleao.admin.catalogo.application.castmember.retrieve.list;

import com.raderleao.admin.catalogo.application.UseCase;
import com.raderleao.admin.catalogo.domain.pagination.Pagination;
import com.raderleao.admin.catalogo.domain.pagination.SearchQuery;

public sealed abstract class ListCastMembersUseCase
    extends UseCase<SearchQuery, Pagination<CastMemberListOutput>>
    permits DefaultListCastMembersUseCase {
}
