package com.raderleao.admin.catalogo.infrastructure.configuration.usecases;

import com.raderleao.admin.catalogo.application.castmember.create.CreateCastMemberUseCase;
import com.raderleao.admin.catalogo.application.castmember.create.DefaultCreateCastMemberUseCase;
import com.raderleao.admin.catalogo.application.castmember.delete.DefaultDeleteCastMemberUseCase;
import com.raderleao.admin.catalogo.application.castmember.delete.DeleteCastMemberUseCase;
import com.raderleao.admin.catalogo.application.castmember.retrieve.get.DefaultGetCastMemberByIdUseCase;
import com.raderleao.admin.catalogo.application.castmember.retrieve.get.GetCastMemberByIdUseCase;
import com.raderleao.admin.catalogo.application.castmember.retrieve.list.DefaultListCastMembersUseCase;
import com.raderleao.admin.catalogo.application.castmember.retrieve.list.ListCastMembersUseCase;
import com.raderleao.admin.catalogo.application.castmember.update.DefaultUpdateCastMemberUseCase;
import com.raderleao.admin.catalogo.application.castmember.update.UpdateCastMemberUseCase;
import com.raderleao.admin.catalogo.domain.castmember.CastMemberGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CastMemberUseCaseConfig {

    private final CastMemberGateway castMemberGateway;

    public CastMemberUseCaseConfig(CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Bean
    public CreateCastMemberUseCase createCastMemberUseCase() {
        return new DefaultCreateCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public DeleteCastMemberUseCase deleteCastMemberUseCase() {
        return new DefaultDeleteCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public GetCastMemberByIdUseCase getCastMemberByIdUseCase() {
        return new DefaultGetCastMemberByIdUseCase(castMemberGateway);
    }

    @Bean
    public ListCastMembersUseCase listCastMemberUseCase(){
        return new DefaultListCastMembersUseCase(castMemberGateway);
    }

    @Bean
    public UpdateCastMemberUseCase updateCastMemberUseCase() {
        return new DefaultUpdateCastMemberUseCase(castMemberGateway);
    }
}
