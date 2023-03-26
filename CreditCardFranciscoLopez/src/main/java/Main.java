import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {

    public static void main(String[] args) {
        // Ejecutamos la suite de pruebas
        Result result = JUnitCore.runClasses(CreditCardTest.class);

        // Imprimimos los resultados de la suite de pruebas
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("¿Completados todos los test? : " + result.wasSuccessful());

    }


}
