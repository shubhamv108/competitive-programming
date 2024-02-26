package code.shubham.java;

import java.util.HashMap;

/*
A record in Java is a special type of class that is intended to store data.
It is similar to a traditional Java class, but it has some unique features, such as:

It is IMMUTABLE by default, which means that its state cannot be changed once it is created.
It has a fixed set of fields, called components, that are declared in the record HEADER.
It AUTOMATICALLY GENRATES a public constructor, getters, equals, hashCode, and toString methods based on the components.
It can be customized by adding static methods, instance methods, or constructors.
Records were introduced in Java 14 as a preview feature, and they were extended in Java 15 with the ability to declare local records. Records are useful for creating data carrier classes, such as POJOs (Plain Old Java Objects) and DTOs (Data Transfer Objects), that do not have any complex logic or behavior.

Here is an example of how to declare and use a record in Java:


 */
public record PersonRecord(String name, HashMap<String, String> info) {

    public PersonRecord(final String name, final HashMap<String, String> info) {
        this.name = name; // String is immutable for security & concurrency.
        this.info = new HashMap<>(info);
    }

    @Override
    public HashMap<String, String> info() {
        return new HashMap<>(info);
    }
}