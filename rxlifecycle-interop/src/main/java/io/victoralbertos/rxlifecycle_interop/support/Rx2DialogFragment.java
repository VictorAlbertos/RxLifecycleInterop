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

package io.victoralbertos.rxlifecycle_interop.support;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxDialogFragment;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class Rx2DialogFragment extends RxDialogFragment {

  @NonNull
  @CheckResult
  public final Observable<FragmentEvent> lifecycle2x() {
    return RxJavaInterop.toV2Observable(lifecycle());
  }

  @NonNull
  @CheckResult
  public final <T> ObservableTransformer<T, T> bindUntilEvent2x(
      @NonNull final FragmentEvent event, @NonNull final BackpressureStrategy strategy) {
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
