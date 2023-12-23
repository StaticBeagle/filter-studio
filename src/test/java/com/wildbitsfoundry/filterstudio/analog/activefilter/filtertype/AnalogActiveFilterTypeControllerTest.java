package com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.entity.AnalogActiveFilterType;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.request.AnalogActiveFilterTypeRequest;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AnalogActiveFilterTypeControllerTest {

    @Value("${spring.application.http.auth-token-header-name}")
    private String authTokenHeaderName;

    @Value("${spring.application.http.auth-token}")
    private String authToken;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllFilterTypes() throws Exception {
        mockMvc.perform(
                get(AnalogActiveFilterTypeController.PATH)
                        .header(authTokenHeaderName, authToken)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0]").value("High Pass"))
                .andExpect(jsonPath("$.[1]").value("Low Pass"));
    }

    @Test
    public void testCreateFilterType() throws Exception {
        AnalogActiveFilterTypeRequest analogActiveFilterTypeRequest = new AnalogActiveFilterTypeRequest();
        analogActiveFilterTypeRequest.setFilterType("High Pass");

        mockMvc.perform(
                        post(AnalogActiveFilterTypeController.PATH)
                                .header(authTokenHeaderName, authToken)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(analogActiveFilterTypeRequest))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));
    }
}
