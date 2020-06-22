package net.suncaper.sparkairline;

import net.suncaper.sparkairline.service.ServiceImpl.FlightsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SparkAirlineApplicationTests {
    @Autowired
    private FlightsServiceImpl FlightsService;

    @Test
    void contextLoads() throws IOException {
        FlightsService.predictModelTraningYearDataTraining();
    }

}
