package com.capgemini.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CarServiceTest.class, AgencyServiceTest.class, EmployeeServiceTest.class })
public class ServiceTestSuite {

}
