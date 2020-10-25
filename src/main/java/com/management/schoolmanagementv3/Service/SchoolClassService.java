package com.management.schoolmanagementv3.Service;

import com.management.schoolmanagementv3.Entity.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SchoolClassService {

    List<SchoolClass> findAll();

    Optional<SchoolClass> findById(Integer id);

    SchoolClass save(SchoolClass schoolClass);

    void delete(SchoolClass schoolClass);
}
