package com.workflex.practice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MockTest {


    @Test
    public void mockTest(){
        List<String> mock = Mockito.mock(List.class);
        when(mock.size()).thenReturn(100);

        assertEquals(100, mock.size());

        mock.add("MOSTAFA TAVASSOLI");
        mock.add("ALI");


        verify(mock).add("MOSTAFA TAVASSOLI");
        verify(mock).add("ALI");



    }
}
