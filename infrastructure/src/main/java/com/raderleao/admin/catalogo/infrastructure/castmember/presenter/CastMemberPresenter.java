package com.raderleao.admin.catalogo.infrastructure.castmember.presenter;

import com.raderleao.admin.catalogo.application.castmember.retrieve.get.CastMemberOutput;
import com.raderleao.admin.catalogo.application.castmember.retrieve.list.CastMemberListOutput;
import com.raderleao.admin.catalogo.infrastructure.castmember.models.CastMemberListResponse;
import com.raderleao.admin.catalogo.infrastructure.castmember.models.CastMemberResponse;

public interface CastMemberPresenter {
    static CastMemberResponse present(final CastMemberOutput aMember) {
        return new CastMemberResponse(
                aMember.id(),
                aMember.name(),
                aMember.type(),
                aMember.createdAt(),
                aMember.updatedAt()
        );
    }

    static CastMemberListResponse present(final CastMemberListOutput aMember) {
        return new CastMemberListResponse(
                aMember.id(),
                aMember.name(),
                aMember.type().name(),
                aMember.createdAt().toString()
        );
    }
}
