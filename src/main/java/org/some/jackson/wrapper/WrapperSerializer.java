package org.some.jackson.wrapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

class WrapperSerializer<T> extends StdSerializer<T> implements ContextualSerializer {

  private final String wrapperName;
  private JsonSerializer<Object> delegatingSerializer;

  protected WrapperSerializer(Class<T> clazz, String wrapperName) {
    super(clazz);
    this.wrapperName = wrapperName;
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property)
      throws JsonMappingException {

    System.out.println("Creating contextual serializer");
    if (property == null) {
      // ???
      return this;
    }

    WrappedList annotation = property.getAnnotation(WrappedList.class);
    if (annotation != null) {
      this.delegatingSerializer = provider.findTypedValueSerializer(property.getType(), true, property);
      return this;
    }
    // ???
    return this;
  }

  @Override
  public void serialize(T value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    gen.writeFieldName(wrapperName);
    delegatingSerializer.serialize(value, gen, provider);
    gen.writeEndObject();
  }
}
