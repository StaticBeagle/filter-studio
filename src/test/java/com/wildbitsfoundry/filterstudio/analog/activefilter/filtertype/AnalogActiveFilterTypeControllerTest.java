package com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.request.AnalogActiveFilterTypeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AnalogActiveFilterTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllFilterTypes() throws Exception {
        mockMvc.perform(
                get(AnalogActiveFilterTypeController.PATH)
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
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(analogActiveFilterTypeRequest))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));
    }
}
