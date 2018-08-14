package com.capgemini.service;

import com.capgemini.domain.Address;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AgencyServiceTest {
    @Autowired
    AgencyService agencyService;

    @Test
    public void shouldAddAgencyToDatabaseAndReturnSavedObject() {
        //given
        AgencyTO agency = getAgency();
        //when
        AgencyTO savedAgency = agencyService.addAgency(agency);
        //then
        Assert.assertNotNull(savedAgency);
        Assert.assertEquals(savedAgency.getId(), (Long)1L);
    }

    @Test
    public void shouldFindAllAgenciesAddedToDatabase() {
        //given
        AgencyTO agency = getAgency();
        //when
        AgencyTO savedAgency1 = agencyService.addAgency(agency);
        AgencyTO savedAgency2 = agencyService.addAgency(agency);
        List<AgencyTO> foundAgencies = agencyService.getAgencies();
        //then
        Assert.assertNotNull(foundAgencies);
        Assert.assertEquals((Integer)foundAgencies.size(),(Integer)2);
        Assert.assertTrue(foundAgencies.contains(savedAgency1) && foundAgencies.contains(savedAgency2));
    }

    @Test
    public void shouldRemoveAgencyFromDatabase() {
        //given
        AgencyTO agency = getAgency();
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
        AgencyTO agency = getAgency();
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

    private AgencyTO getAgency() {
        return AgencyTO.builder()
                .address(Address.builder()
                        .street("ul.Sezamkowa 6")
                        .city("Poznan")
                        .country("Polska")
                        .postalCode("62-652")
                        .contactNumber(555555555L)
                        .build())
                .build();
    }


}
