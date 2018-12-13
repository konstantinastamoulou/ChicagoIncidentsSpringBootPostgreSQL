package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingBaited;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingWithGarbage;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingWithRats;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    // Store Function no1
    @RequestMapping(value = "/test1", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test1() throws JsonProcessingException {

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

    // Store Function no2
    @RequestMapping(value = "/test2", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test2() throws JsonProcessingException {

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

    // Store Function no3
    @RequestMapping(value = "/test3", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test3() throws JsonProcessingException {

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

    // Store Function no4
    @RequestMapping(value = "/test4", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test4() throws JsonProcessingException {

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

    // Store Function no5
    @RequestMapping(value = "/test5", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test5() throws JsonProcessingException {

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

    // Store Function no7
    @RequestMapping(value = "/test7", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test7() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_second_most_common_color_in_abandoned_vehicles");

        query.execute();

        List<String> rodentBaitingBaited = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    // Store Function no8
    @RequestMapping(value = "/test8", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test8() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_second_most_common_color_in_abandoned_vehicles");

        query.execute();

        List<String> rodentBaitingBaited = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    // Store Function no9
    @RequestMapping(value = "/test9", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test9() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_baited_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, 6);
        query.execute();

        List<RodentBaitingBaited> rodentBaitingBaited = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    // Store Function no10
    @RequestMapping(value = "/test10", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test10() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_with_garbage_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, 5);
        query.execute();
        List<RodentBaitingWithGarbage> rodentBaitingWithGarbages = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    // Store Function no11
    @RequestMapping(value = "/test11", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test11() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_with_rats_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, 4);
        query.execute();

        List<RodentBaitingWithRats> rodentBaitingWithRats = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : " + rodentBaitingWithRats.get(0).getRequestNumber());
    }

}
