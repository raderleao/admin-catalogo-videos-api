package com.raderleao.admin.catalogo.infrastructure.castmember.models;

import com.raderleao.admin.catalogo.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}
