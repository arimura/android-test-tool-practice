package com.hormiga6.androidtesttoolpractice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockitoTest {
    @Mock
    PrintService printService;

    @Mock
    User user;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void stubVoidMethod(){
        when(user.getName()).thenReturn("mock name");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                User user= invocation.getArgument(0);
                assertThat(user.getName(),is("mock name"));
                System.out.print("hooked in void method");
                return null;
            }
        }).when(printService).printUserName(any(User.class));

        UserPrinter userPrinter = new UserPrinter(user, printService);
        userPrinter.run();
        verify(printService).printUserName(any(User.class));
    }

    static class UserPrinter {
        private User user;
        private PrintService printService;

        public UserPrinter(User user, PrintService printService){
            this.user = user;
            this.printService = printService;
        }

        public void run(){
            printService.printUserName(user);
        }
    }

    static class PrintService {
        public void printUserName(User user){
            System.out.println(user.getName());
        }
    }

    static class User{
        private String name;

        User(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}
