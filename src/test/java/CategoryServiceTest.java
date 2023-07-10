import nl.rabobank.co2insights.entity.Transaction;
import org.junit.jupiter.api.Test;
import nl.rabobank.co2insights.services.CategoryService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryServiceTest {

    @Test
    void testCategorization() {
        Transaction transaction = new Transaction("NLAHGSD9283674983", "HM", (long) 150.76, LocalDateTime.now());
        System.out.println(transaction.getCategory());
        assertEquals("Clothes", String.valueOf(CategoryService.categorize(transaction)));

    }
}
