package com.nks.quotejava2;

/*
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
 */

import com.nks.quotejava2.controllers.StatusController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class QuoteJava2ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    StatusController statusController;

    @Test
    void contextLoads() throws Exception {
        assertThat(statusController).isNotNull();
    }
}
