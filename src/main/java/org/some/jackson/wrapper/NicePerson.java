package org.some.jackson.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  @Getter(onMethod_ = @WrappedList("Nickname"))
  @Setter(onMethod_ = @WrappedList("Nickname"))
  @JsonProperty("Nicknames")
  private List<String> nicknames;

  @Getter(onMethod_ = @WrappedList("Child"))
  @Setter(onMethod_ = @WrappedList("Child"))
  @JsonProperty("Kids")
  private List<NiceChild> kids;

  @Getter
  @Setter
  @ToString
  static class NiceChild {
    private String name;
    private String birthday;
  }
}
