package tb.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tb.spring.dao.DAOEmployee;
import tb.spring.dao.EmployeeDao;
import tb.spring.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping({"/employees"}) //, method = RequestMethod.GET
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }
}
