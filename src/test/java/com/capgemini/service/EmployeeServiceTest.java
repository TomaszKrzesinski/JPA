package com.capgemini.service;

import com.capgemini.domain.Address;
import com.capgemini.types.CarTO;
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
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    public void shouldAddEmployeeAndReturnWithID() {
        //given
        EmployeeTO employee = getEmployee();
        //when
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);
        //then
        Assert.assertEquals(savedEmployee.getId(), (Long)1L);
        Assert.assertNotNull(savedEmployee);
    }

    @Test
    public void shouldFindEmployeeAddedToDatabase() {
        //given
        EmployeeTO employee = getEmployee();
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
        EmployeeTO employee = getEmployee();
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
        EmployeeTO employee = getEmployee();
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
        EmployeeTO employee = getEmployee();
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

    private EmployeeTO getEmployee() {
        return EmployeeTO.builder()
                .firstName("Tomasz")
                .lastName("Krzesinski")
                .address(Address.builder()
                        .street("Wroclawska 23/1")
                        .city("Mrowino")
                        .postalCode("62-070")
                        .country("Polska")
                        .contactNumber(555666222L)
                        .build())
                .birthDate(Date.valueOf("1986-03-05"))
                .build();
    }

}
