package com.example.sumptuous.controller;

import com.example.sumptuous.bean.User;
import com.example.sumptuous.controller.LoginController;
import com.example.sumptuous.controller.RecipeController;
import com.example.sumptuous.dto.RecipeDto;
import com.example.sumptuous.dto.RecipeRequestDto;
import com.example.sumptuous.dto.RecipeRequestUserDto;
import com.example.sumptuous.dto.RecipeResponseUserDto;
import com.example.sumptuous.enums.Role;
import com.example.sumptuous.service.LoginService;
import com.example.sumptuous.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value= RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    public void testSearchRecipes() throws Exception{
        List<RecipeDto> recipeDtos = new ArrayList<>();
        RecipeRequestUserDto recipeRequestUserDto = new RecipeRequestUserDto();
        recipeRequestUserDto.setName("recipe");
        recipeRequestUserDto.setDishType("salad");
        recipeRequestUserDto.setCookingTime(10);
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("recipe");
        recipeDto.setDishType("salad");
        recipeDto.setCookingTime(10);
        recipeDtos.add(recipeDto);
        RecipeResponseUserDto recipeResponseUserDto = new RecipeResponseUserDto();
        recipeResponseUserDto.setRecipeDtos(recipeDtos);
        when(recipeService.searchRecipes(Mockito.any(RecipeRequestUserDto.class))).thenReturn(recipeResponseUserDto);
        String URI = "/recipes";
        String inputInJson = this.mapToJson(recipeRequestUserDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();
        String expectedJson = this.mapToJson(recipeResponseUserDto);
        assertThat(outputInJson).isEqualTo(expectedJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testAddRecipes() throws Exception{
        RecipeRequestDto recipeRequestDto = new RecipeRequestDto();
        recipeRequestDto.setName("recipe");
        recipeRequestDto.setDishType("salad");
        recipeRequestDto.setCookingTime(10);

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("recipe");
        recipeDto.setDishType("salad");
        recipeDto.setCookingTime(10);
        when(recipeService.addRecipe(Mockito.any(RecipeRequestDto.class))).thenReturn(recipeDto);
        String URI = "/addRecipe";
        String inputInJson = this.mapToJson(recipeRequestDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        //assertThat(response.get).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testFindRecipeByName() throws Exception{

        when(recipeService.findRecipeByName(Mockito.anyString())).thenReturn(true);
        String URI = "/recipe?name=recipe";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo("true");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testApproveRecipe() throws Exception{

        when(recipeService.approveRecipe(Mockito.anyLong())).thenReturn(true);
        String URI = "/approveRecipe?id=1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo("true");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testUnApprovedRecipes() throws Exception{
        List<RecipeDto> recipeDtos = new ArrayList<>();
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("recipe");
        recipeDto.setDishType("Salad");
        recipeDto.setCookingTime(10);
        recipeDto.setServes(2);
        recipeDtos.add(recipeDto);
        when(recipeService.getUnApprovedRecipes()).thenReturn(recipeDtos);
        String URI = "/unApprovedRecipes";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String inputInJson = this.mapToJson(recipeDtos);
        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testRejectRecipe() throws Exception{

        when(recipeService.rejectRecipe(Mockito.anyLong())).thenReturn(true);
        String URI = "/rejectRecipe?id=1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo("true");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
