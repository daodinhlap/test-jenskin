package com.lapdao.model;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class JpaConverterJson<E> implements AttributeConverter<E, String> {

  protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  protected final Logger LOGGER;

  private TypeReference<E> typeReference;

  static {
    OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
  }

  public JpaConverterJson(TypeReference<E> typeReference) {
    this.typeReference = typeReference;
    LOGGER = LoggerFactory.getLogger(getClass());
  }

  @Override
  public String convertToDatabaseColumn(E meta) {
    try {
      if (meta == null) {
        return null;
      }
      return OBJECT_MAPPER.writeValueAsString(meta);
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
      return null;
    }
  }

  @Override
  public E convertToEntityAttribute(String dbData) {
    try {
      if (dbData == null) {
        return null;
      }
      return OBJECT_MAPPER.readValue(dbData, typeReference);
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
      return null;
    }
  }
}
