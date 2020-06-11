package ca.usherbrooke.notifius.restcontrollers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class DocController
{
    @GetMapping(path = "")
    public ResponseEntity<Void> redirect()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("https://documenter.getpostman.com/view/11266465/SzmmTEBG?version=latest"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
