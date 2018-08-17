package com.capgemini.service;

import com.capgemini.dao.RankDao;
import com.capgemini.domain.Address;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RankEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeSearchCriteria;
import com.capgemini.types.EmployeeTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
//@SpringBootTest(properties="spring.profiles.active=hsql")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    CarService carService;

    @Autowired
    AgencyService agencyService;

    @Autowired
    RankDao rankDao;

    @Autowired
    TestTO testTo;





    @Test
    public void shouldAddEmployeeAndReturnWithID() {
        //given
        EmployeeTO employee = testTo.getEmployee();
        //when
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);
        //then
        Assert.assertEquals(savedEmployee.getId(), (Long)1L);
        Assert.assertNotNull(savedEmployee);
    }

    @Test
    public void shouldFindEmployeeAddedToDatabase() {
        //given
        EmployeeTO employee = testTo.getEmployee();
        //when
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);
        EmployeeTO foundEmployee = employeeService.getEmployee(savedEmployee.getId());
        //then
        Assert.assertNotNull(foundEmployee);
        Assert.assertEquals(savedEmployee.getId(), savedEmployee.getId());
    }

    @Test
    public void shouldFindAllEmployeesAddedToDatabase() {
        //given
        EmployeeTO employee = testTo.getEmployee();
        //when
        EmployeeTO savedEmployee1 = employeeService.addEmployee(employee);
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employee);
        List<EmployeeTO> foundEmployees = employeeService.getEmployees();
        //then
        Assert.assertNotNull(foundEmployees);
        Assert.assertEquals((Integer)foundEmployees.size(),(Integer)2);
        Assert.assertTrue(foundEmployees.contains(savedEmployee1) && foundEmployees.contains(savedEmployee2));
    }

    @Test
    public void shouldRemoveEmployeeFromDatabase() {
        //given
        EmployeeTO employee = testTo.getEmployee();
        //when
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);
        Integer sizeAfterAdding = employeeService.getEmployees().size();
        employeeService.removeEmployee(savedEmployee.getId());
        List<EmployeeTO> foundEmployees = employeeService.getEmployees();
        //then
        Assert.assertEquals((Integer)foundEmployees.size(),(Integer)(sizeAfterAdding-1));
    }

    @Test
    public void shouldUpdateEmployeeDataAndReturnUpdatedDO() {
        //given
        EmployeeTO employee = testTo.getEmployee();
        //when
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);

        savedEmployee.setFirstName("Roman");
        savedEmployee.setLastName("Polanski");

        EmployeeTO updatedEmployee = employeeService.updateEmployee(savedEmployee);
        //then
        Assert.assertEquals(updatedEmployee.getId(), updatedEmployee.getId());
        Assert.assertEquals(updatedEmployee.getFirstName(), "Roman");
        Assert.assertEquals(updatedEmployee.getLastName(), "Polanski");
    }

    @Test
    public void shouldFindEmployeeByDefinedSearchCriteria() {
        EmployeeTO employee1 = testTo.getEmployee();
        EmployeeTO employee2 = testTo.getEmployee();
        EmployeeTO employee3 = testTo.getEmployee();
        EmployeeTO employee4 = testTo.getEmployee();
        EmployeeTO employee5 = testTo.getEmployee();

        AgencyTO agency1 = testTo.getAgency();
        AgencyTO agency2 = testTo.getAgency();

        CarTO car1 = testTo.getCar();
        CarTO car2 = testTo.getCar();

        RankEntity rankManager = RankEntity.builder().rank("Manager").build();
        RankEntity rankSeller = RankEntity.builder().rank("Seller").build();
        RankEntity rankAccountant = RankEntity.builder().rank("Accountant").build();

        RankEntity savedManager = rankDao.save(rankManager);
        RankEntity savedSeller = rankDao.save(rankSeller);
        RankEntity savedAccountant = rankDao.save(rankAccountant);

        employee1.setRank(savedManager);
        employee2.setRank(savedSeller);
        employee3.setRank(savedSeller);
        employee4.setRank(savedSeller);
        employee5.setRank(savedAccountant);

        EmployeeTO savedEmployee1 = employeeService.addEmployee(employee1);
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employee2);
        EmployeeTO savedEmployee3 = employeeService.addEmployee(employee3);
        EmployeeTO savedEmployee4 = employeeService.addEmployee(employee4);
        EmployeeTO savedEmployee5 = employeeService.addEmployee(employee5);

        AgencyTO savedAgency1 = agencyService.addAgency(agency1);
        AgencyTO savedAgency2 = agencyService.addAgency(agency2);

        CarTO savedCar1 = carService.addCar(car1);
        CarTO savedCar2 = carService.addCar(car2);

        agencyService.assignEmployee(savedAgency1.getId(), savedEmployee1.getId());
        agencyService.assignEmployee(savedAgency1.getId(), savedEmployee2.getId());
        agencyService.assignEmployee(savedAgency1.getId(), savedEmployee3.getId());
        agencyService.assignEmployee(savedAgency2.getId(), savedEmployee4.getId());
        agencyService.assignEmployee(savedAgency2.getId(), savedEmployee5.getId());

        carService.assignKeeper(savedCar1.getId(), savedEmployee1.getId());
        carService.assignKeeper(savedCar1.getId(), savedEmployee2.getId());
        carService.assignKeeper(savedCar2.getId(), savedEmployee3.getId());
        carService.assignKeeper(savedCar2.getId(), savedEmployee4.getId());
        carService.assignKeeper(savedCar2.getId(), savedEmployee5.getId());

        EmployeeSearchCriteria employeeSearchCriteria1 = EmployeeSearchCriteria.builder()
                .agencyId(1L)
                .carUnderCare(1L)
                .rank("MANAGER")
                .build();
        List<EmployeeTO> searchResult1 = employeeService.searchEmployee(employeeSearchCriteria1);

        EmployeeSearchCriteria employeeSearchCriteria2 = EmployeeSearchCriteria.builder()
                .rank("SELLER")
                .build();
        List<EmployeeTO> searchResult2 = employeeService.searchEmployee(employeeSearchCriteria2);

        EmployeeSearchCriteria employeeSearchCriteria3 = EmployeeSearchCriteria.builder()
                .rank("SELLER")
                .agencyId(1L)
                .build();
        List<EmployeeTO> searchResult3 = employeeService.searchEmployee(employeeSearchCriteria3);

        EmployeeSearchCriteria employeeSearchCriteria4 = EmployeeSearchCriteria.builder()
                .agencyId(2L)
                .carUnderCare(2L)
                .build();
        List<EmployeeTO> searchResult4 = employeeService.searchEmployee(employeeSearchCriteria4);

        Assert.assertEquals((Integer)1, (Integer)searchResult1.size());
        Assert.assertEquals((Integer)3, (Integer)searchResult2.size());
        Assert.assertEquals((Integer)2, (Integer)searchResult3.size());
        Assert.assertEquals((Integer)2, (Integer)searchResult4.size());
    }
}
