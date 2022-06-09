package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest2 {
    @Test
    public void messageGreeting() {
        Message message = new Message("Hola Mundo!", "Hello world!");

        assertEquals("Hello world!", message.greet("us"));
    }

    @Test
    public void messageGreetingDefaultLanguage() {
        Message message = new Message("Hola Mundo!", "Hello world!");

        assertEquals("Hola Mundo!", message.greet());
    }
}
