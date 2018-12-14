package gr.di.uoa.chicagoincidents.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.di.uoa.chicagoincidents.model.User;
import gr.di.uoa.chicagoincidents.repositories.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @ApiOperation(value = "user creation", nickname = "createUser")
    @ApiImplicitParams({
      @ApiImplicitParam(
        name = "username",
        value = "user's username",
        required = true,
        dataType = "string",
        paramType = "query",
        defaultValue = "kstam"),
      @ApiImplicitParam(
        name = "firstName",
        value = "user's firstname",
        required = true,
        dataType = "string",
        paramType = "query",
        defaultValue = "Konstantina"),
      @ApiImplicitParam(
        name = "lastName",
        value = "user's lastname",
        required = true,
        dataType = "string",
        paramType = "query",
        defaultValue = "Stamoulou"),
      @ApiImplicitParam(
        name = "password",
        value = "user's password",
        required = true,
        dataType = "string",
        paramType = "query",
        defaultValue = "123456"),
      @ApiImplicitParam(
        name = "email",
        value = "user's email",
        required = true,
        dataType = "string",
        paramType = "query",
        defaultValue = "test@test.com")
    })
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> create(User user) throws Exception{

        String content = user.getPassword();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(content.getBytes());
        byte[] digest = md.digest();
        String hashed = DatatypeConverter.printHexBinary(digest);

        user.setPassword(hashed);

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        user.setToken(token);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));

    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws Exception{

        String content = password;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(content.getBytes());
        byte[] digest = md.digest();
        String hashed = DatatypeConverter.printHexBinary(digest);

        Optional<User> user = userRepository.findByUsernameAndPassword(username, hashed);

        return user.isPresent()
          ? ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user)) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");

    }

}
