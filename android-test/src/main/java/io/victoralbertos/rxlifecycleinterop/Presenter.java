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

package io.victoralbertos.rxlifecycleinterop;

import android.support.annotation.VisibleForTesting;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.victoralbertos.rxlifecycle_interop.LifecycleTransformer2x;
import java.util.concurrent.TimeUnit;

public final class Presenter {
  private View view;
  @VisibleForTesting public final static int WAIT_TIME = 2;

  public void onCreateView(View view) {
    this.view = view;

    Observable.interval(WAIT_TIME, TimeUnit.SECONDS)
        .compose(view.getLifeCycle(BackpressureStrategy.LATEST))
        .subscribe(TestLogger.Instance::printObservableOnCreate);

    Single.just(1)
        .delay(WAIT_TIME, TimeUnit.SECONDS)
        .compose(view.getLifeCycle(BackpressureStrategy.LATEST).forSingle())
        .subscribe(TestLogger.Instance::printSingleOnCreate,
            ignored -> {
            });
  }

  public void onResume() {
    Observable.interval(WAIT_TIME, TimeUnit.SECONDS)
        .compose(view.getLifeCycle(BackpressureStrategy.LATEST))
        .subscribe(TestLogger.Instance::printObservableOnResume);

    Single.just(1)
        .delay(WAIT_TIME, TimeUnit.SECONDS)
        .compose(view.getLifeCycle(BackpressureStrategy.LATEST).forSingle())
        .subscribe(TestLogger.Instance::printSingleOnResume,
            ignored -> {
            });
  }

  public interface View {
    <T> LifecycleTransformer2x<T> getLifeCycle(BackpressureStrategy strategy);
  }
}
