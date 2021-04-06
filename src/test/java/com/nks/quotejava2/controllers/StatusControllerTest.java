package com.nks.quotejava2.controllers;
/*
import com.nks.quotejava2.models.Status;
import com.nks.quotejava2.models.UpTime;
import com.nks.quotejava2.services.DataMigrationService;
import com.nks.quotejava2.services.StatusService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// https://mkyong.com/spring-boot/spring-rest-integration-test-example/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @MockBean
    DataMigrationService dataMigrationService;

    @Test
    @DisplayName("success: test status endpoint")
    public void appInfoTest() throws Exception {
        when(dataMigrationService.migrateDataFromSqlite3ToMySql()).thenReturn(true);

        Status statusObj = new Status();
        statusObj.setAppName("Quote");
        statusObj.setAppVer("1.0");
        statusObj.setAppStatus("up and running");
        statusObj.setUpTime(new UpTime(1000l, "ms"));

        String expectedJSON = String.format("""
                {
                    "appName": "%s",
                    "appVer": "%s",
                    "appStatus": "%s", 
                    "upTime": {
                        "upTime": %d,
                        "unit": "%s"
                    }
                }
                """, statusObj.getAppName(), statusObj.getAppVer(), statusObj.getAppStatus(), statusObj.getUpTime().getUpTime(), statusObj.getUpTime().getUnit());

        when(statusService.appInfo()).thenReturn(statusObj);
        MvcResult mvnResult = mockMvc.perform(get("/api/v1/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appName", is(statusObj.getAppName())))
                .andReturn();

        String actualResponse = mvnResult.getResponse().getContentAsString();
        assertThat(actualResponse, containsString("Quote"));
        JSONAssert.assertEquals(expectedJSON, actualResponse, true);
    }
}
*/

import com.nks.quotejava2.models.Status;
import com.nks.quotejava2.models.UpTime;
import com.nks.quotejava2.services.StatusService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusController.class)
public class StatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @Test
    @DisplayName("success: test status endpoint")
    public void appInfoTest() throws Exception {
        Status statusObj = new Status();
        statusObj.setAppName("Quote");
        statusObj.setAppVer("1.0");
        statusObj.setAppStatus("up and running");
        statusObj.setUpTime(new UpTime(1000l, "ms"));

        String expectedJSON = String.format("""
                {
                    "appName": "%s",
                    "appVer": "%s",
                    "appStatus": "%s", 
                    "upTime": {
                        "upTime": %d,
                        "unit": "%s"
                    }
                }
                """, statusObj.getAppName(), statusObj.getAppVer(), statusObj.getAppStatus(), statusObj.getUpTime().getUpTime(), statusObj.getUpTime().getUnit());

        when(statusService.appInfo()).thenReturn(statusObj);
        MvcResult mvnResult = mockMvc.perform(get("/api/v1/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appName", is(statusObj.getAppName())))
                .andReturn();

        String actualResponse = mvnResult.getResponse().getContentAsString();
        assertThat(actualResponse, containsString("Quote"));
        JSONAssert.assertEquals(expectedJSON, actualResponse, true);
    }
}