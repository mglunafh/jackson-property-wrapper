package org.some.jackson.wrapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class NicePersonSerializer extends StdSerializer<NicePerson> {

  private static final String WRAPPER_NICKNAMES = "Nickname";
  private static final String WRAPPER_KIDS = "Child";

  public NicePersonSerializer() {
    super(NicePerson.class);
  }

  @Override
  public void serialize(NicePerson value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    ObjectCodec codec = gen.getCodec();

    gen.writeStartObject();
    gen.writeStringField("name", value.getName());
    gen.writeStringField("surname", value.getSurname());

    ObjectNode nicknamesNode = (ObjectNode) codec.createObjectNode();
    nicknamesNode.putPOJO(WRAPPER_NICKNAMES, value.getNicknames());
    gen.writeFieldName("nicknames");
    gen.writeObject(nicknamesNode);

    ObjectNode childrenNode = (ObjectNode) codec.createObjectNode();
    childrenNode.putPOJO(WRAPPER_KIDS, value.getKids());
    gen.writeFieldName("kids");
    gen.writeObject(childrenNode);

    gen.writeEndObject();
  }
}
