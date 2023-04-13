package com.raderleao.admin.catalogo.application.castmember.delete;

import com.raderleao.admin.catalogo.application.UnitUseCase;

public sealed abstract class DeleteCastMemberUseCase
    extends UnitUseCase<String>
    permits DefaultDeleteCastMemberUseCase {
}
