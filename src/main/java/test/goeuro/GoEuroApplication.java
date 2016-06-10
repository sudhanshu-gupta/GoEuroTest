package test.goeuro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import test.goeuro.service.Service;

/**
 * Created by sudhanshu.gupta on 08/06/16.
 */

/**
 * <p>Main class to run the application. This class is annotated with SpringBootApplication, i.e.,
 * this is an springbootapplication which implements commandlinerunner interface</p>
 */
@SpringBootApplication
public class GoEuroApplication implements CommandLineRunner {

    @Autowired
    private Service service;

    private static final Logger logger = LoggerFactory.getLogger(GoEuroApplication.class.getName());

    public static void main(String[] args) {

        SpringApplication.run(GoEuroApplication.class, args);
    }

    /**
     * <p>This is the implemented method which get called by springbootapplication</p>
     * @param strings Accepts all the parameter which is passed as the command line arguments
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        service.setArgs(strings);
        service.execute();
    }
}
