package com.example.sumptuous.controller;

import com.example.sumptuous.controller.IngredientController;
import com.example.sumptuous.dto.IngredientDto;
import com.example.sumptuous.service.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value= IngredientController.class)
public class IngredientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientService ingredientService;

    @Test
    public void testCheckIngredientByName() throws Exception{
        when(ingredientService.checkIngredientByName(Mockito.anyString())).thenReturn(true);
        String URI = "/ingredient?name=name";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("true");
        //assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetIngredients() throws Exception{

        String URI = "/ingredients";
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        IngredientDto ingredientDto1 = new IngredientDto();
        ingredientDto1.setId(1L);
        ingredientDto1.setName("milk");
        IngredientDto ingredientDto2 = new IngredientDto();
        ingredientDto1.setId(2L);
        ingredientDto1.setName("butter");
        ingredientDtos.add(ingredientDto1);
        ingredientDtos.add(ingredientDto2);
        when(ingredientService.getIngredients()).thenReturn(ingredientDtos);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(ingredientDtos);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);

    }
    @Test
    public void testAddIngredient() throws Exception{

        String URI = "/ingredient";

        IngredientDto ingredientDto1 = new IngredientDto();
        ingredientDto1.setId(1L);
        ingredientDto1.setName("milk");
        String inputInJson = this.mapToJson(ingredientDto1);
        when(ingredientService.addIngredient(Mockito.any(IngredientDto.class))).thenReturn(ingredientDto1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);

    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
