//package com.abc.SpringBootSecqurityEx.restController;
//
//
//import com.spring_security_app.entity.StudentModel;
//import com.spring_security_app.service.StudentService;
//import com.spring_security_app.util.ErrorResponse;
//import com.spring_security_app.util.StudentPdfGenerator;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
//public class StudentController {
//
//
//    @Autowired
//    StudentService service;
//
//
//
////this is post api start
//     @PostMapping("/add")
//    public ResponseEntity<Map<String, Object>> postStudent(@Valid @RequestBody StudentModel studentModel, ErrorResponse err) {
//         Map<String, Object> response = new HashMap<>();
//         response.put("message", err);
//         response.put("data", studentModel);
//        try {
////            return ResponseEntity.ok(response);
//            service.save(studentModel);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
////this is post api end
//
//
//
////this is get al data api start
//        @GetMapping("/getAllStudent")
//    public ResponseEntity<List<StudentModel>> getAllStudent() {
//        try {
//            List<StudentModel> studentList = new ArrayList<>();
//            service.getAllss().forEach(studentList::add);
//            if (studentList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(studentList, HttpStatus.OK);
//        } catch(Exception ex) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
////this is get al data api end
//
//
//
////this get api by id start
//    @GetMapping("/getStudentById/{id}")
//    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable Integer id) {
//        String studentId = id.toString();
//        return  service.getById(studentId);
//    }
//
//
////this get api by id end
//
//
//
//
////this is delete api by id start
//    @DeleteMapping("/deleteStudentById/{id}")
//    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Integer id) {
//        try {
//            service.deleteStudent(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
////this is delete api by id end
//
//
//
//    @GetMapping("/student/download/pdf")
//    public ResponseEntity<byte[]> downloadStudentListAsPdf() {
//        try {
//            List<StudentModel> studentList = service.getAllss();
//            byte[] pdfBytes = StudentPdfGenerator.generatePdf(studentList);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("student_list.pdf").build());
//            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
//
//
//
//
//
