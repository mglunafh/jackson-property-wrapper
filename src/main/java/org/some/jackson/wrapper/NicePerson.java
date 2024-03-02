package org.some.jackson.wrapper;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NicePerson {
  private String name;
  private String surname;
  private List<String> nicknames;
  private List<NiceChild> kids;

  @Getter
  @Setter
  @ToString
  static class NiceChild {
    private String name;
    private String birthday;
  }
}
