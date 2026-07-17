public enum Sugar {
    YES, NO;

    public String toString() {
        return switch (this) {
            case YES -> "Yes";
            case NO -> "No";
        };
    }
}
