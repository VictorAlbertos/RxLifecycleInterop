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

package io.victoralbertos.rxlifecycleinterop.mvp;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Single;
import io.victoralbertos.rxlifecycle_interop.LifecycleTransformer2x;
import io.victoralbertos.rxlifecycleinterop.TestLogger;
import io.victoralbertos.rxlifecycleinterop.WaitTime;
import java.util.concurrent.TimeUnit;

public final class PresenterSingle {
  private View view;

  void onCreateView(View view) {
    this.view = view;

    Single.just(1)
        .delay(WaitTime.SECONDS_2, TimeUnit.SECONDS)
        .compose(view.getLifeCycle(BackpressureStrategy.LATEST).forSingle())
        .subscribe(number -> TestLogger.Instance.show(TestLogger.Event.OnCreate, String.valueOf(number)),
            ignored -> {});
  }

  void onResume() {
    Single.just(1)
        .delay(WaitTime.SECONDS_2, TimeUnit.SECONDS)
        .compose(view.getLifeCycle(BackpressureStrategy.LATEST).forSingle())
        .subscribe(number -> TestLogger.Instance.show(TestLogger.Event.OnResume, String.valueOf(number)),
            ignored -> {});
  }

  interface View {
    <T> LifecycleTransformer2x<T> getLifeCycle(BackpressureStrategy strategy);
  }
}
