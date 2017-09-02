import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rjilani on 1/11/2016.
 */
public class SequenceNumberGenerator {

    private static final AtomicInteger sequence = new AtomicInteger(1);

    private SequenceNumberGenerator() {}

    public static int generate(){
        return sequence.getAndIncrement();
    }

}
