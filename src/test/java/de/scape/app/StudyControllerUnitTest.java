/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scape.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.scape.app.configuration.AppConfiguration;
import de.scape.app.configuration.CORSFilter;
import de.scape.app.controller.StudyRestController;
import de.scape.app.entities.Study;
import de.scape.app.model.StudyModel;
import de.scape.app.service.StudyService;
import java.util.Arrays;
import java.util.Date;
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
public class StudyControllerUnitTest {

    private static final AtomicLong counter = new AtomicLong();

    final static Logger logger = Logger.getLogger(StudyControllerUnitTest.class);

    private MockMvc mockMvc;

    @Mock
    private StudyService studyService;

    @InjectMocks
    private StudyRestController studyController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(studyController)
                .addFilters(new CORSFilter())
                .build();
    }

    // =========================================== Create New Study ========================================
    @Test
    public void test_create_study_success() throws Exception {
        StudyModel studyModel = new StudyModel("Fever", (short)0,(short)0,(short)0,"23.09.2017 10:00:10",new Long(1));

        mockMvc.perform(
                post("/study/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(studyModel)))
                .andExpect(status().isCreated());
        System.out.println("Store Object is " + studyService.findByName("Fever"));

    }

    // =========================================== Get All Studys ==========================================
    @Test
    public void test_get_all_success() throws Exception {
        List<Study> studies = Arrays.asList(
               
        new Study(counter.incrementAndGet(), "Fever", (short)0,(short)0,(short)0,new Date(),null,new Long(1)),
        new Study(counter.incrementAndGet(), "Fever2", (short)0,(short)0,(short)0,new Date(),null,new Long(1)));

        when(studyService.getStudies()).thenReturn(studies);

        mockMvc.perform(get("/study/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].description", is("Fever")))
                .andExpect(jsonPath("$[1].description", is("Fever2")));

        verify(studyService, times(1)).getStudies();
        verifyNoMoreInteractions(studyService);
    }

    // =========================================== Get Study By ID =========================================
    @Test
    public void test_get_by_id_success() throws Exception {
        Study study = new Study(counter.incrementAndGet(), "Fever", (short)0,(short)0,(short)0,new Date(),null,new Long(1));

        when(studyService.getStudy(new Long(1))).thenReturn(study);

        mockMvc.perform(get("/study/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("Fever")));

        verify(studyService, times(1)).getStudy(new Long(1));
        verifyNoMoreInteractions(studyService);
    }

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(studyService.getStudy(new Long(1))).thenReturn(null);

        mockMvc.perform(get("/study/{id}", 1))
                .andExpect(status().isNotFound());

        verify(studyService, times(1)).getStudy(new Long(1));
        verifyNoMoreInteractions(studyService);
    }

    // =========================================== Delete Study ============================================
    // =========================================== CORS Headers ===========================================
    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/study"))
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
