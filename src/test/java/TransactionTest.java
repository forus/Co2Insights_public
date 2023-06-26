import entity.Transaction;
import services.CategoryService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testCalculateCo2ForClothesCategory(){
        Transaction transaction = new Transaction("NLAHGSD9283674983", "HM", (long) 1, LocalDateTime.now());
        long actual = transaction.getCo2Emission();
        assertEquals(1143, actual);

    }

    @Test
    void testCalculateCo2ForDefault(){
        Transaction transaction = new Transaction("NLAHGSD9283674983", "", (long) 1, LocalDateTime.now());
        long actual = transaction.getCo2Emission();
        assertEquals(0, actual);

    }
}
