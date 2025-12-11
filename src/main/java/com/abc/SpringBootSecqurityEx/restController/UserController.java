//package com.abc.SpringBootSecqurityEx.restController;
//
//import com.spring_security_app.dto.UserDTO;
//import com.spring_security_app.service.UserService;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import jakarta.annotation.PostConstruct;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//@RestController
////@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
//@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
//public class UserController {
//    @Autowired
//    private UserService userService;
//
////    public UserController(final UserService userService) {
////        this.userService = userService;
////    }
//
//    @GetMapping
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @GetMapping("/getAll")
//    public Page<UserDTO> getAllProducts(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size,
//            @RequestParam(value = "sortBy", defaultValue = "userName") String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
//    ) {
//        return userService.getAllUsers(page, size, sortBy, sortDir);
//    }
//
////    @GetMapping("/search")
////    public Page<UserDTO> searchUsers(
////            @RequestParam(value = "searchTerm", required = false) String searchTerm,
////            @RequestParam(value = "page", defaultValue = "0") int page,
////            @RequestParam(value = "size", defaultValue = "10") int size,
////            @RequestParam(value = "sortBy", defaultValue = "userName") String sortBy,
////            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
////    ) {
////        // Use the service to search users and return a paginated result
////        return userService.searchUsers(searchTerm, page, size, sortBy, sortDir);
////    }
//
//    @GetMapping("/search")
//    public Page<UserDTO> searchUsersByDate(
//            @RequestParam(value = "searchTerm", required = false) String searchTerm,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size,
//            @RequestParam(value = "sortBy", defaultValue = "userName") String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
//            @RequestParam(value = "dateFrom", defaultValue = "0") LocalDateTime dateFrom,
//            @RequestParam(value = "dateTo", defaultValue = "0") LocalDateTime DateTo
//
//            ) {
//        // Use the service to search users and return a paginated result
//        return userService.searchUsers(searchTerm, page, size, sortBy, sortDir);
//    }
//
//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }
//
//    @GetMapping("/{userName}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "userName") final String userName) {
//        return ResponseEntity.ok(userService.get(userName));
//    }
//
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    public ResponseEntity<Void> createUser(@RequestBody @Valid final UserDTO userDTO,
//                                           final BindingResult bindingResult) throws MethodArgumentNotValidException {
////        if (!bindingResult.hasFieldErrors("userName") && userDTO.getUserName() == null) {
////            bindingResult.rejectValue("userName", "NotNull");
////        }
////        if (!bindingResult.hasFieldErrors("userName") && userService.userNameExists(userDTO.getUserName())) {
////            bindingResult.rejectValue("userName", "Exists.user.userName");
////        }
////        if (bindingResult.hasErrors()) {
////            throw new MethodArgumentNotValidException(new MethodParameter(
////                    this.getClass().getDeclaredMethods()[0], -1), bindingResult);
////        }
//        userService.create(userDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{userName}")
//    public ResponseEntity<Void> updateUser(@PathVariable(name = "userName") final String userName,
//                                           @RequestBody @Valid final UserDTO userDTO) {
//        userService.update(userName, userDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{userName}")
//    @ApiResponse(responseCode = "204")
//    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userName") final String userName) {
//        userService.delete(userName);
//        return ResponseEntity.noContent().build();
//    }
//
//}
