package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ca.usherbrooke.notifius.backend.services.ServiceService.EXCLUDED_SERVICES;

@RestController
@RequestMapping("/services")
public class ServiceController
{
    @Autowired
    private ServiceService serviceService;

    @GetMapping("")
    public List<Map<String, String>> getAllService()
    {
        return serviceService.getAll().stream()
                             .filter(service -> !EXCLUDED_SERVICES.contains(service))
                             .map(service -> {
                                 Map<String, String> s = new HashMap<>();
                                 s.put("id", service.name());
                                 s.put("displayName", service.getDisplayName());
                                 s.put("description", service.getDescription());
                                 return s;
                             })
                             .collect(Collectors.toList());
    }
}
