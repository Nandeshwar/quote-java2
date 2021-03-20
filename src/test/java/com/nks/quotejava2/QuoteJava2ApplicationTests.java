package com.nks.quotejava2;

import com.nks.quotejava2.controllers.StatusController;
import com.nks.quotejava2.services.DataMigrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class QuoteJava2ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    StatusController statusController;

    @MockBean
    DataMigrationService dataMigrationService;


    @Test
    void contextLoads() throws Exception {
        when(dataMigrationService.migrateDataFromSqlite3ToMySql()).thenReturn(true);
        assertThat(statusController).isNotNull();
    }

}
