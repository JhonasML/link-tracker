package com.meli.linktracker.controller;

import com.meli.linktracker.dto.RedirectMetricsDTO;
import com.meli.linktracker.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/{linkId}")
    public RedirectMetricsDTO getRedirects(@PathVariable String linkId){
        var redirections = metricsService.getRedirections(linkId);
        return  new RedirectMetricsDTO(redirections);
    }
}
