package com.management.schoolmanagementv3.Repository;

import com.management.schoolmanagementv3.Entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
}
