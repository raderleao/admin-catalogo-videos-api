package com.raderleao.admin.catalogo.application.category.create;

import com.raderleao.admin.catalogo.domain.category.Category;
import com.raderleao.admin.catalogo.domain.category.CategoryGateway;
import com.raderleao.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import static io.vavr.API.Left;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aCategory = Category.newCategory(aName,aDescription, isActive);
        aCategory.validate(notification);


        return notification.hasError() ? Left(notification) : create(aCategory);

    }

    private Either<Notification, CreateCategoryOutput> create(Category aCategory) {
        return API.Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .map(CreateCategoryOutput::from)
                .mapLeft(Notification::create);
    }
}
