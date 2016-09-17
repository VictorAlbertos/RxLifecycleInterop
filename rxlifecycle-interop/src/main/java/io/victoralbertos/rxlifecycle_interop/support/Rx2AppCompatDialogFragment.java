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
import com.trello.rxlifecycle.components.support.RxAppCompatDialogFragment;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.victoralbertos.rxlifecycle_interop.LifecycleTransformer2x;
import io.victoralbertos.rxlifecycle_interop.internal.LifecycleTransformer2xBehaviour;

public class Rx2AppCompatDialogFragment extends RxAppCompatDialogFragment {

  @NonNull
  @CheckResult
  public final Observable<FragmentEvent> lifecycle2x() {
    return RxJavaInterop.toV2Observable(lifecycle());
  }

  @NonNull
  @CheckResult
  public final <T> LifecycleTransformer2x<T> bindUntilEvent2x(
      @NonNull final FragmentEvent event, @NonNull final BackpressureStrategy strategy) {
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
