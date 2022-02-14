package it.polimi.db2_spring.servlet;


import it.polimi.db2_spring.beans.UserService;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

   @PostMapping("/sign_up")
   public ResponseEntity<Response> signUpUser(@RequestBody @Valid Users user) {

      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("user", userService.create(user)))
                      .message("user created")
                      .status(CREATED)
                      .statusCode(CREATED.value())
                      .build()
      );
   }


   @PostMapping("/log_in")
   public ResponseEntity<Response> logInUser(@RequestBody Users user) {

      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("user", userService.checkCredentials(user)))
                      .message("user created")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   //for testing
   @GetMapping("/list")
   public ResponseEntity<Response> getPackages() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("users:", userService.getUserList(0)))
                      .message("users retrieved")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

}
