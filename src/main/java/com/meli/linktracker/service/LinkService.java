package com.meli.linktracker.service;

import com.meli.linktracker.dao.LinkDao;
import com.meli.linktracker.entity.Link;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class LinkService {

    private final LinkDao linkDao;

    @Autowired
    public LinkService(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    public Link create(Link link) {
        if (Objects.nonNull(link.getPassword())){
            link.setPassword(encryptPassword(link));
        }

        return linkDao.persist(link);
    }

    public String redirect(String linkId, String password) {
        var link = linkDao.fetch(linkId);
        if (Objects.isNull(link)) {
            throw new IllegalArgumentException("Link not found");
        }

        if (Objects.nonNull(link.getPassword()) && !link.getPassword().equals(encryptPassword(password.getBytes()))){
            throw new IllegalArgumentException("Password invalid");
        }

        if (!link.isActive()) {
            throw new IllegalArgumentException("Link redirect is inative");
        }

        link.incrementRedirects();
        linkDao.update(linkId, link);

        return link.getLink();
    }

    public void invalidate(String linkId) {
        var link = linkDao.fetch(linkId);
        if (Objects.isNull(link)) {
            throw new IllegalArgumentException("Link not found");
        }

        link.setActive(false);
        linkDao.update(linkId, link);
    }

    private String encryptPassword(Link link) {
        return encryptPassword(link.getPassword().getBytes());
    }

    private String encryptPassword(byte[] bytes){
        return Arrays.toString(Base64.encodeBase64(bytes));
    }
}
