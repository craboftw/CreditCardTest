import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CreditCard {
    private String number;
    private String ownerName;
    private String expirationDate;
    private int cvv;
    private double balance;
    private String tipo;
    private List<String> movimientos = new ArrayList<String>();

    public CreditCard(String number, String ownerName, String expirationDate, int cvv, double balance) {
        //Comprobamos los parámetros
        //NUMBER
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("El número de la tarjeta no puede ser nulo o vacío");
        }
        if (number.length() != 19) {
            throw new IllegalArgumentException("El número de la tarjeta debe tener 19 caracteres");
        }
        if (number.charAt(4) != '-' || number.charAt(9) != '-' || number.charAt(14) != '-') {
            throw new IllegalArgumentException("El número de la tarjeta debe tener guiones en las posiciones 4, 9 y 14");
        }
        if (!number.matches("[0-9-]+")) {
            throw new IllegalArgumentException("El número de la tarjeta debe ser numérico");
        }
        if (number.charAt(0) == '0' || number.charAt(5) == '0' || number.charAt(10) == '0' || number.charAt(15) == '0') {
            throw new IllegalArgumentException("El número de la tarjeta no puede empezar por 0");
        }
        //Ownername
        if (ownerName == null || ownerName.isEmpty()) {
            throw new IllegalArgumentException("El nombre del titular no puede ser nulo o vacío");
        }
        if (ownerName.length() > 30) {
            throw new IllegalArgumentException("El nombre del titular no puede tener más de 30 caracteres");
        }
        if (!ownerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("El nombre del titular debe ser alfabético");
        }
        //Debe tener almenos un espacio y no puede empezar ni acabar por espacio
        if (ownerName.charAt(0) == ' ' || ownerName.charAt(ownerName.length() - 1) == ' ' || ownerName.indexOf(' ') == -1) {
            throw new IllegalArgumentException("El nombre del titular debe tener al menos un espacio y no puede empezar ni acabar por espacio");
        }

        //ExpirationDate
        if (expirationDate == null || expirationDate.isEmpty()) {
            throw new IllegalArgumentException("La fecha de expiración no puede ser nula o vacía");
        }
        if (expirationDate.charAt(0) != '0' && expirationDate.charAt(0) != '1') {
            expirationDate = "0" + expirationDate;
        }
        if (expirationDate.length() != 5) {
            throw new IllegalArgumentException("La fecha de expiración debe tener 5 caracteres");
        }
        if (expirationDate.charAt(2) != '/') {
            throw new IllegalArgumentException("La fecha de expiración debe tener un / en la posición 2");
        }
        if (!expirationDate.matches("[0-9/]+")) {
            throw new IllegalArgumentException("La fecha de expiración debe ser numérica");
        }
        if (Integer.parseInt(expirationDate.substring(3, 5)) < 0 || Integer.parseInt(expirationDate.substring(3, 5)) > 99) {
            throw new IllegalArgumentException("El año de la fecha de expiración debe ser un número de 2 cifras");
        }
        if (Integer.parseInt(expirationDate.substring(0, 2)) < 1 || Integer.parseInt(expirationDate.substring(0, 2)) > 12) {
            throw new IllegalArgumentException("El mes de la fecha de expiración debe ser un número entre 1 y 12");
        }
        if (Integer.parseInt(expirationDate.substring(3, 5)) > LocalDate.now().getYear() % 100+10) {
            throw new IllegalArgumentException("Si la fecha de expiración es mayor que 10 años del año actual, esque se refiere a una de antes del 2000");
        }

        if (Integer.parseInt(expirationDate.substring(0, 2)) > 12) {
            throw new IllegalArgumentException("El mes de la fecha de expiración no puede ser mayor que 12");
        }
        if (Integer.parseInt(expirationDate.substring(3, 5)) < LocalDate.now().getYear() % 100) {
            throw new IllegalArgumentException("El año de la fecha de expiración no puede ser anterior al año actual");
        }
        //CVV
        if (cvv > 999) {
            throw new IllegalArgumentException("El código de seguridad debe ser un número de 3 cifras");
        }
        if (cvv <= 0) {
            throw new IllegalArgumentException("El código de seguridad debe ser mayor a 0");
        }

        //Set CreditCard type (Visa, MasterCard, American Express, Discover)
        if (number.charAt(0) == '4') {
            tipo = "Visa";
        } else if (number.charAt(0) == '5') {
            tipo = "MasterCard";
        } else if (number.charAt(0) == '3') {
            tipo = "American Express";
        } else if (number.charAt(0) == '6') {
            tipo = "Discover";
        } else {
            tipo = "Unknown";
        }
        this.number = number;
        this.ownerName = ownerName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public double getBalance() {
        return balance;
    }

    public String getTipo() {
        return tipo;
    }

    public void payment(double amount, String descripcion) throws InsufficientFundsException {
        if (amount > balance)
            throw new InsufficientFundsException();
        if (amount <= 0)
            throw new IllegalArgumentException("El importe a recargar no puede ser negativo");
        balance -= amount;
        movimientos.add("-" + amount + " Concepto: " + descripcion + " Fecha: " + LocalDate.now());
    }

    public void payment(double amount) throws InsufficientFundsException {
        if (amount > balance)
            throw new InsufficientFundsException();
        if (amount <= 0)
            throw new IllegalArgumentException("El importe a recargar no puede ser negativo");
        balance -= amount;
        movimientos.add("-" + amount + " Concepto: " + " Fecha: " + LocalDate.now());
    }

    public void recharge(double amount, String descripcion) {
        balance += amount;
        if (amount <= 0)
            throw new IllegalArgumentException("El importe a recargar no puede ser negativo");
        movimientos.add("+" + amount + " Concepto: " + descripcion + " Fecha: " + LocalDate.now());
    }

    public void recharge(double amount) {
        balance += amount;
        if (amount <= 0)
            throw new IllegalArgumentException("El importe a recargar no puede ser negativo");
        movimientos.add("+" + amount + " Concepto: " + " Fecha: " + LocalDate.now());
    }

public void printMovimientos(int n) {
        for (int i = movimientos.size() - n; i < movimientos.size(); i++) {
            System.out.println(movimientos.get(i));
        }
    }

    public void printMovimientos() {
        for (String movimiento : movimientos) {
            System.out.println(movimiento);
        }
    }
}

