////package com.insat.controller;
//
//import org.junit.Test;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertThat;
//
//public class NoteControllerTest {
//        @Test
//        public void calcule(){
//            int x = 5;
//            int y = 7;
//            assertEquals (x+y,12);
//        }
//        @Test
//        public void multiplication(){
//            int x = 5;
//            int y = 6;
//            assertTrue (x*y > 0);
//        }
//        @Test
//        public int div(){
//            int x = 5;
//            int y = 6;
//            assertNotNull (y);
//            return x/y;
//
//
//        }
//
//        @Test
//    public void subString(){
//            String s = "testing assertIn";
//            assertThat(s, containsString("foo"));
//        }
//
//
//
//
//}


package com.insat.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.insat.model.Note;
import com.insat.service.NoteService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    /** The mock mvc. */
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private NoteController noteController;

    /** The Note service. */
    @Mock
    private NoteService noteService;

    /**
     * Sets the up.
     *
     * @throws Exception
     */
    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();

    }

    @Test
    public void shouldReturnListNoteWhenGetAll() throws Exception {
//
        // GIVEN
        Note note = new Note();
        	note.setDescription("D12");
            note.setTitle("T1");
        Note []notes ={ note} ;


//         given(noteController.getAll()).willReturn(Arrays.asList(note));
        when(noteService.getNotes()).thenReturn(Arrays.asList(note));

        // THEN
        this.mockMvc.perform(get("/api/notes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].title", CoreMatchers.is("T1")))
                .andExpect(jsonPath("$[0].description", CoreMatchers.is("D12")));

         verify(noteService, times(1)).getNotes();
         verifyNoMoreInteractions(noteService);


    }

    @Test
    public void shouldReturnOneNoteWhenGetNoteById() throws Exception {
//
        // GIVEN
        Note note = new Note();
        note.setDescription("D122");
        note.setTitle("T1");
        Note []notes ={ note} ;


//         given(noteController.getAll()).willReturn(Collections.singletonList(note));
        when(noteService.getNotes()).thenReturn(Arrays.asList(note));

        // THEN
        this.mockMvc.perform(get("/api/notes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].title", CoreMatchers.is("T1")))
                .andExpect(jsonPath("$[0].description", CoreMatchers.is("D12")));

        verify(noteService, times(1)).getNotes();
        verifyNoMoreInteractions(noteService);


    }

    @Test
    public void calcul() {

        int x = 7;
        int y = 12;

        assertEquals(x + y, 18);
    }

}