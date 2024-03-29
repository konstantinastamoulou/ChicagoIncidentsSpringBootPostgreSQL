package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.PotHole;
import gr.di.uoa.chicagoincidents.model.UserHistory;
import gr.di.uoa.chicagoincidents.repositories.PotHoleRepository;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/pot_hole")
@CrossOrigin(origins = "*")
public class PotHoleController {

    @Autowired
    private PotHoleRepository potHoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(PotHole potHole,
                                         HttpServletRequest request,
                                         @RequestParam Long uid,
                                         @RequestParam String token) throws JsonProcessingException {

        if (!userRepository.findByIdAndToken(uid, token).isPresent()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        Optional.ofNullable(uid).ifPresent(id -> userHistoryRepository.save(new UserHistory(id, request.getRequestURI(), new Date())));

        potHoleRepository.save(potHole);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(potHole);
        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<PotHole> potHoles;
        potHoles = potHoleRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(potHoles);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/insert_data", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertData() {
        String csvFile = "src/main/resources/311-service-requests-pot-holes-reported.csv";
        PotHole potHole;
        CSVReader reader = null;
        int errors = 0;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            int counter = 1831256;
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


                potHole = new PotHole(
                        date.getTime(),   // Date creationDate
                        line[1],          // String status
                        date2.getTime(),  // Date completionDate
                        line[3],          // String serviceRequestNumber
                        line[4],          // String serviceRequestType
                        line[8],         // String streetAddress
                        (!line[9].isEmpty()) ? Integer.valueOf(line[9]) : null,   // Integer zipCode
                        (!line[10].isEmpty()) ? Double.valueOf(line[10]) : null,   // Double xCoordinate
                        (!line[11].isEmpty()) ? Double.valueOf(line[11]) : null,   // Double yCoordinate
                        (!line[12].isEmpty()) ? Integer.valueOf(line[12]) : null,   // Integer ward
                        (!line[13].isEmpty()) ? Integer.valueOf(line[13]) : null,   // Integer policeDistrict
                        (!line[14].isEmpty()) ? Integer.valueOf(line[14]) : null,   // Integer communityArea
                        (!line[16].isEmpty()) ? Double.valueOf(line[16]) : null,   // Double latitude
                        (!line[17].isEmpty()) ? Double.valueOf(line[17]) : null,   // Double longitude
                        line[18],           // String location
                        line[5],            // String currentActivity
                        line[6],            // String mostRecentAction;
                        line[15],           // String  SSA
                        (!line[7].isEmpty()) ? Integer.valueOf(line[7]) : null);  // Integer numOfPotholes

                String directory = "/Users/konstantinastamoulou/Documents/";
                String filename = "ph.csv";
                PrintWriter writer = new PrintWriter(new FileOutputStream(
                  new File(directory + File.separator + filename),
                  true /* append = true */));
                writer.write(potHole.toCsvLine(counter));
                writer.close();

                filename = "sr.csv";
                writer = new PrintWriter(new FileOutputStream(
                  new File(directory + File.separator + filename),
                  true /* append = true */));
                writer.write((potHole.superToCsvLine(counter)));
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
