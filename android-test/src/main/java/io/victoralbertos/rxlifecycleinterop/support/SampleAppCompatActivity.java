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

package io.victoralbertos.rxlifecycleinterop.support;

import android.os.Bundle;
import io.reactivex.BackpressureStrategy;
import io.victoralbertos.rxlifecycle_interop.LifecycleTransformer2x;
import io.victoralbertos.rxlifecycle_interop.support.Rx2AppCompatActivity;
import io.victoralbertos.rxlifecycleinterop.Presenter;

public final class SampleAppCompatActivity extends Rx2AppCompatActivity implements Presenter.View {
  private final Presenter presenter = new Presenter();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreateView(this);
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public <T> LifecycleTransformer2x<T> getLifeCycle(BackpressureStrategy strategy) {
    return bindToLifecycle2x(strategy);
  }
}
