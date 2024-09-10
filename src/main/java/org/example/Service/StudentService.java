package org.example.Service;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.Entity.StudentEntity;
import org.example.Handler.ResponseClass;
import org.example.Model.StudentModel;
import org.example.Repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class StudentService {
//    this class is used for service
    @Autowired
    public StudentRepo studentRepo;

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public ResponseEntity<Object> create(StudentModel model) {
        try{
            StudentEntity entity = new StudentEntity();
            if(model.getName().isBlank())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Name should not Empty!");

            if (studentRepo.existsByName(model.getName())&&studentRepo.existsByRegisterNumber(model.getRegisterNumber())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Name and Register Number are Already Exists");
              //  return "Name and Register Number are Already Exists";
            }
            else if (studentRepo.existsByName(model.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Name Already Exists");
             //   return "Name Already Exists";
            }
            else if (studentRepo.existsByRegisterNumber(model.getRegisterNumber())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Register Number Already Exists");
              //  return "Register Number Already Exists";
            }
            else{
                entity.setName(model.getName());
                entity.setRegisterNumber(model.getRegisterNumber());
            }
            entity.setDepartment(model.getDepartment());
            entity.setActive(model.isActive());
            entity.setCreatedTimeStamp(LocalDateTime.now());
            entity = studentRepo.save(entity);
            model.setId(entity.getId());
            model.setCreatedTimeStamp(entity.getCreatedTimeStamp());
            List<StudentModel> student =  new ArrayList<>();
            student.add(model);
            ResponseClass response = new ResponseClass(HttpStatus.OK.value(), "OK", "Data created successfully",LocalDateTime.now(), student);
            logger.info("Data Created Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            ResponseClass response = new ResponseClass(e.getStatusCode().value(), e.getReason(), e.getMessage(), LocalDateTime.now(), null);
            return new ResponseEntity<>(response, e.getStatusCode());
        }

    }

    public String updateAll( @NotNull StudentModel model){
        StudentEntity entity1 = studentRepo.findById(model.getId())
                .orElseThrow(() -> new ResourceAccessException("Student not exist with id: " + model.getId()));
            if (studentRepo.existsByName(model.getName())&&studentRepo.existsByRegisterNumber(model.getRegisterNumber())){

                return "Name and Register Number are Already Exists";
            }
            else if (studentRepo.existsByName(model.getName())) {
                return "Name Already Exists";
            }
            else if (studentRepo.existsByRegisterNumber(model.getRegisterNumber())){
                return "Register Number Already Exists";
            }
            else{
                entity1.setName(model.getName());
                entity1.setRegisterNumber(model.getRegisterNumber());
            }
            entity1.setDepartment(model.getDepartment());
            entity1.setActive(model.isActive());
            entity1.setUpdatedTimeStamp(LocalDateTime.now());
            entity1 = studentRepo.save(entity1);
            model.setId(entity1.getId());
            return "Data Updated!";
    }


    public String updateById(StudentModel model){
        StudentEntity student = studentRepo.findById(model.getId())
                .orElseThrow(() -> new ResourceAccessException("Student not exist with Id: " + model.getId()));

            if (model.getName()!=null && !model.getName().isBlank()){
                if (studentRepo.existsByName(model.getName()))
                    return "Name Already Exists";
                student.setName(model.getName());
            } else if (model.getDepartment()!=null && !model.getDepartment().isBlank()) {
                student.setDepartment(model.getDepartment());
            }
            student.setUpdatedTimeStamp(LocalDateTime.now());
            studentRepo.save(student);
            return "Data Updated!";
    }
    public String updateStatus(StudentModel model){
        StudentEntity student = studentRepo.findById(model.getId())
                .orElseThrow(() -> new ResourceAccessException("Student not exist with Id: " + model.getId()));
        student.setActive(model.isActive());
        student.setUpdatedTimeStamp(LocalDateTime.now());
        studentRepo.save(student);
        return "Status Updated!";
    }

    public ResponseEntity<?> getAllDetails() {
        try {
            List<StudentEntity> allDetails = studentRepo.getAllDetails();
            if (allDetails.isEmpty()) {
                logger.error("No Data in Table");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data in Table");
            }
            ResponseClass response = new ResponseClass(HttpStatus.OK.value(), "OK", "Data retrieved successfully",LocalDateTime.now(), allDetails);
            logger.info("All Details got Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ResponseStatusException e) {
            ResponseClass response = new ResponseClass(e.getStatusCode().value(), e.getReason(), e.getMessage(), LocalDateTime.now(), null);
            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }


    public ResponseEntity<?> getById(int id){
        StudentEntity student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID Not Found");
        }
    }

    public List<StudentEntity> getActiveStudents(Boolean status) {
        if (status == null) {
            return studentRepo.findAllByOrderByIdAsc();
        } else if (status) {
            return studentRepo.findByActiveTrue();
        } else {
            return studentRepo.findByActiveFalse();
        }
    }
    public String deleteByValue(int id){
        StudentEntity student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Student not exist with Id: " + id));
        studentRepo.deleteById(id);
        return "Deleted Successfully";
    }
    public String deleteAll(){
        studentRepo.deleteAll();
        return "Truncate Successfully!";
    }
}
