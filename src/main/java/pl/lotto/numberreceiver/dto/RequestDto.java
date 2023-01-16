package pl.lotto.numberreceiver.dto;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RequestDto (@Size(min = 6, max = 6)
                          List<Integer> numbers){
}
