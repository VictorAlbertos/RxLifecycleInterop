/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.victoralbertos.rxlifecycleinterop;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.BackpressureStrategy;
import io.victoralbertos.rxlifecycle_interop.LifecycleTransformer2x;
import io.victoralbertos.rxlifecycle_interop.Rx2Fragment;
import io.victoralbertos.rxlifecycle_interop.Rx2LifecycleAndroid;

public final class SampleFragment extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getFragmentManager().beginTransaction()
        .add(new MyFragment(), "MyFragment")
        .commit();
  }

  public static class MyFragment extends Rx2Fragment implements Presenter.View {
    private final Presenter presenter = new Presenter();

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      presenter.onCreateView(this);
      return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override public void onResume() {
      super.onResume();
      presenter.onResume();
    }

    @Override public <T> LifecycleTransformer2x<T> getLifeCycle(BackpressureStrategy strategy) {
      return Rx2LifecycleAndroid.bindFragment(lifecycle2x(), strategy);
    }
  }

}
