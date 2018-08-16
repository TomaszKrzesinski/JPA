package com.capgemini.service;

import com.capgemini.domain.Address;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AgencyServiceTest {
    @Autowired
    AgencyService agencyService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    TestTO testTo;

    @Test
    public void shouldAddAgencyToDatabaseAndReturnSavedObject() {
        //given
        AgencyTO agency = testTo.getAgency();
        //when
        AgencyTO savedAgency = agencyService.addAgency(agency);
        //then
        Assert.assertNotNull(savedAgency);
        Assert.assertEquals(savedAgency.getId(), (Long)1L);
    }

    @Test
    public void shouldFindAllAgenciesAddedToDatabase() {
        //given
        AgencyTO agency = testTo.getAgency();
        //when
        AgencyTO savedAgency1 = agencyService.addAgency(agency);
        AgencyTO savedAgency2 = agencyService.addAgency(agency);
        List<AgencyTO> foundAgencies = agencyService.getAgencies();
        //then
        Assert.assertNotNull(foundAgencies);
        Assert.assertEquals((Integer)foundAgencies.size(),(Integer)2);
        boolean fff = foundAgencies.contains(savedAgency1);
        boolean fff2 = foundAgencies.contains(savedAgency2);
        Assert.assertTrue(foundAgencies.contains(savedAgency1) && foundAgencies.contains(savedAgency2));
    }

    @Test
    public void shouldRemoveAgencyFromDatabase() {
        //given
        AgencyTO agency = testTo.getAgency();
        //when
        AgencyTO savedAgency = agencyService.addAgency(agency);
        Integer sizeAfterAdding = agencyService.getAgencies().size();
        agencyService.removeAgency(savedAgency.getId());
        List<AgencyTO> foundAgencies = agencyService.getAgencies();
        //then
        Assert.assertEquals((Integer)foundAgencies.size(),(Integer)(sizeAfterAdding-1));
    }

    @Test
    public void shouldUpdateAgencyDataAndReturnUpdatedDO() {
        //given
        AgencyTO agency = testTo.getAgency();
        //when
        AgencyTO savedAgency = agencyService.addAgency(agency);

        Address address = savedAgency.getAddress();
        address.setCity("Wroclaw");
        address.setPostalCode("55-555");
        savedAgency.setAddress(address);

        AgencyTO updatedAgency = agencyService.updateAgency(savedAgency);
        //then
        Assert.assertEquals(updatedAgency.getId(), updatedAgency.getId());
        Assert.assertEquals(updatedAgency.getAddress().getCity(), "Wroclaw");
        Assert.assertEquals(updatedAgency.getAddress().getPostalCode(), "55-555");
    }

    @Test
    public void shouldAssignEmployeeToAgency() {
        //given
        AgencyTO agency = testTo.getAgency();
        EmployeeTO employee = testTo.getEmployee();

        //when
        AgencyTO savedAgency = agencyService.addAgency(agency);
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);

        AgencyTO assignedAgency = agencyService.assignEmployee(savedAgency.getId(),savedEmployee.getId());
        EmployeeTO assignedEmployee = employeeService.getEmployee(savedEmployee.getId());

        Set<EmployeeTO> assignedEmployees = agencyService.findAllAgencyEmployees(assignedAgency.getId());

        //then
        Assert.assertNotNull(assignedAgency);
        Assert.assertNotNull(assignedEmployee);
        Assert.assertNotNull(assignedEmployees);

        Assert.assertEquals((Integer)1, (Integer)assignedEmployees.size());
        Assert.assertEquals((Long)1L, assignedEmployee.getAgencyID());
        Assert.assertEquals("Tomasz", assignedEmployees.iterator().next().getFirstName());
    }

    @Test
    public void shouldRemoveEmployeeFromAgency() {
        //given
        AgencyTO agency = testTo.getAgency();
        EmployeeTO employee = testTo.getEmployee();

        //when
        AgencyTO savedAgency = agencyService.addAgency(agency);
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);

        AgencyTO assignedAgency = agencyService.assignEmployee(savedAgency.getId(),savedEmployee.getId());
        EmployeeTO assignedEmployee = employeeService.getEmployee(savedEmployee.getId());

        int numberOfAssignedEmployees = agencyService.findAllAgencyEmployees(assignedAgency.getId()).size();

        agencyService.removeEmployee(savedAgency.getId(),savedEmployee.getId());

        Set<EmployeeTO> assignedEmployees = agencyService.findAllAgencyEmployees(assignedAgency.getId());
        EmployeeTO assignedEmployeeAfterRemoval = employeeService.getEmployee(savedEmployee.getId());

        //then
        Assert.assertNotNull(assignedAgency);
        Assert.assertNotNull(assignedEmployee);
        Assert.assertNotNull(assignedEmployees);

        Assert.assertEquals((Integer)(numberOfAssignedEmployees-1), (Integer)assignedEmployees.size());
        Assert.assertNull(assignedEmployeeAfterRemoval.getAgencyID());
    }

    @Test
    public void shouldFindAllCurrentEmployeesAssignedToAgency() {
        //when
        EmployeeTO employee1 = testTo.getEmployee();
        EmployeeTO employee2 = testTo.getEmployee();
        EmployeeTO employee3 = testTo.getEmployee();
        EmployeeTO employee4 = testTo.getEmployee();
        EmployeeTO employee5 = testTo.getEmployee();

        AgencyTO agency1 = testTo.getAgency();
        AgencyTO agency2 = testTo.getAgency();

        EmployeeTO savedEmployee1 = employeeService.addEmployee(employee1);
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employee2);
        EmployeeTO savedEmployee3 = employeeService.addEmployee(employee3);
        EmployeeTO savedEmployee4 = employeeService.addEmployee(employee4);
        EmployeeTO savedEmployee5 = employeeService.addEmployee(employee5);

        AgencyTO savedAgency1 = agencyService.addAgency(agency1);
        AgencyTO savedAgency2 = agencyService.addAgency(agency2);

        agencyService.assignEmployee(savedAgency1.getId(),savedEmployee1.getId());
        agencyService.assignEmployee(savedAgency2.getId(),savedEmployee2.getId());
        agencyService.assignEmployee(savedAgency1.getId(),savedEmployee3.getId());
        agencyService.assignEmployee(savedAgency2.getId(),savedEmployee4.getId());
        agencyService.assignEmployee(savedAgency1.getId(),savedEmployee5.getId());

        Set<EmployeeTO> assignedEmployees1 = agencyService.findAllAgencyEmployees(savedAgency1.getId());
        Set<EmployeeTO> assignedEmployees2 = agencyService.findAllAgencyEmployees(savedAgency2.getId());

        Assert.assertEquals((Integer)3, (Integer)assignedEmployees1.size());
        Assert.assertEquals((Integer)2, (Integer)assignedEmployees2.size());
        Assert.assertTrue(assignedEmployees1.contains(savedEmployee1));
        Assert.assertTrue(assignedEmployees1.contains(savedEmployee3));
        Assert.assertTrue(assignedEmployees1.contains(savedEmployee5));
        Assert.assertTrue(assignedEmployees2.contains(savedEmployee2));
        Assert.assertTrue(assignedEmployees2.contains(savedEmployee4));
    }



}
