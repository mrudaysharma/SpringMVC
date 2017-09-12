/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.scape.app.configuration.AppConfiguration;
import de.scape.app.configuration.CORSFilter;
import de.scape.app.controller.PatientRestController;
import de.scape.app.entities.Patient;
import de.scape.app.model.PatientModel;
import de.scape.app.service.PatientService;
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
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class PatientControllerUnitTest {

    private static final AtomicLong counter = new AtomicLong();

    final static Logger logger = Logger.getLogger(PatientControllerUnitTest.class);

    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;


    @InjectMocks
    private PatientRestController patientController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(patientController)
                .addFilters(new CORSFilter())
                .build();
    }

    // =========================================== Create New Patient ========================================
    @Test
    public void test_create_patient_success() throws Exception {
        Patient patient = new Patient(counter.incrementAndGet(), "Arya Stark", new Long(5));

        PatientModel patientModel = new PatientModel("Arya Stark", "1.101");

        mockMvc.perform(
                post("/patient/createOrupdate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patientModel)))
                .andExpect(status().isCreated());
        System.out.println("Store Object is " + patientService.findByName("Arya Stark"));

    }

    // =========================================== Get All Patients ==========================================
    @Test
    public void test_get_all_success() throws Exception {
        List<Patient> patients = Arrays.asList(
                new Patient(new Long(1), "Arya Stark", new Long(1)),
                new Patient(new Long(2), "Kim Tom", new Long(2)));

        when(patientService.getPatients()).thenReturn(patients);

        mockMvc.perform(get("/patient/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Arya Stark")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Kim Tom")));

        verify(patientService, times(1)).getPatients();
        verifyNoMoreInteractions(patientService);
    }

    // =========================================== Get Patient By ID =========================================
    @Test
    public void test_get_by_id_success() throws Exception {
        Patient patient = new Patient(new Long(1), "Arya Stark", new Long(1));

        when(patientService.getPatient(new Long(1))).thenReturn(patient);

        mockMvc.perform(get("/patient/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Arya Stark")));

        verify(patientService, times(1)).getPatient(new Long(1));
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(patientService.getPatient(new Long(1))).thenReturn(null);

        mockMvc.perform(get("/patient/{id}", 1))
                .andExpect(status().isNotFound());

        verify(patientService, times(1)).getPatient(new Long(1));
        verifyNoMoreInteractions(patientService);
    }

    // =========================================== Update Existing Patient ===================================
   

    // =========================================== Delete Patient ============================================
    // =========================================== CORS Headers ===========================================
    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/patient"))
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
