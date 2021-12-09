package com.cicdlectures.menuserver.exception;

import java.lang.RuntimeException;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(Long id) {
        super("Could not find menu " + id);
      }
}
