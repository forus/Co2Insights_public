package controller;

import nl.rabobank.co2insights.entity.Transaction;
import nl.rabobank.co2insights.Co2InsightsApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import nl.rabobank.co2insights.services.TransactionService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Co2InsightsApplication.class)
public class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransactionService transactionService;

    @Test
    @WithMockUser(username = "test@test.com")
    public void getTransactions() throws Exception {
        when(transactionService.getTransactionsByIban("test@test.com", "NL01436456457577"))
                .thenReturn(Arrays.asList(
                        new Transaction("NL01436456457577", "HM", 45L, LocalDateTime.now()),
                        new Transaction("NL01436456457577", "wizzair", 200L, LocalDateTime.now())
                ));

        mockMvc.perform(get("/transactions").param("iban", "NL01436456457577"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].sourceAccountIban", is("NL01436456457577")));
    }

    @Test
    public void postTransactions() throws Exception {

    }

}
