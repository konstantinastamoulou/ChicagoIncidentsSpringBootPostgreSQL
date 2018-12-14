package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.GraffitiRemoval;
import gr.di.uoa.chicagoincidents.model.UserHistory;
import gr.di.uoa.chicagoincidents.repositories.GraffitiRemovalRepository;
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
@RequestMapping("/graffiti_removal")
@CrossOrigin(origins = "*")
public class GraffitiRemovalController {

    @Autowired
    private GraffitiRemovalRepository graffitiRemovalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(GraffitiRemoval graffitiRemoval,
                                         HttpServletRequest request,
                                         @RequestParam Long uid,
                                         @RequestParam String token) throws JsonProcessingException {

        if (!userRepository.findByIdAndToken(uid, token).isPresent()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        Optional.ofNullable(uid).ifPresent(id -> userHistoryRepository.save(new UserHistory(id, request.getRequestURI(), new Date())));

        graffitiRemovalRepository.save(graffitiRemoval);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(graffitiRemoval);
        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<GraffitiRemoval> graffitiRemovals;
        graffitiRemovals = graffitiRemovalRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(graffitiRemovals);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/insert_data", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertData() {
        String csvFile = "src/main/resources/311-service-requests-graffiti-removal.csv";
        GraffitiRemoval graffitiRemoval;
        CSVReader reader = null;
        int errors = 0;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            int counter = 798795;
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


                    graffitiRemoval = new GraffitiRemoval(
                      date.getTime(),   // Date creationDate
                      line[1],          // String status
                      date2.getTime(),  // Date completionDate
                      line[3],          // String serviceRequestNumber
                      line[4],          // String serviceRequestType
                      line[7],          // String streetAddress
                      (!line[8].isEmpty()) ? Integer.valueOf(line[8]) : null,   // Integer zipCode
                      (!line[9].isEmpty()) ? Double.valueOf(line[9]) : null,   // Double xCoordinate
                      (!line[10].isEmpty()) ? Double.valueOf(line[10]) : null,   // Double yCoordinate
                      (!line[11].isEmpty()) ? Integer.valueOf(line[11]) : null,   // Integer ward
                      (!line[12].isEmpty()) ? Integer.valueOf(line[12]) : null,   // Integer policeDistrict
                      (!line[13].isEmpty()) ? Integer.valueOf(line[13]) : null,   // Integer communityArea
                      (!line[15].isEmpty()) ? Double.valueOf(line[15]) : null,   // Double latitude
                      (!line[16].isEmpty()) ? Double.valueOf(line[16]) : null,   // Double longitude
                      line[14],       // String location
                      line[5],        // String surfaceType;
                      line[6],        // String graffitiLocation
                      line[14]);      // String SSA

                    String directory = "/Users/konstantinastamoulou/Documents/";
                    String filename = "gr.csv";
                    PrintWriter writer = new PrintWriter(new FileOutputStream(
                      new File(directory + File.separator + filename),
                      true /* append = true */));
                    writer.write(graffitiRemoval.toCsvLine(counter));
                    writer.close();

                    filename = "sr.csv";
                    writer = new PrintWriter(new FileOutputStream(
                      new File(directory + File.separator + filename),
                      true /* append = true */));
                    writer.write((graffitiRemoval.superToCsvLine(counter)));
                    writer.close();
                    counter++;
                }
                catch (Exception ignored) {
                    errors++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
