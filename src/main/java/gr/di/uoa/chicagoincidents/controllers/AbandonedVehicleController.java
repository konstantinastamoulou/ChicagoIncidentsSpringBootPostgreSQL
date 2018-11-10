package gr.di.uoa.chicagoincidents.controllers;

import gr.di.uoa.chicagoincidents.model.AbandonedVehicle;
import gr.di.uoa.chicagoincidents.repositories.AbandonedVehicleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/abandoned_vehicle")
@CrossOrigin(origins = "*")
public class AbandonedVehicleController {

    @Autowired
    private AbandonedVehicleRepository abandonedVehicleRepository;

    @Value("${pageSize}")
    private int pageSize;


    @RequestMapping(value="/create", method={RequestMethod.POST},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("service_request_number") @Valid String service_request_number,
            @RequestParam("status") @Valid String status,
            @RequestParam("completion_date") @Valid String completion_date,
            @RequestParam("creation_date") @Valid String creation_date
    ) throws JsonProcessingException {

        AbandonedVehicle abandonedVehicle = new AbandonedVehicle();
        abandonedVehicle.setServiceRequestNumber(service_request_number);
        abandonedVehicle.setStatus(status);
        abandonedVehicle.setCompletionDate(completion_date);
        abandonedVehicle.setCreationDate(creation_date);

        abandonedVehicleRepository.save(abandonedVehicle);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(abandonedVehicle);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value="/list", method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<AbandonedVehicle> abandonedVehicles=null;
        abandonedVehicles = abandonedVehicleRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(abandonedVehicles);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }
}
