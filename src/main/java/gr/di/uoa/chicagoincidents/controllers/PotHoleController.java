package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import gr.di.uoa.chicagoincidents.model.PotHole;
import gr.di.uoa.chicagoincidents.repositories.PotHoleRepository;
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
@RequestMapping("/pot_hole")
@CrossOrigin(origins = "*")
public class PotHoleController {

    @Autowired
    private PotHoleRepository potHoleRepository;

    @Value("${pageSize}")
    private int pageSize;


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
        String csvFile = "src/main/resources/311-service-requests-graffiti-removal.csv";
        PotHole potHole;
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

                potHoleRepository.save(potHole);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
