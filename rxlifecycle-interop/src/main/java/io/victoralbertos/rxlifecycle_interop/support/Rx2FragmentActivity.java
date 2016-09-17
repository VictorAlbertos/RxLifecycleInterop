package io.victoralbertos.rxlifecycle_interop.support;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class Rx2FragmentActivity extends RxFragmentActivity {
  @NonNull
  @CheckResult
  public final Observable<ActivityEvent> lifecycle2x() {
    return RxJavaInterop.toV2Observable(lifecycle());
  }

  @NonNull
  @CheckResult
  public final <T> ObservableTransformer<T, T> bindUntilEvent2x(
      @NonNull final ActivityEvent event, @NonNull final BackpressureStrategy strategy) {
    return new ObservableTransformer<T, T>() {
      @Override public ObservableSource<T> apply(Observable<T> source) throws Exception {
        rx.Observable<T> rxObservable = RxJavaInterop.toV1Observable(source, strategy);
        rx.Observable<T> observableBound =
            (rx.Observable<T>) rxObservable.compose(bindUntilEvent(event));
        return RxJavaInterop.toV2Observable(observableBound);
      }
    };
  }

  @NonNull
  @CheckResult
  public final <T> ObservableTransformer<T, T> bindToLifecycle2x(
      @NonNull final BackpressureStrategy strategy) {
    return new ObservableTransformer<T, T>() {
      @Override public ObservableSource<T> apply(Observable<T> source) throws Exception {
        rx.Observable<T> rxObservable = RxJavaInterop.toV1Observable(source, strategy);
        rx.Observable<T> observableBound =
            (rx.Observable<T>) rxObservable.compose(bindToLifecycle());
        return RxJavaInterop.toV2Observable(observableBound);
      }
    };
  }
}
