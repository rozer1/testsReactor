package edu.iis.mto.testreactor.exc4;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;



public class DishWasherTest {

    private Door door;

    private DirtFilter dirtFilter;

    private Engine engine;

    private WaterPump waterPump;

    @Before
    public void setUp() {
        door = mock(Door.class);
        dirtFilter = mock(DirtFilter.class);
        engine = mock(Engine.class);
        waterPump = mock(WaterPump.class);
    }


    @Test
    public void dishWasherStartShouldReturnDoorOpenErrorIfDoorIsOpen() {
        when(door.closed()).thenReturn(true);
        when(dirtFilter.capacity()).thenReturn(60.0d);

        DishWasher dishWasher = new DishWasher(waterPump, engine, dirtFilter, door);
        ProgramConfiguration program = ProgramConfiguration.builder().withProgram(WashingProgram.ECO).withTabletsUsed(true).build();
        RunResult result = dishWasher.start(program);
        RunResult expectedResult = RunResult.builder().withStatus(Status.DOOR_OPEN).build();
        assertThat(result.getStatus(), is(expectedResult.getStatus()));
    }

    @Test
    public void dishWasherStartShouldReturnErrorFilterIfFilterIsNotClean() {
        when(door.closed()).thenReturn(false);
        when(dirtFilter.capacity()).thenReturn(40.0d);

        DishWasher dishWasher = new DishWasher(waterPump, engine, dirtFilter, door);
        ProgramConfiguration program = ProgramConfiguration.builder().withProgram(WashingProgram.ECO).withTabletsUsed(true).build();
        RunResult result = dishWasher.start(program);
        RunResult expectedResult = RunResult.builder().withStatus(Status.ERROR_FILTER).build();
        assertThat(result.getStatus(), is(expectedResult.getStatus()));
    }

    @Test
    public void dishWasherStartShouldReturnSuccessWithCorrectValuesGiven() {
        when(door.closed()).thenReturn(false);
        when(dirtFilter.capacity()).thenReturn(60.0d);

        DishWasher dishWasher = new DishWasher(waterPump, engine, dirtFilter, door);
        ProgramConfiguration program = ProgramConfiguration.builder().withProgram(WashingProgram.ECO).withTabletsUsed(true).build();
        RunResult result = dishWasher.start(program);
        RunResult expectedResult = RunResult.builder().withStatus(Status.SUCCESS).build();
        assertThat(result.getStatus(), is(expectedResult.getStatus()));
    }
}
