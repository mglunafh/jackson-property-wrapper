package org.some.jackson.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Person {
  private String name;
  private String surname;
  private Nicknames nicknames;
  private Kids kids;

  @Getter
  @Setter
  @ToString
  static class Nicknames {
    @JsonProperty("Nickname")
    private List<String> nicknames;
  }

  @Getter
  @Setter
  @ToString
  static class Kids {
    @JsonProperty("Child")
    private List<Child> kids;
  }

  @Getter
  @Setter
  @ToString
  static class Child {
    private String name;
    private String birthday;
  }
}
