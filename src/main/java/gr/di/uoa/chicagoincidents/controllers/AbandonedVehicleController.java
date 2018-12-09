package gr.di.uoa.chicagoincidents.controllers;

import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.AbandonedVehicle;
import gr.di.uoa.chicagoincidents.repositories.AbandonedVehicleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.di.uoa.chicagoincidents.repositories.CountRequestsPerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@RestController
@RequestMapping("/abandoned_vehicle")
@CrossOrigin(origins = "*")
public class AbandonedVehicleController {

    @Autowired
    private AbandonedVehicleRepository abandonedVehicleRepository;

    @Autowired
    private CountRequestsPerTypeRepository countRequestsPerTypeRepository;

    @Value("${pageSize}")
    private int pageSize;


    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("current_activity") @Valid String current_activity,
            @RequestParam("most_recent_action") @Valid String most_recent_action,
            @RequestParam("SSA") @Valid String SSA,
            @RequestParam("license_plate") @Valid String license_plate,
            @RequestParam("vehicle_model") @Valid String vehicle_model,
            @RequestParam("vehicle_color") @Valid String vehicle_color,
            @RequestParam("days_vehicle_reported_as_parked") @Valid String days_vehicle_reported_as_parked
    ) throws JsonProcessingException {

        AbandonedVehicle abandonedVehicle = new AbandonedVehicle();
        abandonedVehicle.setCurrentActivity(current_activity);
        abandonedVehicle.setMostRecentAction(most_recent_action);
        abandonedVehicle.setSSA(SSA);
        abandonedVehicle.setLicensePlate(license_plate);
        abandonedVehicle.setVehicleModel(vehicle_model);
        abandonedVehicle.setVehicleColor(vehicle_color);
        abandonedVehicle.setDaysVehicleReportedAsParked(days_vehicle_reported_as_parked);

        abandonedVehicleRepository.save(abandonedVehicle);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(abandonedVehicle);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<AbandonedVehicle> abandonedVehicles;
        abandonedVehicles = abandonedVehicleRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(abandonedVehicles);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/insert_data", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertData() {
        String csvFile = "src/main/resources/311-service-requests-abandoned-vehicles.csv";
        AbandonedVehicle abandonedVehicle;
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                String creationDate = line[0].replace("T", " ");
                DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH);
                Calendar date = Calendar.getInstance();
                date.setTime(format.parse(creationDate));

                String completionDate = line[2].replace("T", " ");
                DateFormat format2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH);
                Calendar date2 = Calendar.getInstance();
                date2.setTime(format2.parse(completionDate));


                abandonedVehicle = new AbandonedVehicle(
                        date.getTime(),   // Date creationDate
                        line[1],          // String status
                        date2.getTime(),  // Date completionDate
                        line[3],          // String serviceRequestNumber
                        line[4],          // String serviceRequestType
                        line[11],         // String streetAddress
                        (!line[12].isEmpty()) ? Integer.valueOf(line[12]) : null,   // Integer zipCode
                        (!line[13].isEmpty()) ? Double.valueOf(line[13])  : null,   // Double xCoordinate
                        (!line[14].isEmpty()) ? Double.valueOf(line[14])  : null,   // Double yCoordinate
                        (!line[15].isEmpty()) ? Integer.valueOf(line[15]) : null,   // Integer ward
                        (!line[16].isEmpty()) ? Integer.valueOf(line[16]) : null,   // Integer policeDistrict
                        (!line[17].isEmpty()) ? Integer.valueOf(line[17]) : null,   // Integer communityArea
                        (!line[19].isEmpty()) ? Double.valueOf(line[19])  : null,   // Double latitude
                        (!line[20].isEmpty()) ? Double.valueOf(line[20])  : null,   // Double longitude
                        line[21],         // String location
                        line[8],          // String currentActivity
                        line[9],          // String mostRecentAction
                        line[18],         // String SSA
                        line[5],          // String licensePlate
                        line[6],          // String vehicleModel
                        line[7],          // String vehicleColor
                        line[10]);        // String daysVehicleReportedAsParked

                abandonedVehicleRepository.save(abandonedVehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}


