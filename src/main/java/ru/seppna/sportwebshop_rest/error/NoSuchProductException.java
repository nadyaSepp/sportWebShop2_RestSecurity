package ru.seppna.sportwebshop_rest.error;

//наследуем от непроверяемого исключения
public class NoSuchProductException extends RuntimeException{

    public NoSuchProductException(String message) {
        super(message);
    }
}
