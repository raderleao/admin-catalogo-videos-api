package com.raderleao.admin.catalogo.application.castmember.create;

import com.raderleao.admin.catalogo.application.UseCase;

public sealed abstract class CreateCastMemberUseCase
    extends UseCase<CreateCastMemberCommand, CreateCastMemberOutput>
    permits DefaultCreateCastMemberUseCase {
}
