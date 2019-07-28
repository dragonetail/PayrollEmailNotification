package pl.dmichalski.reservations.business.ui.payrolls.service;

import org.springframework.stereotype.Service;
import pl.dmichalski.reservations.business.ui.payrolls.bean.Payroll;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollService {
    private List<Payroll> payrolls =  new ArrayList<>();

    @PostConstruct
    public void init(){
        for(int i = 0; i< 10; i++){
            Payroll payroll= new Payroll();
            payroll.setId(i);
            payroll.setAddress("PR-XA-" + i);
            payroll.setName("N" + i);
            payroll.setSurname("S" + i);
            payroll.setPesel("pesel" + i);
            payroll.setPhoneNumber("1303338888" + i);
            payroll.setEmail("e" + i + "@test.com");

            payrolls.add(payroll);
        }
    }

    public List<Payroll> findAll() {
        return payrolls;
    }

}
