package com.meli.linktracker.service;

import com.meli.linktracker.dao.LinkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MetricsService {

    private LinkDao linkDao;

    @Autowired
    public MetricsService(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    public Integer getRedirections(String linkId){
        var linkFound=  linkDao.fetch(linkId);
        if(Objects.isNull(linkFound)){
            throw new IllegalArgumentException("Link not found");
        }

        return linkFound.getRedirects();
    }
}
