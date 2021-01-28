package no.knowit.godeboker.bok;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerBokTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BokRepository bokRepository;

    @Test
    void shouldReturn200Status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/get")).andExpect(status().is2xxSuccessful());
    }
}
