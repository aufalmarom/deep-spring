package com.domain.explore.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO {

    @NotEmpty(message = "name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
