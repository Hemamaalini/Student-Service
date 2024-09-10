package org.example.Repo;

import org.example.Entity.StudentEntity;
import org.example.Model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//import static org.example.Service.StudentService.findByActiveTrue;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity,Integer> {

    //@Query(value = "SELECT * FROM student_service", nativeQuery = true)
    //@Query("SELECT new org.example.Entity.StudentDTO(s.id, s.name, s.registerNumber, s.department, s.active) FROM Student s")
    //@Query(name = "StudentRepository.getAllDetails", nativeQuery = true)
   // @Query("SELECT new org.example.Model.StudentModel(s.id, s.name, s.RegisterNumber, s.department, s.active) FROM Student s")
    @Query("SELECT e FROM StudentEntity e")
    List<StudentEntity> getAllDetails();

    List<StudentEntity> findByActiveTrue();
    List<StudentEntity> findByActiveFalse();
    List<StudentEntity> findAllByOrderByIdAsc();
   // List<StudentEntity> findByName();
    boolean existsByName(String name);
    boolean existsByRegisterNumber(int registerNumber);


}
