package com.wildbitsfoundry.filterstudio.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record IdDto<T>(T id) {
    @JsonCreator
    public IdDto(@JsonProperty("id") T id) {
        this.id = id;
    }
}
