package com.nks.quotejava2;

import com.nks.quotejava2.controllers.StatusController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class QuoteJava2ApplicationTests {

    @Autowired
    StatusController statusController;

    @Test
    void contextLoads() {
        QuoteJava2Application.main(new String[]{});
        assertThat(statusController).isNotNull();
    }

}
