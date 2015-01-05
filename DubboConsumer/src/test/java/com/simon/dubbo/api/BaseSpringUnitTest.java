package com.simon.dubbo.api;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:spring/dubbo-interfaces.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseSpringUnitTest {

}
