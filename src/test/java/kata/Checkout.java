package kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Checkout {

  private List<String> scannedItems = new ArrayList<>();
  private Map<String, Integer> prices = Map.of(
      "a", 50,
      "b", 30,
      "c", 20,
      "d", 15);
  private int total;

  public void scan(String sku) {
    scannedItems.add(sku);

  }

  public int total() {
    Map<String, Long> map = scannedItems.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    int total = 0;
    for (String scannedItem : scannedItems) {
      total += prices.get(scannedItem);
    }
    if (map.get("a") != null && map.get("a") == 3) {
      total -= 20;
    }
    if (map.get("b") != null && map.get("b") == 2) {
      total -= 15;
    }
    return total;
  }
}
