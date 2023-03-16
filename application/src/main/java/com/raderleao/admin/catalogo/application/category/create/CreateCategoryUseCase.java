package com.raderleao.admin.catalogo.application.category.create;

import com.raderleao.admin.catalogo.application.UseCase;
import com.raderleao.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.beans.BeanProperty;

public abstract class CreateCategoryUseCase
    extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
