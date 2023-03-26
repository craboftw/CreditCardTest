# Ejercicio utilizando jUnit: CreditCard
Este ejercicio consiste en utilizar el framework jUnit para diseñar y codificar una suite de pruebas unitarias para la clase CreditCard, que simula la interfaz y el comportamiento de una tarjeta de crédito.

## Diagrama UML
El diagrama UML para la clase CreditCard se muestra a continuación:
```mermaid
classDiagram
    class CreditCard {
        -String number
        -String ownerName
        -String expirationDate
        -int cvv
        -double balance
        -String tipo
        -List<String> movimientos
        +CreditCard(number: String, ownerName: String, expirationDate: String, cvv: int, balance: double)
        +getNumber(): String
        +getOwnerName(): String
        +getExpirationDate(): String
        +getCvv(): int
        +getBalance(): double
        +getTipo(): String
        +payment(amount: double, descripcion: String): void
        +payment(amount: double): void
        +recharge(amount: double, descripcion: String): void
        +recharge(amount: double): void
        +printMovimientos(n: int): void
        +printMovimientos(): void
    }

    class CreditCardTest {
        -CreditCard creditCard
        +setUp(): void
        +testConstructorWithNullNumber(): void
        +testConstructorWithEmptyNumber(): void
        +testConstructorWithInvalidNumberLength(): void
        +testConstructorWithInvalidNumberFormat(): void
        +testPayment(): void
        +testPaymentWithNegativeAmount(): void
        +testPaymentWithInsufficientFunds(): void
        +testRecharge(): void
        +testRechargeWithNegativeAmount(): void
        +testPrintMovimientos(): void
        +testPrintMovimientosWithNegativeNumber(): void
    }

    class InsufficientFundsException {
        +InsufficientFundsException()
    }

    CreditCardTest --> CreditCard
    CreditCard --> InsufficientFundsException

```
