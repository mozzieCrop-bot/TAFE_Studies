public enum TrainingSystems {
    Y_TRELLIS, A_FRAME_TRELLIS, PERGOLA, TRELLIS, CORDON, T_BAR_TRELLIS, GUYOT, NA;

    public String toString() {
        return switch (this) {
            case Y_TRELLIS -> "Y-Trellis";
            case A_FRAME_TRELLIS -> "A-Frame Trellis";
            case PERGOLA -> "Pergola";
            case TRELLIS -> "Trellis";
            case CORDON -> "Cordon";
            case T_BAR_TRELLIS -> "T-Bar Trellis";
            case GUYOT -> "Guyot";
            case NA -> "N/A";
        };
    }
}
