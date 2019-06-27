package edu.iis.mto.testreactor.exc4;

public enum WashingProgram {
    INTENSIVE(120),
    ECO(90),
    RINSE(14),
    NIGHT(300);

    private int timeInMinutes;

    private WashingProgram(int timeMinutes) {
        this.timeInMinutes = timeMinutes;

    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }
}
