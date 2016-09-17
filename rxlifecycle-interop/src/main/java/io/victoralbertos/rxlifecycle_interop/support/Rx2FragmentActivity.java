package io.victoralbertos.rxlifecycle_interop.support;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.victoralbertos.rxlifecycle_interop.LifecycleTransformer2x;
import io.victoralbertos.rxlifecycle_interop.internal.LifecycleTransformer2xBehaviour;

public class Rx2FragmentActivity extends RxFragmentActivity {
  @NonNull
  @CheckResult
  public final Observable<ActivityEvent> lifecycle2x() {
    return RxJavaInterop.toV2Observable(lifecycle());
  }

  @NonNull
  @CheckResult
  public final <T> LifecycleTransformer2x<T> bindUntilEvent2x(
      @NonNull final ActivityEvent event, @NonNull final BackpressureStrategy strategy) {
    return new LifecycleTransformer2xBehaviour<>(bindUntilEvent(event),
        bindUntilEvent(event).forSingle(), strategy);
  }

  @CheckResult
  public final <T> LifecycleTransformer2x<T> bindToLifecycle2x(
      @NonNull final BackpressureStrategy strategy) {
    return new LifecycleTransformer2xBehaviour<>(bindToLifecycle(),
        bindToLifecycle().forSingle(), strategy);
  }
}
