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

import android.os.Bundle;
import io.reactivex.BackpressureStrategy;
import io.reactivex.ObservableTransformer;
import io.victoralbertos.rxlifecycle_interop.Rx2Activity;
import io.victoralbertos.rxlifecycle_interop.Rx2LifecycleAndroid;

public final class ActivityView extends Rx2Activity implements Presenter.View {
  private final Presenter presenter = new Presenter();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreateView(this);
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  public ObservableTransformer getLifeCycle(BackpressureStrategy strategy) {
    return Rx2LifecycleAndroid.bindActivity(lifecycle2x(), strategy);
  }
}