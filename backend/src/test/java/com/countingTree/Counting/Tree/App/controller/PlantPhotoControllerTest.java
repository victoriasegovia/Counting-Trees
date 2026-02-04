package com.countingTree.Counting.Tree.App.controller;

import com.countingTree.Counting.Tree.App.model.Photo;
import com.countingTree.Counting.Tree.App.model.Plant;
import com.countingTree.Counting.Tree.App.service.PlantPhotoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PlantPhotoController.class)
public class PlantPhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantPhotoService plantPhotoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void postAddPlantPhoto_callsService() throws Exception {
        Photo photo = new Photo();
        photo.setUrl("http://example.com/photo.jpg");
        photo.setDateTaken(LocalDateTime.now());

        mockMvc.perform(post("/api/v1/plant-photos/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(photo)))
            .andExpect(status().isOk());

        verify(plantPhotoService, times(1)).addPlantPhoto(eq(5L), any(Photo.class));
    }

    @Test
    void getPlantPhotoById_returnsPhoto() throws Exception {
        Photo photo = new Photo();
        photo.setPhotoId(1L);
        photo.setUrl("http://example.com/1.jpg");
        photo.setDateTaken(LocalDateTime.now());
        photo.setPlant(new Plant());

        when(plantPhotoService.getPlantPhotoById(1L)).thenReturn(photo);

        mockMvc.perform(get("/api/v1/plant-photos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("http://example.com/1.jpg"));

        verify(plantPhotoService, times(1)).getPlantPhotoById(1L);
    }
}