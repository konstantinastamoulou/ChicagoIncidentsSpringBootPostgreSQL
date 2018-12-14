package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.di.uoa.chicagoincidents.model.datatypes.AvgCompletionPerType;
import gr.di.uoa.chicagoincidents.model.datatypes.LicensePlateMoreThanOnce;
import gr.di.uoa.chicagoincidents.model.datatypes.RequestCountPerDayForDateRange;
import gr.di.uoa.chicagoincidents.model.datatypes.RequestCountPerType;
import gr.di.uoa.chicagoincidents.model.datatypes.RequestTypeZipCode;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingBaited;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingWithGarbage;
import gr.di.uoa.chicagoincidents.model.datatypes.RodentBaitingWithRats;
import gr.di.uoa.chicagoincidents.model.datatypes.SsaCount;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/service_requests")
@CrossOrigin(origins = "*")
public class ServiceRequestController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/sp1/find_total_requests_on_date_range", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp1(@RequestParam("start_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date start_date,
                                      @RequestParam("end_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date ) throws JsonProcessingException {

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
    public ResponseEntity<String> sp2(@RequestParam("service_request_type") String service_request_type,
                                        @RequestParam("start_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date start_date,
                                      @RequestParam("end_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date ) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_total_requests_for_specific_type_per_day_for_date_range")
          .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(3, Date.class, ParameterMode.IN)
          .setParameter(1, service_request_type)
          .setParameter(2, start_date)
          .setParameter(3, end_date);
        query.execute();

        List<RequestCountPerDayForDateRange> requestCountPerDayForDateRanges = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(requestCountPerDayForDateRanges);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp3/find_most_common_request_per_zipcode_for_a_day", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp3(@RequestParam("input_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date input_date) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_most_common_request_per_zipcode_for_a_day")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .setParameter(1, input_date);
        query.execute();

        List<RequestTypeZipCode> requestTypeZipCode = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(requestTypeZipCode);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp4/avg_completion_per_type_for_date_range", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp4(@RequestParam("start_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date start_date,
                                      @RequestParam("end_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("avg_completion_per_type_for_date_range")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .setParameter(1, start_date)
          .setParameter(2, end_date);
        query.execute();

        query.getResultList();

        List<AvgCompletionPerType> avgCompletionPerTypeList = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(avgCompletionPerTypeList);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp5/find_most_common_request_on_bounding_box", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp5(@RequestParam("a1") Double a1,
                                      @RequestParam("a2") Double a2,
                                      @RequestParam("b1") Double b1,
                                      @RequestParam("b2") Double b2,
                                      @RequestParam("c1") Double c1,
                                      @RequestParam("c2") Double c2,
                                      @RequestParam("d1") Double d1,
                                      @RequestParam("d2") Double d2) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_most_common_request_on_bounding_box")
          .registerStoredProcedureParameter(1, Double.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Double.class, ParameterMode.IN)
          .registerStoredProcedureParameter(3, Double.class, ParameterMode.IN)
          .registerStoredProcedureParameter(4, Double.class, ParameterMode.IN)
          .registerStoredProcedureParameter(5, Double.class, ParameterMode.IN)
          .registerStoredProcedureParameter(6, Double.class, ParameterMode.IN)
          .registerStoredProcedureParameter(7, Double.class, ParameterMode.IN)
          .registerStoredProcedureParameter(8, Double.class, ParameterMode.IN)
          .setParameter(1, a1)
          .setParameter(2, a2)
          .setParameter(3, b1)
          .setParameter(4, b2)
          .setParameter(5, c1)
          .setParameter(6, c2)
          .setParameter(7, d1)
          .setParameter(8, d2);
        query.execute();

        List<String> common_request_numbers = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(common_request_numbers);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp6/find_top5_SSA_on_date_range", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp6(@RequestParam("start_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date start_date,
                                      @RequestParam("end_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date end_date) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_top5_SSA_on_date_range")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
          .setParameter(1, start_date)
          .setParameter(2, end_date);;
        query.execute();

        List<SsaCount> ssaCount = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(ssaCount);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp7/find_license_plates_in_abandoned_vehicles_more_than_once", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp7() throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_license_plates_in_abandoned_vehicles_more_than_once");
        query.execute();

        List<LicensePlateMoreThanOnce> licensePlateMoreThanOnce = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(licensePlateMoreThanOnce);

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

        List<RodentBaitingBaited> rodentBaitingBaited = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rodentBaitingBaited);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp10/find_rodent_baitings_number_of_premises_with_garbage_less_than", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp10(@RequestParam("max_number") Integer max_number) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_with_garbage_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, max_number);
        query.execute();

        List<RodentBaitingWithGarbage> rodentBaitingWithGarbages = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rodentBaitingWithGarbages);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp11/find_rodent_baitings_number_of_premises_with_rats_less_than", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp11(@RequestParam("max_number") Integer max_number) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_rodent_baitings_number_of_premises_with_rats_less_than")
          .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
          .setParameter(1, max_number);
        query.execute();

        List<RodentBaitingWithRats> rodentBaitingWithRats = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rodentBaitingWithRats);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/sp12/find_police_districts_pot_holes_and_rodent_baiting", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sp12(@RequestParam("input_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date input_date) throws JsonProcessingException {

        StoredProcedureQuery query = em
          .createStoredProcedureQuery("find_police_districts_pot_holes_and_rodent_baiting")
          .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
          .setParameter(1, input_date);
        query.execute();

        List<Integer> police_districts = query.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(police_districts);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

}
