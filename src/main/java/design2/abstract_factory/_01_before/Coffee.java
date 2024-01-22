package design2.abstract_factory._01_before;

import design2.abstract_factory._02_after.Ice;
import design2.abstract_factory._02_after.Sugar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coffee {

  private String name;
  private String size;
  private String logo;
  private Integer price;
  private Ice ice;
  private Sugar sugar;

}
