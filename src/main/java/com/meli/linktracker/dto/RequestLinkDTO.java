package com.meli.linktracker.dto;

import com.meli.linktracker.entity.Link;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RequestLinkDTO {
    @URL
    @NotNull
    private String link;

    @Pattern(regexp = "\\w*")
    private String password;

    public Link toModel() {
        return new Link(link, password);
    }
}
