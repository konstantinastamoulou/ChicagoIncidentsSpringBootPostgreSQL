package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.StreetLightsAllOut;
import gr.di.uoa.chicagoincidents.repositories.StreetLightsAllOutRepository;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


@RestController
@RequestMapping("/street_lights_all_out")
@CrossOrigin(origins = "*")
public class StreetLightsAllOutController {

    @Autowired
    private StreetLightsAllOutRepository streetLightsAllOutRepository;

    @Value("${pageSize}")
    private int pageSize;


    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(

    ) throws JsonProcessingException {

        StreetLightsAllOut streetLightsAllOut = new StreetLightsAllOut();

        streetLightsAllOutRepository.save(streetLightsAllOut);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(streetLightsAllOut);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
      @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<StreetLightsAllOut> streetLightsAllOut;
        streetLightsAllOut = streetLightsAllOutRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(streetLightsAllOut);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/insert_data", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertData() {
        String csvFile = "src/main/resources/311-service-requests-street-lights-all-out.csv";
        StreetLightsAllOut streetLightsAllOut;
        CSVReader reader = null;
        int errors = 0;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            int counter = 2837848;
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


                    streetLightsAllOut = new StreetLightsAllOut(
                      date.getTime(),   // Date creationDate
                      line[1],          // String status
                      date2.getTime(),  // Date completionDate
                      line[3],          // String serviceRequestNumber
                      line[4],          // String serviceRequestType
                      line[5],         // String streetAddress
                      (!line[6].isEmpty()) ? Integer.valueOf(line[6]) : null,   // Integer zipCode
                      (!line[7].isEmpty()) ? Double.valueOf(line[7]) : null,   // Double xCoordinate
                      (!line[8].isEmpty()) ? Double.valueOf(line[8]) : null,   // Double yCoordinate
                      (!line[9].isEmpty()) ? Integer.valueOf(line[9]) : null,   // Integer ward
                      (!line[10].isEmpty()) ? Integer.valueOf(line[10]) : null,   // Integer policeDistrict
                      (!line[11].isEmpty()) ? Integer.valueOf(line[11]) : null,   // Integer communityArea
                      (!line[12].isEmpty()) ? Double.valueOf(line[12]) : null,   // Double latitude
                      (!line[13].isEmpty()) ? Double.valueOf(line[13]) : null,   // Double longitude
                      line[14]         // String location
                    );
                    String directory = "/Users/konstantinastamoulou/Documents/";
                    String filename = "slao.csv";
                    PrintWriter writer = new PrintWriter(new FileOutputStream(
                      new File(directory + File.separator + filename),
                      true /* append = true */));
                    writer.write(streetLightsAllOut.toCsvLine(counter));
                    writer.close();

                    filename = "sr.csv";
                    writer = new PrintWriter(new FileOutputStream(
                      new File(directory + File.separator + filename),
                      true /* append = true */));
                    writer.write((streetLightsAllOut.superToCsvLine(counter)));
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

