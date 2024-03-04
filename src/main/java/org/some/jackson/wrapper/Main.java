package org.some.jackson.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import lombok.SneakyThrows;
import org.some.jackson.wrapper.NicePerson.NiceChild;
import org.some.jackson.wrapper.Person.Child;
import org.some.jackson.wrapper.Person.Kids;
import org.some.jackson.wrapper.Person.Nicknames;

public class Main {

  public static void main(String[] args) {
//    workWithPerson();
    workWithNicePerson();
  }

  @SneakyThrows
  private static void workWithNicePerson() {
    NicePerson person = new NicePerson();
    person.setName("James");
    person.setSurname("Smith");
    person.setNicknames(List.of("Al-Dente", "Roundhead", "Whiskers"));

    NiceChild kate = new NiceChild();
    kate.setName("Kate");
    kate.setBirthday("2015-01-01");
    NiceChild lucy = new NiceChild();
    lucy.setName("Lucy");
    lucy.setBirthday("2018-08-08");
    person.setKids(List.of(kate, lucy));

    System.out.println(person);

    ObjectMapper objectMapper = new ObjectMapper();
    String personStr = objectMapper.writeValueAsString(person);
    System.out.println(personStr);

    NicePerson personDeser = objectMapper.readValue(personStr, NicePerson.class);
    System.out.println(personDeser);

    SimpleModule nicePersonModule = new SimpleModule().addSerializer(new NicePersonSerializer());
    ObjectMapper personMapper = new ObjectMapper().registerModule(nicePersonModule);
    personStr = personMapper.writeValueAsString(person);
    System.out.println(personStr);

    personMapper = new ObjectMapper()
        .setAnnotationIntrospector(new WrapperIntrospector());
    personStr = personMapper.writeValueAsString(person);
    System.out.println(personStr);

  }

  @SneakyThrows
  private static void workWithPerson() {
    Person person = new Person();
    person.setName("James");
    person.setSurname("Smith");

    List<String> nicknamesList = List.of("Al-Dente", "Roundhead", "Whiskers");
    Nicknames nicknames = new Nicknames();
    nicknames.setNicknames(nicknamesList);
    person.setNicknames(nicknames);

    Child kate = new Child();
    kate.setName("Kate");
    kate.setBirthday("2015-01-01");
    Child lucy = new Child();
    lucy.setName("Lucy");
    lucy.setBirthday("2018-08-08");
    Kids kids = new Kids();
    kids.setKids(List.of(kate, lucy));
    person.setKids(kids);

    System.out.println(person);

    ObjectMapper objectMapper = new ObjectMapper();
    String personStr = objectMapper.writeValueAsString(person);
    System.out.println(personStr);

    Person person1 = objectMapper.readValue(personStr, Person.class);
    System.out.println(person1);
  }
}
