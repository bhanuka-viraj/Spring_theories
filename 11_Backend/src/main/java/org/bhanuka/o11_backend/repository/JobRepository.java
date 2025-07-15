package org.bhanuka.o11_backend.repository;

import jakarta.transaction.Transactional;
import org.bhanuka.o11_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query(value = "UPDATE job SET status = 'closed' WHERE id = ?", nativeQuery = true)
    @Modifying
    @Transactional
    void updateJobStatus(String id);

    @Query(value = "SELECT * FROM job WHERE company = ?", nativeQuery = true)
    List<Job> findJobsByCompanyContainingIgnoreCase(String keyword);

    //JPA Methods
    // Prefix + FieldName + Operator + Conjunctions

    //*Prefix
        //findBy - readBy - getBy - countBy - existBy -deleteBy

    //*FieldName
        //attribute name of entity

    //*Operator
        //Equals - IsNull - IsNotNull - GreaterThan - LessThan - Like - NotLike - StartsWith - EndsWith - Between - In - NotIn - Not - NotEqual - NotBetween - NotLike - NotStartsWith - NotEndsWith
        //Containing - In - NotIn - NotContaining - NotContainingIgnoreCase - NotContainingAny - NotContainingAll - NotContainingIgnoreCaseAny - NotContainingIgnoreCaseAll - NotContainingAny - NotContainingAll etc..

    //*Conjunctions
        //And - Or
}
