package newtest.common;

import io.cucumber.spring.CucumberContextConfiguration;
import newtest.BasicApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { BasicApplication.class })
public class CucumberSpingConfiguration {}
