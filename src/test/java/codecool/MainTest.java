package codecool;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("ALL")
public class MainTest {

    @Test
    public void example1() {
        String object = exampleFunction();
        if (object == null) {
            System.out.println(object.chars());
        }

    }

    private String exampleFunction() {
        return null;
    }

    private Optional<String> exampleBetterFunction() {

        if (true) {
            return Optional.of("String");
        }

        return Optional.empty();
    }

    @Test
    public void example2() {
        String name = "wokstym";
        Optional<String> optionalName = Optional.ofNullable(name);
        assertTrue(optionalName.isPresent());
    }

    @Test
    public void example3() {
        String name = null;
        Optional<String> optionalName = Optional.ofNullable(name);
        assertFalse(optionalName.isPresent());
    }

    @Test
    public void example4() {
        String name = "wokstym";

        Optional<String> optionalName = Optional.ofNullable(name);
        if (optionalName.isPresent()) {
            String forSureNotNullName = optionalName.get();
            System.out.println(forSureNotNullName);
        }
    }

    @Test
    public void example5() {
        String name = "wokstym";

        Optional.ofNullable(name)
                .ifPresent(System.out::println);

    }

    @Test
    public void example6() {
        // 2 ways:
        // 1:
        String name = getName();
        if (name == null) {
            name = "default";
        }

        String name1 = getName() == null ? "default" : getName();
        String name2 = name == null ? "default" : name;

        // 2:
        String betterName = Optional.ofNullable(getName())
                .orElse(getDefaultName());

    }

    private String getName() {
        return "wokstym";
    }

    @Test
    public void example7() {
        String betterName = Optional.ofNullable(getName())
                .orElseGet(() -> getDefaultName());


    }

    private String getDefaultName() {
        System.out.println("default name has been called");
        return "default";
    }

    @Test
    public void example8() {
        String betterName = Optional.ofNullable(getName())
                .filter(name -> name.length() == 7)
                .orElseGet(() -> getDefaultName());
        assertEquals("wokstym", betterName);
    }

    @Test
    public void example9() {
        String betterName = Optional.ofNullable(getName())
                .filter(name -> name.equals("bad name"))
                .filter(name -> name.length() == 7)
                .orElseGet(() -> getDefaultName());
        assertEquals("default", betterName);
    }

    @Test
    public void example10() {
        String i = Stream.of(1, 6, 3, 4)
                .map(val -> val * 2)
                .filter(val -> val > 5)
                .map(val -> String.valueOf(val))
                .findFirst()
                .orElse("no value");
        System.out.println(i);


        int betterLenght = Optional.ofNullable("wokstym")
                .map(name -> name.length())
                .filter(lenght -> lenght == 7)
                .orElseGet(() -> -1);

        assertEquals(7, betterLenght);
    }

    @Test
    public void example11() {
        Person person = new Person("grzegorz");

        if (person != null) {
            String name = person.notUsingOptionalsGetName();
            if (name != null) {
                int nameLenght = name.length();
                System.out.println(nameLenght);
            }
        }

//        boolean blank = StringUtils.isBlank("       ");

        Optional.ofNullable(person)
                .flatMap(person1 -> person1.getName())
                .map(name1 -> name1.length())
                .ifPresent(lenght -> System.out.println(lenght));


    }

    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String notUsingOptionalsGetName() {
            return name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }
    }


}
