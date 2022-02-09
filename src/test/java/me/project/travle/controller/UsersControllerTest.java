package me.project.travle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.project.travle.domain.User;
import me.project.travle.dto.user.UserResponseDto;
import me.project.travle.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UsersController.class)
class UsersControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UsersService usersService;

    @Test
    void createUserTest() throws Exception {
        //given
        Map<String, String> input = new HashMap<>();
        input.put("id", "test");
        input.put("password", "123");
        input.put("phoneNumber", "010-1234-5678");
        //when
        final ResultActions actions = mvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk());
    }

    @Test
    void createUserErrorTest() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("id", "test");
        input.put("password", "123");
        input.put("phoneNumber", "010-1234-5678");
        //when
        mvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)));
        input.replace("phoneNumber", "010-1234-1234");
        final ResultActions actions = mvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print());

        //then
        actions
                .andExpect(status().is4xxClientError());

    }

    @Test
    void findUserByIdTest() throws Exception {
        //given
        User user = User.builder().id("test").password("123").phoneNumber("123-4567-1234").build();
        UserResponseDto userResponseDto = new UserResponseDto(user);

        given(usersService.findUserById("test")).willReturn(userResponseDto);

        //when
        final ResultActions actions = mvc.perform(get("/users/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("test")))
                .andDo(print());
    }

    @Test
    void loginTest() throws Exception {

    }

}