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

import android.util.Log;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public enum TestLogger {
  Instance;

  private int eventsOnResume;
  private int eventsOnCreate;

  public void show(Event event, String message) {
    if (event == Event.OnCreate) eventsOnCreate++;
    else eventsOnResume++;
    Log.d(event.toString(), message);
  }

  public void assertNoValuesOnResume() {
    assertThat(eventsOnResume, is(0));
  }

  public void assertNoValuesOnCreate() {
    assertThat(eventsOnCreate, is(0));
  }

  public void assertValuesOnResume() {
    assertThat(eventsOnResume, is(not(0)));
  }

  public void assertValuesOnCreate() {
    assertThat(eventsOnCreate, is(not(0)));
  }

  public void clearEvents() {
    eventsOnCreate = 0;
    eventsOnResume = 0;
  }

  public enum Event {
    OnResume("OnResume"), OnCreate("OnCreate");
    private final String text;

    Event(final String text) {
      this.text = text;
    }

    @Override public String toString() {
      return text;
    }
  }

}
