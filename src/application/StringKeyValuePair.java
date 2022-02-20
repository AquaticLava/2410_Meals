package application;

public class StringKeyValuePair<T> {
    T Value;
    String string;

    public StringKeyValuePair(T value, String string) {
        this.Value = value;
        this.string = string;
    }

    public T getValue() {
        return Value;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return getString();
    }
}
