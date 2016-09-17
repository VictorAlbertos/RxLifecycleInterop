/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.victoralbertos.rxlifecycle_interop;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;

public class Rx2LifecycleAndroid {

  @NonNull
  @CheckResult
  public static <T> LifecycleTransformer2x<T> bindActivity(
      @NonNull final Observable<ActivityEvent> lifecycle,
      @NonNull final BackpressureStrategy strategy) {
    LifecycleTransformer<T> rxTransformer =
        RxLifecycleAndroid.bindActivity(RxJavaInterop.toV1Observable(lifecycle, strategy));
    return bind(rxTransformer, strategy);
  }

  @NonNull
  @CheckResult
  public static <T> LifecycleTransformer2x<T> bindFragment(
      @NonNull final Observable<FragmentEvent> lifecycle,
      @NonNull final BackpressureStrategy strategy) {
    LifecycleTransformer<T> rxTransformer =
        RxLifecycleAndroid.bindFragment(RxJavaInterop.toV1Observable(lifecycle, strategy));
    return bind(rxTransformer, strategy);
  }

  private static <T> LifecycleTransformer2x<T> bind(final LifecycleTransformer<T> rxTransformer,
      final BackpressureStrategy strategy) {
    return new LifecycleTransformer2x<T>() {
      @Override public <U> SingleTransformer<U, U> forSingle() {
        return new SingleTransformer<U, U>() {
          @Override public SingleSource<U> apply(Single<U> source) throws Exception {
            rx.Single<U> rxSourceSingle =
                RxJavaInterop.toV1Single(source);

            return (Single<U>) RxJavaInterop
                .toV2Single(rxSourceSingle.compose(rxTransformer.forSingle()));
          }
        };
      }

      @Override public ObservableSource<T> apply(Observable<T> source) throws Exception {
        rx.Observable<T> rxSourceObservable =
            RxJavaInterop.toV1Observable(source, strategy);

        return RxJavaInterop
            .toV2Observable(rxSourceObservable.compose(rxTransformer));
      }
    };
  }
}
