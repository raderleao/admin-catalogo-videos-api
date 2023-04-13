package com.raderleao.admin.catalogo.application.castmember.create;

import com.raderleao.admin.catalogo.domain.castmember.CastMember;

public record CreateCastMemberOutput(
        String id
) {
    public static CreateCastMemberOutput from(final CastMember aMember) {
        return new CreateCastMemberOutput(aMember.getId().getValue());
    }
}
