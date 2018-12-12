package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.AbandonedVehicle;
import gr.di.uoa.chicagoincidents.repositories.AbandonedVehicleRepository;
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
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/abandoned_vehicle")
@CrossOrigin(origins = "*")
public class AbandonedVehicleController {

    @Autowired
    private AbandonedVehicleRepository abandonedVehicleRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${pageSize}")
    private int pageSize;

//    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> create(
//
//
//            @RequestParam("current_activity") @Valid Double xCoordinate,
//            @RequestParam("current_activity") @Valid Integer zipCode,
//            @RequestParam("current_activity") @Valid Double xCoordinate,
//            @RequestParam("current_activity") @Valid Double yCoordinate,
//            @RequestParam("current_activity") @Valid Integer communityArea,
//            @RequestParam("current_activity") @Valid Integer ward,
//            @RequestParam("current_activity") @Valid Integer policeDistrict,
//            @RequestParam("current_activity") @Valid String latitude,
//            @RequestParam("current_activity") @Valid String longitude,
//            @RequestParam("current_activity") @Valid String location,
//
//            @RequestParam("current_activity") @Valid String currentActivity,
//            @RequestParam("most_recent_action") @Valid String mostRecentAction,
//            @RequestParam("SSA") @Valid String SSA,
//            @RequestParam("license_plate") @Valid String licensePlate,
//            @RequestParam("vehicle_model") @Valid String vehicleModel,
//            @RequestParam("vehicle_color") @Valid String vehicleColor,
//            @RequestParam("days_vehicle_reported_as_parked") @Valid String daysVehicleReportedAsParked
//    ) throws JsonProcessingException {
//
//        AbandonedVehicle abandonedVehicle = new AbandonedVehicle(Date creationDate,
//                String status,
//                Date completionDate,
//                String serviceRequestNumber,
//                String serviceRequestType,
//                String streetAddress,
//                Integer zipCode,
//                Double xCoordinate,
//                Double yCoordinate,
//                Integer ward,
//                Integer policeDistrict,
//                Integer communityArea,
//                Double latitude,
//                Double longitude,
//                String location,
//                String currentActivity,
//                String mostRecentAction,
//                String SSA,
//                String licensePlate,
//                String vehicleModel,
//                String vehicleColor,
//                String daysVehicleReportedAsParked);
//
//
//
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

    @RequestMapping(value = {"/home"})
    public ModelAndView lista(HttpServletRequest request) {
        String test = "Alohaaaa my friends";
        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("test", test);
        return modelAndView;
    }


    @RequestMapping(value = {"/search"})
    public ModelAndView lista2(HttpServletRequest request) {
        String test = "Aloheirosss my friends";
        ModelAndView modelAndView = new ModelAndView("search");

        modelAndView.addObject("test", test);
        return modelAndView;
    }
    

    @RequestMapping(value = "/test", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test() throws JsonProcessingException {

        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = dateformat3.parse("17/07/2001");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date2 = dateformat3.parse("15/10/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StoredProcedureQuery query = em
                .createStoredProcedureQuery("find_total_requests_on_date_range")
                .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
                .setParameter(1, date1)
                .setParameter(2, date2);

        query.execute();
        List requestCountPerType = query.getResultList();



        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    @RequestMapping(value = "/insert_data", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertData() {
        String csvFile = "src/main/resources/311-service-requests-abandoned-vehicles.csv";
        AbandonedVehicle abandonedVehicle;
        CSVReader reader = null;
        int errors = 0;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;

            List<AbandonedVehicle> vehicles = new ArrayList<>();
            int counter = 1;
            int threshold = 5000;
            while ((line = reader.readNext()) != null) {
                try {
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
                            (!line[13].isEmpty()) ? Double.valueOf(line[13]) : null,   // Double xCoordinate
                            (!line[14].isEmpty()) ? Double.valueOf(line[14]) : null,   // Double yCoordinate
                            (!line[15].isEmpty()) ? Integer.valueOf(line[15]) : null,   // Integer ward
                            (!line[16].isEmpty()) ? Integer.valueOf(line[16]) : null,   // Integer policeDistrict
                            (!line[17].isEmpty()) ? Integer.valueOf(line[17]) : null,   // Integer communityArea
                            (!line[19].isEmpty()) ? Double.valueOf(line[19]) : null,   // Double latitude
                            (!line[20].isEmpty()) ? Double.valueOf(line[20]) : null,   // Double longitude
                            line[21],         // String location
                            line[8],          // String currentActivity
                            line[9],          // String mostRecentAction
                            line[18],         // String SSA
                            line[5],          // String licensePlate
                            line[6],          // String vehicleModel
                            line[7],          // String vehicleColor
                            line[10]);        // String daysVehicleReportedAsParked

                    String directory = "/Users/konstantinastamoulou/Documents/";
                    String filename = "av.csv";
                    PrintWriter writer = new PrintWriter(new FileOutputStream(
                            new File(directory + File.separator + filename),
                            true /* append = true */));
                    writer.write(abandonedVehicle.toCsvLine(counter));
                    writer.close();

                    filename = "sr.csv";
                    writer = new PrintWriter(new FileOutputStream(
                            new File(directory + File.separator + filename),
                            true /* append = true */));
                    writer.write((abandonedVehicle.superToCsvLine(counter)));
                    writer.close();
                    counter++;
                } catch (Exception ignored) {
                    errors++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


