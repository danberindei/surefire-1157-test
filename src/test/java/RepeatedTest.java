import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Dan Berindei
 */
@Test
public class RepeatedTest {
   private Process stressProcess;

   @BeforeClass
   public void startStressThread() throws IOException {
      stressProcess = new ProcessBuilder("yes", "blah").redirectOutput(ProcessBuilder.Redirect.INHERIT)
                                                         .redirectError(ProcessBuilder.Redirect.INHERIT)
                                                         .start();
   }

   @AfterClass
   public void stopStressThread() throws InterruptedException {
      stressProcess.destroyForcibly();
   }

   @Test(invocationCount = 100)
   public void testSomething() throws InterruptedException {
      Thread.sleep(1);
      System.out.print(".");
      //throwExceptionWithLongStackTrace(5);
   }

   private void throwExceptionWithLongStackTrace(int frames) {
      if (frames > 0) {
         throwExceptionWithLongStackTrace(frames - 1);
      } else {
         throw new RuntimeException("Expected");
      }
   }
}
