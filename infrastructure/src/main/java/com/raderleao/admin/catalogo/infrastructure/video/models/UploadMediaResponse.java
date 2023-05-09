package com.raderleao.admin.catalogo.infrastructure.video.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raderleao.admin.catalogo.domain.video.VideoMediaType;

public record UploadMediaResponse(
        @JsonProperty("video_id") String videoId,
        @JsonProperty("media_type") VideoMediaType mediaType
) {
}
