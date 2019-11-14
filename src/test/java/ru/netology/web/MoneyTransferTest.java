import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {

    @Test
    @DisplayName("переводим 1000 со второй на первую карту, баланс первой должен быть 11000")
    void shouldTransferWithSecondCardToFirstCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val moneyTransfer = new MoneyTransfer();

        moneyTransfer.transferWithSecondCardToFirstCard();
        $(withText("11000")).shouldBe(visible);
    }


    @Test
    @DisplayName("переводим 1000 с первой на вторую карту, баланс второй должен быть 10000")
    void shouldTransferWithFirstCardToSecondCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val moneyTransfer = new MoneyTransfer();

        moneyTransfer.transferWithFirstCardToSecondCard();
        $(withText("10000")).shouldBe(visible);
    }

    @Test
    @DisplayName ("переводим 20000 с первой на вторую карту, баланс второй должен быть -10000")
    void shouldTransferWithSecondCardToFirstCardNegativeBalance() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val moneyTransfer = new MoneyTransfer();

        moneyTransfer.transferWithSecondCardToFirstCardNegativeBalance();
        $(withText("-10000")).shouldBe(visible);
    }

}


