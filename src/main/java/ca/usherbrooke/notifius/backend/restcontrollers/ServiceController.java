package ca.usherbrooke.notifius.backend.restcontrollers;

import ca.usherbrooke.notifius.backend.models.Service;
import ca.usherbrooke.notifius.backend.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/services")
public class ServiceController
{
    @Autowired
    private ServiceService serviceService;

    @GetMapping("")
    public Map<Service, String[]> getAllService()
    {
        return serviceService.getAll();
    }
}
