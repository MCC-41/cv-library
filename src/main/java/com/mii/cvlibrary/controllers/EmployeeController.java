/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Religion;
import com.mii.cvlibrary.models.data.MemoRequest;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.EmployeeService;
import com.mii.cvlibrary.services.NotificationService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author habib
 */
@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RM')")
public class EmployeeController implements IController<Employee, Integer> {

    @Autowired
    EmployeeService service;
    @Autowired
    private NotificationService ns;

    @GetMapping("employee")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER','READ_RM')")
    @Override
    public ResponseList<Employee> getAll() {
        return new ResponseList(service.getAll());
    }

    @GetMapping("employee/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER','READ_RM')")
    @Override
    public ResponseRest<Employee> getById(Integer id) {
        return ResponseRest.success(service.getById(id));
    }

    @PostMapping("employee")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<Employee> insert(Employee data) {
        try {
            return ResponseRest.success(service.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("employee/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<Employee> update(Integer id, Employee data) {
        try {
            return ResponseRest.success(service.update(id, data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("employee/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<Employee> delete(Integer id) {
        Employee employee = service.getById(id);
        if (employee.getPhoto()!=null) {
            String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\employee-photo";
            File oldFile = new File(dir + "\\" + employee.getPhoto());
            oldFile.delete();
        }
        if (service.delete(id)) {
            return ResponseRest.success("Success");
        } else {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("employee/send")
    public ResponseRest<String> sendMemo(@RequestBody MemoRequest mr) {
        try {
            ns.sendMemo(mr.getEmail(), mr.getMemo());
            return ResponseRest.success("Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("employees")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    public ResponseRest<Employee> insert2(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String dateBirth,
            @RequestParam String nation,
            @RequestParam String status,
            @RequestParam String gender,
            @RequestParam Integer religion,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setEmail(email);
            employee.setDateBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dateBirth));
            employee.setNation(nation);
            employee.setStatus(status);
            employee.setGender(gender);
            Religion r = new Religion();
            r.setId(religion);
            employee.setReligion(r);

            if (!file.isEmpty() || !file.getOriginalFilename().equals(employee.getPhoto())) {
                String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\employee-photo";
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
                employee.setPhoto(file.getOriginalFilename());
            } else {
                System.out.println("kosong");
            }
            return ResponseRest.success(service.insert(employee), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed" + e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("employees/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public ResponseRest<Employee> update2(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String dateBirth,
            @RequestParam String nation,
            @RequestParam String status,
            @RequestParam String gender,
            @RequestParam Integer religion,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Employee employee = service.getById(id);
            employee.setName(name);
            employee.setEmail(email);
            employee.setDateBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dateBirth));
            employee.setNation(nation);
            employee.setStatus(status);
            employee.setGender(gender);
            Religion r = new Religion();
            r.setId(religion);
            employee.setReligion(r);

            if (!file.isEmpty() || !file.getOriginalFilename().equals(employee.getPhoto())) {
                String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\employee-photo";
                File checkDir = new File(dir);
                if (!checkDir.exists()) {
                    checkDir.mkdirs();
                }
                String documentDir = dir + "\\" + file.getOriginalFilename();
                File oldFile = new File(dir + "\\" + employee.getPhoto());
                File newFile = new File(documentDir);

                oldFile.delete();

                newFile.createNewFile();
                FileOutputStream fout = new FileOutputStream(newFile);
                fout.write(file.getBytes());
                fout.close();
                employee.setPhoto(file.getOriginalFilename());
            } else {
                System.out.println("kosong");
            }
            return ResponseRest.success(service.update(id, employee), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed" + e, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("employee/{id}/photo")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER','READ_RM')")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Integer id) throws IOException {
        try {
            Employee employee = service.getById(id);
            if (employee.getPhoto()!= null) {
                String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\employee-photo\\" +  employee.getPhoto();
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
