/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.scape.app.configuration.AppConfiguration;
import de.scape.app.configuration.CORSFilter;
import de.scape.app.controller.DoctorRestController;
import de.scape.app.entities.Doctor;
import de.scape.app.service.DoctorService;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class DoctorControllerUnitTest {

    private static final AtomicLong counter = new AtomicLong();

    final static Logger logger = Logger.getLogger(DoctorControllerUnitTest.class);

    private MockMvc mockMvc;

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorRestController doctorController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(doctorController)
                .addFilters(new CORSFilter())
                .build();
    }

    // =========================================== Get All Doctors ==========================================
    @Test
    public void test_get_all_success() throws Exception {
        List<Doctor> doctors = Arrays.asList(
                new Doctor(new Long(1), "Sam"),
                new Doctor(new Long(2), "Tom"));

        when(doctorService.addAllDoctors()).thenReturn(doctors);

        mockMvc.perform(get("/doctor/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Sam")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Tom")));

        verify(doctorService, times(1)).addAllDoctors();
        verifyNoMoreInteractions(doctorService);
    }

    
    // =========================================== CORS Headers ===========================================
    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/doctor"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
