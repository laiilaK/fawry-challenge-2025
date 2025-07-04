package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter@ToString
public class Shippable {

    protected float weight;
    protected String measure;
}
