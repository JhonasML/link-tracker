package com.meli.linktracker.dao;

import com.meli.linktracker.entity.Link;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class LinkDao {
    public static Map<String, Link> links = new HashMap<>();

    public Link persist(Link link) {
        link.setLinkId(UUID.randomUUID().toString());
        links.put(link.getLinkId(), link);

        return link;
    }

    public Link update(String id, Link link) {
        link.setLinkId(id);
        links.put(link.getLinkId(), link);
        return link;
    }

    public Link fetch(String id){
        return links.get(id);
    }

}
