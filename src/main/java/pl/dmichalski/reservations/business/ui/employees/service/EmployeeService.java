package pl.dmichalski.reservations.business.ui.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmichalski.reservations.business.entity.Address;
import pl.dmichalski.reservations.business.entity.Client;
import pl.dmichalski.reservations.business.entity.domain.ClientReservationCount;
import pl.dmichalski.reservations.business.repository.ClientRepository;
import pl.dmichalski.reservations.business.ui.employees.bean.Employee;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    private List<Employee> employees =  new ArrayList<>();

    @PostConstruct
    public void init(){
        for(int i = 0; i< 10; i++){
            Employee employee = new Employee();
            employee.setId(i);
            employee.setAddress("XA-" + i);
            employee.setName("N" + i);
            employee.setSurname("S" + i);
            employee.setPesel("pesel" + i);
            employee.setPhoneNumber("1303338888" + i);
            employee.setEmail("e" + i + "@test.com");

            employees.add(employee);
        }
    }

    public List<Employee> findAll() {
        return employees;
    }

}
