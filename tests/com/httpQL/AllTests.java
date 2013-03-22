package com.httpQL;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestQueryProcessor.class, TestQueryDB.class })
public class AllTests {

}
