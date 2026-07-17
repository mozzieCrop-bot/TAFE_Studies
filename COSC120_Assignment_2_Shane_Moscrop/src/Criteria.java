public enum Criteria {
    CATEGORY, TYPE, DWARF, TRAINING_SYSTEM, POLLINATOR;

    public String toString() {
        return switch (this) {
            case CATEGORY -> "Category";
            case TYPE -> "Type";
            case DWARF -> "Dwarf";
            case TRAINING_SYSTEM -> "Training System";
            case POLLINATOR -> "Pollinator";
        };
    }
}
