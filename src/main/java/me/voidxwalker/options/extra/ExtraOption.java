package me.voidxwalker.options.extra;

public class ExtraOption {

    private final String name;
    private float value;

    public ExtraOption(String name, float value) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public String getDisplayName() {
        return name + ": " + (int) (value * 100) + "%";
    }

}
