package com.raderleao.admin.catalogo.application.category.create;

import com.raderleao.admin.catalogo.domain.category.Category;
import com.raderleao.admin.catalogo.domain.category.CategoryGateway;
import com.raderleao.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

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

        if (notification.hasError()) {
            //
        }
        return CreateCategoryOutput.from(this.categoryGateway.create(aCategory));

    }
}
