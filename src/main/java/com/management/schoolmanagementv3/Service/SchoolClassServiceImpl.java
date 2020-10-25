package com.management.schoolmanagementv3.Service;

import com.management.schoolmanagementv3.Entity.SchoolClass;
import com.management.schoolmanagementv3.Repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassServiceImpl implements SchoolClassService{

    @Autowired
    private SchoolClassRepository schoolClassRepository;


    @Override
    public List<SchoolClass> findAll() {

        return schoolClassRepository.findAll();
    }

    @Override
    public Optional<SchoolClass> findById(Integer id) {

        return schoolClassRepository.findById(id);
    }

    @Override
    public SchoolClass save(SchoolClass schoolClass) {

        return schoolClassRepository.save(schoolClass);
    }

    @Override
    public void delete(SchoolClass schoolClass) {

        schoolClassRepository.delete(schoolClass);
    }
}
