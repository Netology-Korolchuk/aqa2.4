import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.Keys;
import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class MoneyTransfer {

    List<SelenideElement> transfer = $$("[data-test-id=action-deposit]");
    SelenideElement toFirstCardTransfer = transfer.get(0);
    SelenideElement toSecondCardTransfer = transfer.get(1);
    SelenideElement amount = $("[data-test-id=amount] input");
    SelenideElement from = $("[data-test-id=from] input");
    SelenideElement transferButton = $("[data-test-id=action-transfer]");
    SelenideElement secondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    SelenideElement firstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");

    public void loginAndVerification() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
}
