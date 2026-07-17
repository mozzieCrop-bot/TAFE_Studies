public enum Milk {
    // FULL CREAM, SKIM, SOY, ALMOND, and OAT
    FULL_CREAM, SKIM, SOY, ALMOND, OAT, NO_MILK;

    /**
     * @return the milk options
     */
    public String toString(){
        return switch (this) {
            case FULL_CREAM -> "Full Cream";
            case SKIM -> "Skim";
            case SOY -> "Soy";
            case ALMOND -> "Almond";
            case OAT -> "Oat";
            case NO_MILK -> "No Milk";
        };
    }
}
