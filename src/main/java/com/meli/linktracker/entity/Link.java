package com.meli.linktracker.entity;


import com.meli.linktracker.dto.ResponseLinkDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Link {
    @NonNull
    private String link;
    private String linkId;
    private String password;
    private Integer redirects = 0;
    private boolean active = true;

    public Link(@NonNull String link, String password) {
        this.link = link;
        this.password = password;
    }

    public void incrementRedirects() {
        redirects++;
    }

    public ResponseLinkDTO toResponseDTO() {
        return new ResponseLinkDTO(this.getLinkId());
    }
}
