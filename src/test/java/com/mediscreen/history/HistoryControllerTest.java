package com.mediscreen.history;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.history.controller.HistoryController;
import com.mediscreen.history.model.Note;
import com.mediscreen.history.model.PatientHistory;
import com.mediscreen.history.service.HistoryService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @Test
    void givenHistories_whenGetHistories_thenListOfHistoriesIsDisplayed() throws Exception {
        Note note = new Note("abc", "def","ijk", "lmn");
        Note note2 = new Note("123", "456","789","012");
        PatientHistory history1 = new PatientHistory( 1, note);
        PatientHistory history2 = new PatientHistory( 1, note2);

        List<PatientHistory> stories = new ArrayList<>();
        stories.add(history1);
        stories.add(history2);
        given(historyService.getListOfHistoriesByPatId(1)).willReturn(stories);

        mockMvc.perform(get("/history/1")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].note.noteTitle", is("abc")))
        .andReturn();
    }

    
    @Test
    void givenHistories_whenGetHistorybyId_thenThisHistoryIsDisplayed() throws Exception {
        Note note = new Note("abc", "def","ijk", "lmn");
        PatientHistory history1 = new PatientHistory( 1, note);
        String historyId = "historyId";
        history1.setHistoryId(historyId);
        given(historyService.getHistoryById("historyId")).willReturn(history1);

        mockMvc.perform(get("/history-get/"+history1.getHistoryId())).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();
    }

    @Test
    void givenHistory_whenHistoryAdded_thenHistoryIsCreated() throws Exception {
        Note note = new Note("abc", "def","ijk", "lmn");
        PatientHistory history1 = new PatientHistory( 1, note);
        when(historyService.savePatientHistory(history1)).thenReturn(history1);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(historyService.savePatientHistory(history1));
        mockMvc.perform(post("/history/add")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful());
   
    }

    @Test
    void givenHistory_whenHistoryUpdated_thenHistoryIsUpdated() throws Exception {
        Note note = new Note("abc", "def","ijk", "lmn");
        PatientHistory history1 = new PatientHistory( 1, note);
        String historyId = "historyId";
        history1.setHistoryId(historyId);
        Note note2 = new Note("123", "456","789","012");
        PatientHistory history2 = new PatientHistory( 1, note2);

        given(historyService.getHistoryById("historyId")).willReturn(history1);

    ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(history2);
        MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/history-update/" + historyId)
                              .contentType(MediaType.APPLICATION_JSON)
                              .accept(MediaType.APPLICATION_JSON)
                              .content(json);
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());

    }
}
