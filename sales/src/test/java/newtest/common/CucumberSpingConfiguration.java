package newtest.common;

import io.cucumber.spring.CucumberContextConfiguration;
import newtest.SalesApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { SalesApplication.class })
public class CucumberSpingConfiguration {}
