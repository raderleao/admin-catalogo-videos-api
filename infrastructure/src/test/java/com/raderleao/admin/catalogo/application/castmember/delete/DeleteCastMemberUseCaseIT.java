package com.raderleao.admin.catalogo.application.castmember.delete;

import com.raderleao.admin.catalogo.IntegrationTest;
import com.raderleao.admin.catalogo.domain.castmember.CastMember;
import com.raderleao.admin.catalogo.domain.castmember.CastMemberGateway;
import com.raderleao.admin.catalogo.domain.castmember.CastMemberID;
import com.raderleao.admin.catalogo.infrastructure.castmember.persistence.CastMemberJpaEntity;
import com.raderleao.admin.catalogo.infrastructure.castmember.persistence.CastMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static com.raderleao.admin.catalogo.domain.Fixture.CastMembers.type;
import static com.raderleao.admin.catalogo.domain.Fixture.name;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@IntegrationTest
public class DeleteCastMemberUseCaseIT {

    @Autowired
    private DeleteCastMemberUseCase useCase;

    @Autowired
    private CastMemberRepository castMemberRepository;

    @SpyBean
    private CastMemberGateway castMemberGateway;

    @Test
    public void givenAValidId_whenCallsDeleteCastMember_shouldDeleteIt(){
        // given
        final var aMember = CastMember.newMember(name(), type());
        final var aMemberTwo = CastMember.newMember(name(), type());

        final var expectedId = aMember.getId();

        this.castMemberRepository.saveAndFlush(CastMemberJpaEntity.from(aMember));
        this.castMemberRepository.saveAndFlush(CastMemberJpaEntity.from(aMemberTwo));
        Assertions.assertEquals(2, this.castMemberRepository.count());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // then
        verify(castMemberGateway).deleteById(expectedId);

        Assertions.assertEquals(1, this.castMemberRepository.count());
        Assertions.assertFalse(this.castMemberRepository.existsById(expectedId.getValue()));
        Assertions.assertTrue(this.castMemberRepository.existsById(aMemberTwo.getId().getValue()));
    }

    @Test
    public void givenAnInvalidId_whenCallsDeleteCastMember_shouldBeOk(){
        // given
        this.castMemberRepository.saveAndFlush(
                CastMemberJpaEntity.from(
                        CastMember.newMember(name(), type())
                )
        );

        final var expectedId = CastMemberID.from("123");

        Assertions.assertEquals(1, this.castMemberRepository.count());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // then
        verify(castMemberGateway).deleteById(eq(expectedId));
        Assertions.assertEquals(1, this.castMemberRepository.count());
    }

    @Test
    public void givenAValidId_whenCallsDeleteCastMemberAndGatewayThrowsException_shouldReceiveException(){
        // given
        final var aMember = CastMember.newMember(name(), type());

        this.castMemberRepository.saveAndFlush(CastMemberJpaEntity.from(aMember));
        Assertions.assertEquals(1, this.castMemberRepository.count());

        final var expectedId = aMember.getId();

        doThrow(new IllegalStateException("Gateway error"))
                .when(castMemberGateway).deleteById(any());

        // when
        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        // then
        verify(castMemberGateway).deleteById(eq(expectedId));
        Assertions.assertEquals(1, this.castMemberRepository.count());
    }
}
