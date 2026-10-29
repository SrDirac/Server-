package es.srdirac.p7springboot;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class P7springbootApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // Las dos paginas responden y por defecto salen en español
    @Test
    void paginasEnEspanol() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ruta histórica por Toledo")));
        mockMvc.perform(get("/visita"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cómo llegar")));
    }

    // Con ?lang=en salen en ingles
    @Test
    void paginasEnIngles() throws Exception {
        mockMvc.perform(get("/").param("lang", "en"))
                .andExpect(content().string(containsString("Historic walk through Toledo")));
        mockMvc.perform(get("/visita").param("lang", "en"))
                .andExpect(content().string(containsString("How to get here")));
    }

    // Despues de elegir ingles, la siguiente pagina sigue en ingles sin poner ?lang
    @Test
    void elIdiomaSeGuardaEnLaSesion() throws Exception {
        MockHttpSession sesion = new MockHttpSession();
        mockMvc.perform(get("/").param("lang", "en").session(sesion));
        mockMvc.perform(get("/visita").session(sesion))
                .andExpect(content().string(containsString("How to get here")));
    }
}
