public enum Pollinators {
    CRIMSON_CRISP, GALA, HONEYCRISP, PIXIE_CRUNCH, RED_DELICIOUS, RED_FUJI, BARTLETT, BOSC, D_ANJOU, REDHAVEN, CONTENDER, METHLEY, SATSUMA, ELBERTA, FUJI, GOLDEN_DELICIOUS, GRANNY_SMITH, NA;

    public String toString() {
        return switch (this) {
            case CRIMSON_CRISP -> "Crimson Crisp";
            case GALA -> "Gala";
            case HONEYCRISP -> "Honeycrisp";
            case PIXIE_CRUNCH -> "Pixie Crunch";
            case RED_DELICIOUS -> "Red Delicious";
            case RED_FUJI -> "Red Fuji";
            case BARTLETT -> "Bartlett";
            case BOSC -> "Bosc";
            case D_ANJOU -> "D'Anjou";
            case REDHAVEN -> "Redhaven";
            case CONTENDER -> "Contender";
            case METHLEY -> "Methley";
            case SATSUMA -> "Satsuma";
            case ELBERTA -> "Elberta";
            case FUJI -> "Fuje";
            case GOLDEN_DELICIOUS -> "Golden Delicious";
            case GRANNY_SMITH -> "Granny Smith";
            case NA -> "N/A";
        };
    }
}
