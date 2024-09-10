package org.example.Controller;
import org.example.Entity.StudentEntity;
import org.example.Model.StudentModel;
import org.example.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/student")
@Validated
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping( "/create")
    public ResponseEntity<Object> createStudent(@RequestBody StudentModel model){

        //System.out.println("controller_request"+model);

        return studentService.create(model);
    }
    @PutMapping("/updateValue")
    public String updateValue(@RequestBody StudentModel model){
        return studentService.updateById(model);
    }

    @PutMapping("/updateAll")
    public String updateAll(@RequestBody StudentModel model) {
        return studentService.updateAll(model);
    }
    @PatchMapping("/updateStatus")
    public String updateStatus(@RequestBody StudentModel model){
        return studentService.updateStatus(model);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getStudentById(@RequestParam  int id ) {
        return studentService.getById(id);

    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getDetails(){                    // student model - structure doesn't match
        return (ResponseEntity<?>) studentService.getAllDetails();
    }
    @GetMapping("/getByStatus")
    public List<StudentEntity> getByStatus(@RequestParam(required = false) Boolean status){

        return studentService.getActiveStudents(status);
    }
    @DeleteMapping("/deleteByValue/{id}")
    public String deleteByValue(@PathVariable int id){
        return studentService.deleteByValue(id);
    }

    @DeleteMapping("deleteAll")
    public String deleteAll(){
        return studentService.deleteAll();
    }

}
