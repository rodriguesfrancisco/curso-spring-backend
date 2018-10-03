package com.francisco.cursomc.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> list = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return list;
    }

    public void addError(String fieldName, String message) {
        list.add(new FieldMessage(fieldName, message));
    }
}
