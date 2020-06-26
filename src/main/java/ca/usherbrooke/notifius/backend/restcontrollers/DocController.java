/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.restcontrollers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class DocController
{
    @Value("${notifius.doc-url}")
    private String notifiusDocURL;

    @GetMapping(path = "/")
    public ResponseEntity<Void> redirect()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(notifiusDocURL));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
