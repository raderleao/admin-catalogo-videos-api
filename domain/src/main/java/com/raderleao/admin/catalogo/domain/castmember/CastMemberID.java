package com.raderleao.admin.catalogo.domain.castmember;

import com.raderleao.admin.catalogo.domain.Identifier;
import com.raderleao.admin.catalogo.domain.utils.IdUtils;

import java.util.Objects;

public class CastMemberID extends Identifier {

    private final String value;

    private CastMemberID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CastMemberID unique() {
        return CastMemberID.from(IdUtils.uuid());
    }

    public static CastMemberID from(final String anId) {
        return new CastMemberID(anId);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CastMemberID that = (CastMemberID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
