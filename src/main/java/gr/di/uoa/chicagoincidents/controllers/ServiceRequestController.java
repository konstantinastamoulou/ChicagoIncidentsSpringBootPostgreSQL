package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.di.uoa.chicagoincidents.model.datatypes.LicensePlateMoreThanOnce;
import gr.di.uoa.chicagoincidents.model.datatypes.RequestCountPerType;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingWithGarbage;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingWithRats;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/service_requests")
@CrossOrigin(origins = "*")
public class ServiceRequestController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/sp1/find_total_requests_on_date_range", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp1(@RequestParam("start_date") @DateTimeFormat(pattern="MM/dd/yyyy") Date start_date,
                                      @RequestParam("end_date") @DateTimeFormat(pattern="MM/dd/yyyy") Date end_date ) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_total_requests_on_date_range")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .setParameter(1, start_date)
          .setParameter(2, end_date);
        query.execute();

        List<RequestCountPerType> requestCountPerTypes = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(requestCountPerTypes);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp2/find_total_requests_for_specific_type_per_day_for_date_range", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp2() {

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
          .createStoredProcedureQuery("find_total_requests_for_specific_type_per_day_for_date_range")
          .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(3, Date.class, ParameterMode.IN)
          .setParameter(1, "Abandoned Vehicle Complaint")
          .setParameter(2, date1)
          .setParameter(3, date2);

        query.execute();
        List RequestCountPerDayForDateRange = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    @RequestMapping(value = "/sp3/find_most_common_request_per_zipcode_for_a_day", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp3() {

        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");

        Date date1 = null;

        try {
            date1 = dateformat3.parse("17/07/2001");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_most_common_request_per_zipcode_for_a_day")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .setParameter(1, date1);
        query.execute();
        List CommonRequestPerZipcode = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    @RequestMapping(value = "/sp4/avg_completion_per_type_for_date_range", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp4() {

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
          .createStoredProcedureQuery("avg_completion_per_type_for_date_range")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .setParameter(1, date1)
          .setParameter(2, date2);
        query.execute();
        List AvgCompletionPerType = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    @RequestMapping(value = "/sp5/avg_completion_per_type_for_date_range", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp5() {

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
          .createStoredProcedureQuery("avg_completion_per_type_for_date_range")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .setParameter(1, date1)
          .setParameter(2, date2);
        query.execute();
        List AvgCompletionPerType = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    @RequestMapping(value = "/sp7/find_license_plates_in_abandoned_vehicles_more_than_once", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp7() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_license_plates_in_abandoned_vehicles_more_than_once");
        query.execute();

        List<String> rodentBaitingBaited = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rodentBaitingBaited);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp8/find_second_most_common_color_in_abandoned_vehicles", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp8() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_second_most_common_color_in_abandoned_vehicles");
        query.execute();

        List<LicensePlateMoreThanOnce> licensePlateMoreThanOnce = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(licensePlateMoreThanOnce);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp9/find_rodent_baitings_number_of_premises_baited_less_than", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp9(@RequestParam("max_number") Integer max_number) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_baited_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, max_number);
        query.execute();

        List rodentBaitingBaited = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rodentBaitingBaited);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp10/find_rodent_baitings_number_of_premises_with_garbage_less_than", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp10() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_with_garbage_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, 5);
        query.execute();

        List<RodentBaitingWithGarbage> rodentBaitingWithGarbages = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rodentBaitingWithGarbages);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp11/find_rodent_baitings_number_of_premises_with_rats_less_than", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp11() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_with_rats_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, 4);
        query.execute();

        List<RodentBaitingWithRats> rodentBaitingWithRats = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rodentBaitingWithRats);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

}
