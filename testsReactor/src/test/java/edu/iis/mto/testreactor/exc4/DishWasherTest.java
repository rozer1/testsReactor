package edu.iis.mto.testreactor.exc4;

import static org.junit.Assert.*;

import org.junit.Test;


public class DishWasherTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }
    @Test
    public void dishWasherStartShouldReturnDoorOpenErrorIfDoorIsOpen() {
        Door door = mock(Door.class);
        DirtFilter dirtFilter = mock(DirtFilter.class);
        Engine engine = mock(Engine.class);
        WaterPump waterpPump = mock(WaterPump.class);

        when(door.closed()).thenReturn(true);
        when(dirtFilter.capacity()).thenReturn(60.0d);

        DishWasher dishWasher = new DishWasher(waterpPump, engine, dirtFilter, door);
        ProgramConfiguration program = ProgramConfiguration.builder().withProgram(WashingProgram.ECO).withTabletsUsed(true).build();
        RunResult result = dishWasher.start(program);
        RunResult expectedResult = RunResult.builder().withStatus(Status.DOOR_OPEN).build();
        assertThat(result, is(expectedResult));
    }
    
}
