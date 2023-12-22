package com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnalogActiveFilterTypeControllerTest {

    @Value("${spring.application.http.auth-token-header-name}")
    private String authTokenHeaderName;

    @Value("${spring.application.http.auth-token}")
    private String authToken;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllFilterTypes() throws Exception {
        mockMvc.perform(
                get(AnalogActiveFilterTypeController.PATH)
                        .header(authTokenHeaderName, authToken)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0]").value("Low Pass"));
    }
}
