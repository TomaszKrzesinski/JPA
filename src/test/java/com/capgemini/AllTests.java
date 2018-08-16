package com.capgemini;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.service.ServiceTestSuite;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Suite.class)
@SuiteClasses({ServiceTestSuite.class})
@ActiveProfiles(profiles = "hsql")
public class AllTests {

}
