/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Training;
import com.mii.cvlibrary.models.TrainingType;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.TrainingService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author habib
 */
@RestController
@ControllerAdvice
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class TrainingController implements IController<Training, Integer> {

    @Autowired
    private TrainingService ts;

    @GetMapping("training")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseList<Training> getAll() {
        return new ResponseList(ts.getAll());
    }

    @GetMapping("training/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseRest<Training> getById(Integer id) {
        return ResponseRest.success(ts.getById(id));
    }

    @PostMapping("training")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<Training> insert(Training data) {
        try {
            return ResponseRest.success(ts.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("trainings")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    public ResponseRest<Training> insert2(
            @RequestParam String name,
            @RequestParam String institution,
            @RequestParam String year,
            @RequestParam Integer employee,
            @RequestParam Integer trainingType,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Training training = new Training();
            training.setName(name);
            training.setInstitution(institution);
            training.setYear(new SimpleDateFormat("yyyy-MM-dd").parse(year));
            Employee e = new Employee();
            e.setId(employee);
            training.setEmployee(e);
            TrainingType type = new TrainingType();
            type.setId(trainingType);
            training.setTrainingType(type);

            if (!file.isEmpty()) {
                String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\training\\employee-" + employee;
                File checkDir = new File(dir);
                if (!checkDir.exists()) {
                    checkDir.mkdirs();
                }
                String documentDir = dir + "\\" + file.getOriginalFilename();
                File saveFile = new File(documentDir);
                saveFile.createNewFile();
                try (FileOutputStream fout = new FileOutputStream(saveFile)) {
                    fout.write(file.getBytes());
                }
                training.setFile(file.getOriginalFilename());
            } else {
                System.out.println("kosong");
            }

            return ResponseRest.success(ts.insert(training), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("trainings/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public ResponseRest<Training> update2(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String institution,
            @RequestParam String year,
            @RequestParam Integer employee,
            @RequestParam Integer trainingType,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Training training = ts.getById(id);
            training.setName(name);
            training.setInstitution(institution);
            training.setYear(new SimpleDateFormat("yyyy-MM-dd").parse(year));
            Employee e = new Employee();
            e.setId(employee);
            training.setEmployee(e);
            System.out.println(employee);
            TrainingType type = new TrainingType();
            type.setId(trainingType);
            training.setTrainingType(type);

            if (!file.isEmpty() || !file.getOriginalFilename().equals(training.getFile())) {
                String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\training\\employee-" + employee;
                File checkDir = new File(dir);
                if (!checkDir.exists()) {
                    checkDir.mkdirs();
                }
                String documentDir = dir + "\\" + file.getOriginalFilename();
                File oldFile = new File(dir + "\\" + training.getFile());
                File newFile = new File(documentDir);

                oldFile.delete();

                newFile.createNewFile();
                FileOutputStream fout = new FileOutputStream(newFile);
                fout.write(file.getBytes());
                fout.close();
                training.setFile(file.getOriginalFilename());
            } else {
                System.out.println("kosong");
            }
            return ResponseRest.success(ts.update(id, training), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("training/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<Training> update(Integer id, Training data) {
        try {
            return ResponseRest.success(ts.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("training/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<Training> delete(Integer id) {
        Training training = ts.getById(id);
        if(!training.getFile().isEmpty()){
            String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\training\\employee-" + training.getEmployee().getId();
            File oldFile = new File(dir + "\\" + training.getFile());
            oldFile.delete();
        }
        if (ts.delete(id)) {
            return ResponseRest.success("Success");
        } else {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("training/{id}/employee")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER','READ_RM')")
    public ResponseList<Training> getByEmployee(@PathVariable Integer id) {
        return new ResponseList(ts.getByEmployee(id));
    }

    @PostMapping("training/{id}/download")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Integer id) throws IOException {
        try {
            Training training = ts.getById(id);
            if (training.getFile() != null) {
                String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\training\\employee-" + training.getEmployee().getId() + "\\" + training.getFile();
                File file = new File(dir);
                byte[] bytes = Files.readAllBytes(file.toPath());
                ByteArrayResource resource = new ByteArrayResource(bytes);
                return new ResponseEntity<>(resource, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
}
