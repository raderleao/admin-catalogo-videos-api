package com.raderleao.admin.catalogo.infrastructure.castmember.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raderleao.admin.catalogo.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(
        @JsonProperty("name") String name,
        @JsonProperty("type") CastMemberType type
){
}
