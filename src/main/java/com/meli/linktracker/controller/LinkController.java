package com.meli.linktracker.controller;

import com.meli.linktracker.dto.RequestLinkDTO;
import com.meli.linktracker.dto.ResponseLinkDTO;
import com.meli.linktracker.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping
public class LinkController {

    private LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/link")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseLinkDTO criarLink(@RequestBody @Valid RequestLinkDTO linkDto) {
        var createdLink = linkService.create(linkDto.toModel());

        return createdLink.toResponseDTO();
    }

    @GetMapping("/link/{linkId}")
    public void redirect(@PathVariable String linkId, @RequestParam String password, HttpServletResponse response) throws IOException {
        var foundLink = linkService.redirect(linkId, password);
        response.sendRedirect(foundLink);
    }

    @GetMapping("/invalidate/{linkId}")
    public void invalidate(@PathVariable String linkId) {
        linkService.invalidate(linkId);
    }
}
