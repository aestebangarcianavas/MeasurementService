package com.measurement;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Controller to manage the operations related to the sensors.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class MeasurementCollectorApplicationTest {

    @Test
    public void contextLoads() { }
}
