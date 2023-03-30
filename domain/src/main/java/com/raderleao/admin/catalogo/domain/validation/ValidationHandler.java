package com.raderleao.admin.catalogo.domain.validation;

import java.util.List;
import java.util.Optional;

public interface ValidationHandler {

    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    <T> T validate(Validation<T> aValidation);

    List<Error> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Optional<Error> firtError() {
        return getErrors() != null && !getErrors().isEmpty()
                ? Optional.of(getErrors().get(0))
                : Optional.empty();
    }

    interface Validation<T> {
        T validate();
    }
}
