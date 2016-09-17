package io.victoralbertos.rxlifecycleinterop;

import android.support.test.rule.ActivityTestRule;
import io.victoralbertos.rxlifecycleinterop.support.FragmentActivitySample;
import io.victoralbertos.rxlifecycleinterop.support.SampleAppCompatActivity;
import io.victoralbertos.rxlifecycleinterop.support.SampleAppCompatDialogFragment;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestSuit.SampleActivityTest.class,
    TestSuit.SampleDialogFragmentTest.class,
    TestSuit.SampleFragmentTest.class,
    TestSuit.Support.FragmentActivitySampleTest.class,
    TestSuit.Support.SampleAppCompatActivityTest.class,
    TestSuit.Support.SampleAppCompatDialogFragmentTest.class,
    TestSuit.Support.SampleDialogFragmentTest.class,
    TestSuit.Support.SampleFragmentTest.class
})
public class TestSuit {

  public static final class SampleActivityTest extends LifeCycleTest {
    @Rule public ActivityTestRule<SampleActivity> mActivityRule =
        new ActivityTestRule<>(SampleActivity.class);
  }

  public static final class SampleDialogFragmentTest extends LifeCycleTest {
    @Rule public ActivityTestRule<SampleDialogFragment> mActivityRule =
        new ActivityTestRule<>(SampleDialogFragment.class);
  }

  public static final class SampleFragmentTest extends LifeCycleTest {
    @Rule public ActivityTestRule<SampleFragment> mActivityRule =
        new ActivityTestRule<>(SampleFragment.class);
  }

  public static final class Support {

    public static final class FragmentActivitySampleTest extends LifeCycleTest {
      @Rule public ActivityTestRule<FragmentActivitySample> mActivityRule =
          new ActivityTestRule<>(FragmentActivitySample.class);
    }

    public static final class SampleAppCompatActivityTest extends LifeCycleTest {
      @Rule public ActivityTestRule<SampleAppCompatActivity> mActivityRule =
          new ActivityTestRule<>(SampleAppCompatActivity.class);
    }

    public static final class SampleAppCompatDialogFragmentTest extends LifeCycleTest {
      @Rule public ActivityTestRule<SampleAppCompatDialogFragment> mActivityRule =
          new ActivityTestRule<>(SampleAppCompatDialogFragment.class);
    }

    public static final class SampleDialogFragmentTest extends LifeCycleTest {
      @Rule
      public ActivityTestRule<io.victoralbertos.rxlifecycleinterop.support.SampleDialogFragment>
          mActivityRule =
          new ActivityTestRule<>(
              io.victoralbertos.rxlifecycleinterop.support.SampleDialogFragment.class);
    }

    public static final class SampleFragmentTest extends LifeCycleTest {
      @Rule public ActivityTestRule<io.victoralbertos.rxlifecycleinterop.support.SampleFragment>
          mActivityRule =
          new ActivityTestRule<>(io.victoralbertos.rxlifecycleinterop.support.SampleFragment.class);
    }
  }
}
