//package gr.di.uoa.chicagoincidents.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import gr.di.uoa.chicagoincidents.model.AbandonedVehicle;
//import gr.di.uoa.chicagoincidents.repositories.AbandonedVehicleRepository;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.Test.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.io.IOException;
//import java.io.Reader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//
//@RestController
//@RequestMapping("/abandoned_vehicle")
//@CrossOrigin(origins = "*")
//public class VacantAbandonedBuildingController {
//
//    @Autowired
//    private AbandonedVehicleRepository abandonedVehicleRepository;
//
//    @Value("${pageSize}")
//    private int pageSize;
//
//
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
//
//    @RequestMapping(value="/list", method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> list(
//            @RequestParam("page") int page
//    ) throws JsonProcessingException {
//
//        Iterable<AbandonedVehicle> abandonedVehicles;
//        abandonedVehicles = abandonedVehicleRepository.findAll(PageRequest.of(page, pageSize));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
//                .writeValueAsString(abandonedVehicles);
//
//        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
//    }
//
//    @RequestMapping(value="/insert_data", method={RequestMethod.POST},produces = MediaType.APPLICATION_JSON_VALUE)
//    public void insertData() throws ParseException, IOException {
//        try (
//                Reader reader = Files.newBufferedReader(Paths.get("/Users/konstantinastamoulou/Documents/MSc/Database Management Systems/ChicagoIncidentsSpringBootPostgeSQL/src/main/resources/311-service-requests-abandoned-vehicles.csv"));
//                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
//                        .withHeader("Creation Date",
//                                "Status",
//                                "Completion Date",
//                                "Service Request Number",
//                                "Service Request Type",
//                                "License Plate",
//                                "Vehicle Make/Model",
//                                "Vehicle Color",
//                                "Current Activity",
//                                "Most Recent Action",
//                                "How Many Days Has the Vehicle Been Reported as Parked?",
//                                "Street Address",
//                                "ZIP Code",
//                                "X Coordinate",
//                                "Y Coordinate",
//                                "Ward",
//                                "Police District",
//                                "Community Area",
//                                "SSA",
//                                "Latitude",
//                                "Longitude",
//                                "Location"
//                        )
//                        .withIgnoreHeaderCase()
//                        .withTrim());
//        ) {
//            for (CSVRecord csvRecord : csvParser) {
//                // Accessing values by the names assigned to each column
//                System.out.println(csvRecord.get("Creation Date").replace("T", " "));
//
//                String string = csvRecord.get("Creation Date").replace("T", " ");
//
//                DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH);
//                Calendar date = Calendar.getInstance();
//                date.setTime(format.parse(string));
//                System.out.println(format.format(date.getTime())); // Mon Feb 23 05:30:55 GMT 2015
//
//
//                Date creationDate = date.getTime();
//
//                String status = csvRecord.get("Status");
//
//                String string2 = csvRecord.get("Completion Date").replace("T", " ");
//
//                DateFormat format2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH);
//                Calendar date2 = Calendar.getInstance();
//                date.setTime(format.parse(string));
//                System.out.println(format.format(date.getTime())); // Mon Feb 23 05:30:55 GMT 2015
//
//
//                Date completionDate = date2.getTime();
//
//                String serviceRequestNumber = csvRecord.get("Service Request Number");
//                String serviceRequestType = csvRecord.get("Service Request Type");
//                String streetAddress = csvRecord.get("Street Address");
//                Integer zipCode = Integer.valueOf(csvRecord.get("ZIP Code"));
//                Double xCoordinate = Double.valueOf(csvRecord.get("X Coordinate"));
//                Double yCoordinate = Double.valueOf(csvRecord.get("Y Coordinate"));
//                Integer ward = Integer.valueOf(csvRecord.get("Ward"));
//                Integer policeDistrict = Integer.valueOf(csvRecord.get("Police District"));
//                Integer communityArea = Integer.valueOf(csvRecord.get("Community Area"));
//                Double latitude = Double.valueOf(csvRecord.get("Latitude"));
//                Double longitude = Double.valueOf(csvRecord.get("Longitude"));
//                String location = csvRecord.get("Location");
//                String currentActivity = csvRecord.get("Current Activity");
//                String mostRecentAction = csvRecord.get("Most Recent Action");
//                String SSA = csvRecord.get("SSA");
//                String licensePlate = csvRecord.get("License Plate");
//                String vehicleModel = csvRecord.get("Vehicle Make/Model");
//                String vehicleColor = csvRecord.get("Vehicle Color");
//                String daysVehicleReportedAsParked = csvRecord.get("How Many Days Has the Vehicle Been Reported as Parked?");
//
//                AbandonedVehicle abandonedVehicle = new AbandonedVehicle(creationDate,
//                        status,
//                        completionDate,
//                        serviceRequestNumber,
//                        serviceRequestType,
//                        streetAddress,
//                        zipCode,
//                        xCoordinate,
//                        yCoordinate,
//                        ward,
//                        policeDistrict,
//                        communityArea,
//                        latitude,
//                        longitude,
//                        location,
//                        currentActivity,
//                        mostRecentAction,
//                        SSA,
//                        licensePlate,
//                        vehicleModel,
//                        vehicleColor,
//                        daysVehicleReportedAsParked
//                );
//
//                System.out.println(creationDate);
//                System.out.println(completionDate);
//                System.out.println(serviceRequestNumber);
//                System.out.println(serviceRequestType);
//                System.out.println(streetAddress);
//                System.out.println(xCoordinate);
//                System.out.println(yCoordinate);
//                System.out.println(ward);
//                System.out.println(policeDistrict);
//                System.out.println(communityArea);
//                System.out.println(latitude);
//                System.out.println(longitude);
//                System.out.println(location);
//                System.out.println(currentActivity);
//                System.out.println(mostRecentAction);
//                System.out.println(SSA);
//                System.out.println(licensePlate);
//                System.out.println(vehicleModel);
//                System.out.println(vehicleColor);
//                System.out.println(daysVehicleReportedAsParked);
//                if (!status.equals("Completed - Dup")) {
//                    abandonedVehicleRepository.save(abandonedVehicle);
//                }
//
//            }
//        }
//    }
//}
