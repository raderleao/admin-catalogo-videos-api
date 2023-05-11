package com.raderleao.admin.catalogo.infrastructure.castmember.models;

import com.raderleao.admin.catalogo.domain.Fixture;

import com.raderleao.admin.catalogo.JacksonTest;
import com.raderleao.admin.catalogo.domain.utils.InstantUtils;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

@JacksonTest
class CastMemberResponseTest {

    @Autowired
    private JacksonTester<CastMemberResponse> json;

    @Test
    public void testMarshall() throws Exception {
        final var expectedId = "123";
        final var expectedName = Fixture.name();
        final var expectedType = Fixture.CastMembers.type();
        final var expectedCreatedAt = InstantUtils.now();
        final var expectedUpdatedAt = InstantUtils.now();

        final var response = new CastMemberResponse(
                expectedId,
                expectedName,
                expectedType,
                expectedCreatedAt,
                expectedUpdatedAt
        );

        final var actualJson = this.json.write(response);

        Assertions.assertThat(actualJson)
                .hasJsonPathValue("$.id", expectedId)
                .hasJsonPathValue("$.name", expectedName)
                .hasJsonPathValue("$.type", expectedType)
                .hasJsonPathValue("$.created_at", expectedCreatedAt)
                .hasJsonPathValue("$.updated_at", expectedUpdatedAt);
    }
}