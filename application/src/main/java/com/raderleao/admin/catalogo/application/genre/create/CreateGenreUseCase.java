package com.raderleao.admin.catalogo.application.genre.create;

import com.raderleao.admin.catalogo.application.UseCase;
import com.raderleao.admin.catalogo.application.category.create.CreateCategoryCommand;
import com.raderleao.admin.catalogo.application.category.create.CreateCategoryOutput;
import com.raderleao.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateGenreUseCase
    extends UseCase<CreateGenreCommand, CreateGenreOutput> {
}
