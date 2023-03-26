import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class CreditCardTest {

    CreditCard creditCard;

    @Before
    public void setUp() {
        creditCard = new CreditCard("4779-5518-4645-9207", "Pakito ypongo", "01/25", 123, 1000);
    }
    //Constructor y excepciones
    @Test
    public void testConstructorWithNullNumber() {
        assertThrows(IllegalArgumentException.class, () -> new CreditCard(null, "Elvis Cocho", "03/25", 123, 1000.0));
    }

    @Test
    public void testConstructorWithEmptyNumber() {
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("", "Elvis Cocho", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard(" ", "Elvis Cocho", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("  ", "Elvis Cocho", "03/25", 123, 1000.0));
    }

    @Test
    public void testConstructorWithInvalidNumberLength() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-345", "Elvis Cocho", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-34567", "Elvis Cocho", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-345678", "Elvis Cocho", "03/25", 123, 1000.0));
    }

    @Test
    public void testConstructorWithInvalidNumberFormat() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-34x6", "Elvis Cocho", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("123 -5678-9012-3456", "Elvis Cocho", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-901234-56", "Elvis Cocho", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456-", "Elvis Cocho", "03/25", 123, 1000.0));

    }

    @Test
    public void testConstructorWithNumberStartingWithZero() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("0234-5678-9012-3456", "Elvis Cocho", "03/25", 123, 1000.0));
    }

    @Test
    public void testConstructorWithNullOwnerName() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", null, "03/25", 123, 1000.0));

    }

    @Test
    public void testConstructorWithEmptyOwnerName() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", " ", "03/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "  ", "03/25", 123, 1000.0));
    }

    @Test
    public void testConstructorWithNullExpirationDate() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", null, 123, 1000.0));
    }

    @Test
    public void testConstructorWithEmptyExpirationDate() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", " ", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "  ", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "  /  ", 123, 1000.0));
    }

    @Test
    public void testConstructorWithInvalidExpirationDate() {
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "13/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "00/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "-1/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "a/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "1a/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "00///", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "10/2a", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "0/25", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "1/25", 123, 1000.0));

    }

    @Test
    public void testConstructorWithExpiredExpirationDate() {
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/20", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/21", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () ->  new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/22", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/99", 123, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/00", 123, 1000.0));
    }

    @Test
    public void testConstructorWithInvalidCVV() {
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/25", 1000, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/25", 0, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/25", -1, 1000.0));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/25", 10000, 1000.0));
    }


    @Test
    public void testConstructorWithValidData() {
        CreditCard card = new CreditCard("1234-5678-9012-3456", "Elvis Cocho", "03/25", 123, 1000.0);
        assertEquals("1234-5678-9012-3456", card.getNumber());
        assertEquals("Elvis Cocho", card.getOwnerName());
        assertEquals("03/25", card.getExpirationDate());
        assertEquals(123, card.getCvv());
        assertEquals(1000.0, card.getBalance(), 0);
    }


    @Test
    public void testRecharge() {
        creditCard.recharge(1500);
        assertEquals(2500, creditCard.getBalance(), 0);
        creditCard.recharge(0.01);
        assertEquals(2500.01, creditCard.getBalance(), 0);
        assertThrows(IllegalArgumentException.class, () -> creditCard.recharge(0));
        assertThrows(IllegalArgumentException.class, () -> creditCard.recharge(-1));
        assertThrows(IllegalArgumentException.class, () -> creditCard.recharge(-100));

    }

    @Test
    public void testPayment() {
        try {
            creditCard.payment(500);
            assertEquals(500, creditCard.getBalance(), 0);
            assertThrows(InsufficientFundsException.class, () -> creditCard.payment(501));
            assertThrows(IllegalArgumentException.class, () -> creditCard.payment(-1));
            assertThrows(IllegalArgumentException.class, () -> creditCard.payment(0));
            assertThrows(IllegalArgumentException.class, () -> creditCard.payment(-100));
        } catch (InsufficientFundsException e) {
            fail("No debería haber lanzado excepción");
        }
    }

    @Test
    public void testGetTipo() {
        //Create all other type of card
        CreditCard creditCardMasterCard = new CreditCard("5579-5518-4645-9207", "Perico Palotes", "01/25", 123, 1000);
        CreditCard creditCardAmericanExpress = new CreditCard("3779-5518-4645-9207", "Fistro Rodrigo", "01/25", 123, 1000);
        CreditCard creditCardDiscover = new CreditCard("6779-5518-4645-9207", "Cervantes Unamano", "01/25", 123, 1000);
        CreditCard creditCardUnknown = new CreditCard("7779-5518-4645-9207", "Lorena Morena", "01/25", 123, 1000);

        assertEquals("Visa", creditCard.getTipo());
        assertEquals("MasterCard", creditCardMasterCard.getTipo());
        assertEquals("American Express", creditCardAmericanExpress.getTipo());
        assertEquals("Discover", creditCardDiscover.getTipo());
        assertEquals("Unknown", creditCardUnknown.getTipo());
    }

}
