package org.some.jackson.wrapper;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class WrapperIntrospector extends JacksonAnnotationIntrospector {

  @Override
  public Object findSerializer(Annotated a) {
    WrappedList annotation = a.getAnnotation(WrappedList.class);
    if (annotation != null) {
      System.out.println(annotation);
      Class<?> rawType = a.getRawType();
      return new WrapperSerializer<>(rawType, annotation.value());
    }
    return super.findSerializer(a);
  }
}
