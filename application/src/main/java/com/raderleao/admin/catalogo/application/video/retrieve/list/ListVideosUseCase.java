package com.raderleao.admin.catalogo.application.video.retrieve.list;

import com.raderleao.admin.catalogo.application.UseCase;
import com.raderleao.admin.catalogo.domain.pagination.Pagination;
import com.raderleao.admin.catalogo.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase
        extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}
