package tb.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tb.spring.dao.DAOEmployee;
import tb.spring.dao.EmployeeDao;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public List<DAOEmployee> getEmployees() { // returns List<EmployeeDTO>
        List<DAOEmployee> employeeDTOList = new LinkedList<>();
        employeeDao.findAll().forEach(employeeDTOList::add);
        return employeeDTOList;
    }
}
