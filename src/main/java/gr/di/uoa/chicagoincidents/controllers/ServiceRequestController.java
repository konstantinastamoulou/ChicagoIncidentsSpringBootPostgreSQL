package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
          .createStoredProcedureQuery("find_total_requests_on_date_range")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .setParameter(1, date1)
          .setParameter(2, date2);

        query.execute();
        List requestCountPerType = query.getResultList();

        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
    }

    // Store Function no1
//    @RequestMapping(value = "/test2", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> test2() throws JsonProcessingException {
//
//        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
//
//        Date date1 = null;
//        Date date2 = null;
//
//        try {
//            date1 = dateformat3.parse("17/07/2001");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            date2 = dateformat3.parse("15/10/2018");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        StoredProcedureQuery query = em
//          .createStoredProcedureQuery("find_total_requests_for_specific_type_per_day_for_date_range")
//          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
//          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
//          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
//          .setParameter(1, date1)
//          .setParameter(2, date2);
//
//        query.execute();
//        List requestCountPerType = query.getResultList();
//
//        return ResponseEntity.status(HttpStatus.OK).body("asdf : ");
//    }


}
