package com.sport_objects.repositories;

import com.sport_objects.entities.SportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportTypeRepository extends JpaRepository<SportType, Long> {

    @Query("SELECT s FROM SportType s WHERE LOWER(s.name) LIKE %?1%")
    List<SportType> searchKeyword(String searchKeyword);

}
