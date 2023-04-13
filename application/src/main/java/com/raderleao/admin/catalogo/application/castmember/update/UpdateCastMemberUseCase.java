package com.raderleao.admin.catalogo.application.castmember.update;

import com.raderleao.admin.catalogo.application.UseCase;

public sealed abstract class UpdateCastMemberUseCase
    extends UseCase<UpdateCastMemberCommand, UpdateCastMemberOutput>
    permits DefaultUpdateCastMemberUseCase {
}
