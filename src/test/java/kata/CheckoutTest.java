package kata;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CheckoutTest {


  @Test
  void oneACostsFifty() {
    Checkout checkout = new Checkout();
    checkout.scan("a");
    assertThat(checkout.total()).isEqualTo(50);
  }

  @Test
  void oneBcosts30() {
    Checkout checkout = new Checkout();

    checkout.scan("b");

    assertThat(checkout.total()).isEqualTo(30);
  }

  @Test
  void oneCcosts20() {
    Checkout checkout = new Checkout();

    checkout.scan("c");

    assertThat(checkout.total()).isEqualTo(20);
  }

  @Test
  void oneDcosts15() {
    Checkout checkout = new Checkout();

    checkout.scan("d");

    assertThat(checkout.total()).isEqualTo(15);
  }
  @Test
  void threeAcosts130() {
    Checkout checkout = new Checkout();

    checkout.scan("a");
    checkout.scan("a");
    checkout.scan("a");

    assertThat(checkout.total()).isEqualTo(130);
  }
  @Test
  void twoBcosts45() {
    Checkout checkout = new Checkout();

    checkout.scan("b");
    checkout.scan("b");

    assertThat(checkout.total()).isEqualTo(45);
  }
  @Test
  void fourAcost180() {
    Checkout checkout = new Checkout();

    checkout.scan("a");
    checkout.scan("a");
    checkout.scan("a");
    checkout.scan("a");

    assertThat(checkout.total()).isEqualTo(180);
  }
}
