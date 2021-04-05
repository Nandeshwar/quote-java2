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
import com.nks.quotejava2.models.mysql.Info;
import com.nks.quotejava2.models.sqlite3.InfoLink;
import com.nks.quotejava2.models.sqlite3.InfoSqlite;
import com.nks.quotejava2.repositories.mysql.InfoRepository;
import com.nks.quotejava2.services.DataMigrationServiceImpl;
import com.nks.quotejava2.services.InfoService;
import com.nks.quotejava2.services.StatusService;
import com.nks.quotejava2.services.sqlite3.InfoLinkSqlite3Service;
import com.nks.quotejava2.services.sqlite3.InfoSqliteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @InjectMocks
    DataMigrationServiceImpl dataMigrationService;

    @Mock
    InfoSqliteService infoSqliteService;

    List<InfoSqlite> infoSqliteList = new ArrayList<InfoSqlite>();
    InfoSqlite infoSqlite = new InfoSqlite();

    @Mock
    InfoRepository infoRepository;

    List<Info> infoList = new ArrayList<Info>();

    @Mock
    InfoService infoService;

    @Mock
    InfoLinkSqlite3Service infoLinkSqlite3Service;


    @Test
    @DisplayName("success: test status endpoint")
    public void appInfoTest() throws Exception {
        InfoLink infoLinkSqlit3 = new InfoLink();
        infoLinkSqlit3.setId(1);
        infoLinkSqlit3.setLinkId(1);
        infoLinkSqlit3.setLink("www.google.com");
        infoLinkSqlit3.setCreatedAt("2010-10-10 10:10");
        infoLinkSqlit3.setUpdatedAt("2010-10-10 10:10");

        List<InfoLink> infoLinks = new ArrayList<>();
        infoLinks.add(infoLinkSqlit3);


        infoSqlite.setTitle("abc");
        infoSqlite.setInfo("abc");
        infoSqlite.setId(1);


        infoSqlite.setCreatedAt("2010-10-10 10:10");
        infoSqlite.setUpdatedAt("2010-10-10 10:10");

        infoSqliteList.add(infoSqlite);
        Info info = new Info();
        info.setTitle("abc");
        info.setInfo("abc");

        infoList.add(info);

        when(infoRepository.saveAll(infoList)).thenReturn(infoList);
        when(infoLinkSqlite3Service.findByInfoLink(1)).thenReturn(infoLinks);
        when(infoService.findFirstInfoByTitleAndInfo("abc", "abc")).thenReturn(null);
        when(infoSqliteService.findAll()).thenReturn(infoSqliteList);
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