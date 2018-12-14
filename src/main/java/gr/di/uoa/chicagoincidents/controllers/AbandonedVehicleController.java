package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.AbandonedVehicle;
import gr.di.uoa.chicagoincidents.model.UserHistory;
import gr.di.uoa.chicagoincidents.repositories.AbandonedVehicleRepository;
import gr.di.uoa.chicagoincidents.repositories.UserHistoryRepository;
import gr.di.uoa.chicagoincidents.repositories.UserRepository;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/abandoned_vehicle")
@CrossOrigin(origins = "*")
public class AbandonedVehicleController {

    @Autowired
    private AbandonedVehicleRepository abandonedVehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(AbandonedVehicle abandonedVehicle,
                                         HttpServletRequest request,
                                         @RequestParam Long uid,
                                         @RequestParam String token) throws JsonProcessingException {

        if (!userRepository.findByIdAndToken(uid, token).isPresent()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        Optional.ofNullable(uid).ifPresent(id -> userHistoryRepository.save(new UserHistory(id, request.getRequestURI(), new Date())));

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


