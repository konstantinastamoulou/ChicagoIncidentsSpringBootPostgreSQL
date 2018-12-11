package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.AlleyLightOut;
import gr.di.uoa.chicagoincidents.repositories.AlleyLightOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@RestController
@RequestMapping("/alley_light_out")
@CrossOrigin(origins = "*")
public class AlleyLightOutController {

    @Autowired
    private AlleyLightOutRepository alleyLightOutRepository;

    @Value("${pageSize}")
    private int pageSize;


//    @RequestMapping(value="/create", method={RequestMethod.POST},produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> create(
//            @RequestParam("current_activity") @Valid String current_activity,
//            @RequestParam("most_recent_action") @Valid String most_recent_action,
//            @RequestParam("SSA") @Valid String SSA,
//            @RequestParam("license_plate") @Valid String license_plate,
//            @RequestParam("vehicle_model") @Valid String vehicle_model,
//            @RequestParam("vehicle_color") @Valid String vehicle_color,
//            @RequestParam("days_vehicle_reported_as_parked") @Valid String days_vehicle_reported_as_parked
//    ) throws JsonProcessingException {
//
//        AbandonedVehicle abandonedVehicle = new AbandonedVehicle();
//        abandonedVehicle.setCurrentActivity(current_activity);
//        abandonedVehicle.setMostRecentAction(most_recent_action);
//        abandonedVehicle.setSSA(SSA);
//        abandonedVehicle.setLicensePlate(license_plate);
//        abandonedVehicle.setVehicleModel(vehicle_model);
//        abandonedVehicle.setVehicleColor(vehicle_color);
//        abandonedVehicle.setDaysVehicleReportedAsParked(days_vehicle_reported_as_parked);
//
//        abandonedVehicleRepository.save(abandonedVehicle);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
//                .writeValueAsString(abandonedVehicle);
//
//        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
//    }

    @RequestMapping(value="/list", method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<AlleyLightOut> alleyLightsOut;
        alleyLightsOut = alleyLightOutRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(alleyLightsOut);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/insert_data", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertData() {
        String csvFile = "src/main/resources/311-service-requests-alley-lights-out.csv";
        AlleyLightOut alleyLightOut;
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


                alleyLightOut = new AlleyLightOut(
                        date.getTime(),   // Date creationDate
                        line[1],          // String status
                        date2.getTime(),  // Date completionDate
                        line[3],          // String serviceRequestNumber
                        line[4],          // String serviceRequestType
                        line[5],         // String streetAddress
                        (!line[6].isEmpty()) ? Integer.valueOf(line[6]) : null,   // Integer zipCode
                        (!line[7].isEmpty()) ? Double.valueOf(line[7])  : null,   // Double xCoordinate
                        (!line[8].isEmpty()) ? Double.valueOf(line[8])  : null,   // Double yCoordinate
                        (!line[9].isEmpty()) ? Integer.valueOf(line[9]) : null,   // Integer ward
                        (!line[10].isEmpty()) ? Integer.valueOf(line[10]) : null,   // Integer policeDistrict
                        (!line[11].isEmpty()) ? Integer.valueOf(line[11]) : null,   // Integer communityArea
                        (!line[12].isEmpty()) ? Double.valueOf(line[12])  : null,   // Double latitude
                        (!line[13].isEmpty()) ? Double.valueOf(line[13])  : null,   // Double longitude
                        line[14]);         // String location


                alleyLightOutRepository.save(alleyLightOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
