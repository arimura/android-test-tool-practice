package com.hormiga6.androidtesttoolpractice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void stubVoidMethod() {
        when(user.getName()).thenReturn("mock name");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                User user = invocation.getArgument(0);
                assertThat(user.getName(), is("mock name"));
                System.out.print("hooked in void method");
                return null;
            }
        }).when(printService).printUserName(any(User.class));

        UserPrinter userPrinter = new UserPrinter(user, printService);
        userPrinter.run();
        verify(printService).printUserName(any(User.class));
    }

    @Test
    public void stubMethodWithGenericArg() {
        final Map<String, String> map = new HashMap<>();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                map.put("hoge", "fuga");
                return null;
            }
        }).when(printService).print(ArgumentMatchers.<String>anyList());

        printService.print(Arrays.asList("hoge"));

        assertThat(map.get("hoge"), is("fuga"));
    }

    static class UserPrinter {
        private User user;
        private PrintService printService;

        public UserPrinter(User user, PrintService printService) {
            this.user = user;
            this.printService = printService;
        }

        public void run() {
            printService.printUserName(user);
        }
    }

    static class PrintService {
        public void printUserName(User user) {
            System.out.println(user.getName());
        }

        public void print(List<String> list) {
            for (String string : list) {
                System.out.print(string);
            }
        }
    }

    static class User {
        private String name;

        User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
