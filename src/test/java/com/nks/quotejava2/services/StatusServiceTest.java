package com.nks.quotejava2.services;

import com.nks.quotejava2.models.Status;
import com.nks.quotejava2.repositories.mysql.InfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

//import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class StatusServiceTest {
    @Autowired
    private StatusService statusService;

    @MockBean
    private InfoRepository infoRepository;

    @Test
    @DisplayName("sucess: getting app info")
    void testAppInfo() {

        String expectedAppName = "quote-java2";
        Status status = statusService.appInfo();

        // Example using mock
        //doReturn(status).when(infoRepository).findFirstInfoByTitleAndInfo("abc", "abc");


        Assertions.assertEquals(expectedAppName, status.getAppName());
        Assertions.assertTrue(status.getUpTime().getUnit().contains("ms"));
    }
}
