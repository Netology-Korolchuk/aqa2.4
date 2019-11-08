import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {

    @Test
    @DisplayName ("переводим 1000 со второй на первую карту")
    void shouldTransferMoneyWithFirstCardToSecondCard() {
        val moneyTransfer = new MoneyTransfer();
        moneyTransfer.loginAndVerification();

        moneyTransfer.toFirstCardTransfer.click();
        moneyTransfer.amount.setValue("1000");
        moneyTransfer.from.setValue("5559000000000002");
        moneyTransfer.transferButton.click();
        moneyTransfer.firstCard.shouldBe(visible).$(withText("11000"));
    }

    @Test
    @DisplayName ("переводим 1000 с первой карту на вторую")
    void shouldTransferMoneyWithSecondCardToFirstCard() {
        val moneyTransfer = new MoneyTransfer();
        moneyTransfer.loginAndVerification();

        moneyTransfer.toSecondCardTransfer.click();
        moneyTransfer.amount.setValue("1000");
        moneyTransfer.from.setValue("5559000000000001");
        moneyTransfer.transferButton.click();
        moneyTransfer.secondCard.shouldBe(visible).$(withText("14000"));
    }

    @Test
    @DisplayName ("переводим 20000 со второй на первую карту, отрицательный баланс")
    void shouldTransferMoneyWithFirstCardToSecondCardNegativeBalance() {
        val moneyTransfer = new MoneyTransfer();
        moneyTransfer.loginAndVerification();

        moneyTransfer.toFirstCardTransfer.click();
        moneyTransfer.amount.setValue("20000");
        moneyTransfer.from.setValue("5559000000000002");
        moneyTransfer.transferButton.click();
        moneyTransfer.secondCard.shouldBe(visible).$(withText("-5000"));

    }
}


