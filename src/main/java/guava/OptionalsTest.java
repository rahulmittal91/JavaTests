package guava;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Created by rmittal1 on 2/20/16.
 */
public class OptionalsTest {
    public void test() {
        String s = null;
        Optional<String> stringOptional = Optional.absent();
        //stringOptional.get();                      //Throws a Illelegal State Exceptiom

        Optional<String> stringOptional1 = Optional.fromNullable(s);
        //System.out.println(stringOptional1.get());   //Throws a Illelegal State Exceptiom

        if(!stringOptional1.isPresent()){
            System.out.println("true");
        }
        Optional<String> stringOptional2 = Optional.of(s);   // Throws a null pointer exception

        s = "new";
        Optional<String> stringOptional3 = Optional.fromNullable(s);
        System.out.println(stringOptional3.get());

        Optional<String> stringOptional4 = Optional.of(s);
        System.out.println(stringOptional4.get());

        Optional<String> stringOptional5 = stringOptional.or(stringOptional3);
        System.out.println(stringOptional5.get());

        Optional<String> stringOptional6 = stringOptional3.or(Optional.<String>absent());
        System.out.println(stringOptional6.get());

        String s1 = stringOptional.or("old");
        System.out.println(s1);

        String s2 = stringOptional3.or("old");
        System.out.println(s2);

        String s3 = stringOptional.orNull();
        System.out.println(s3);

    }

    public static void main(String args[]) {
        OptionalsTest test = new OptionalsTest();
        test.test();
    }
}
