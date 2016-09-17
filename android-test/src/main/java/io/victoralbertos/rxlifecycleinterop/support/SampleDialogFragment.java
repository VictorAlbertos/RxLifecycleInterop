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
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.victoralbertos.rxlifecycle_interop.support.Rx2DialogFragment;
import io.victoralbertos.rxlifecycleinterop.TestLogger;
import io.victoralbertos.rxlifecycleinterop.WaitTime;
import java.util.concurrent.TimeUnit;

public final class SampleDialogFragment extends FragmentActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new MyFragment()
        .show(getSupportFragmentManager(), "MyFragment");
  }

  public static class MyFragment extends Rx2DialogFragment {

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      Observable.interval(WaitTime.SECONDS_2, TimeUnit.SECONDS)
          .compose(bindToLifecycle2x(BackpressureStrategy.LATEST))
          .doOnNext(number -> TestLogger.Instance.show(TestLogger.Event.OnCreate, String.valueOf(number)))
          .subscribe();
      return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override public void onResume() {
      super.onResume();

      Observable.interval(WaitTime.SECONDS_2, TimeUnit.SECONDS)
          .compose(bindToLifecycle2x(BackpressureStrategy.LATEST))
          .doOnNext(number -> TestLogger.Instance.show(TestLogger.Event.OnResume, String.valueOf(number)))
          .subscribe();
    }
  }
}
