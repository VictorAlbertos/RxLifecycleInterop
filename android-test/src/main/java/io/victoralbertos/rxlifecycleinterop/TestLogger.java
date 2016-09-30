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

import android.util.Log;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public enum TestLogger {
  Instance;

  private int eventsObservableOnResume;
  private int eventsSingleOnResume;
  private int eventsFlowableOnResume;
  private int eventsObservableOnCreate;
  private int eventsSingleOnCreate;
  private int eventsFlowableOnCreate;

  public void printObservableOnResume(long times) {
    eventsObservableOnResume++;
    Log.d("ObservableOnResume", String.valueOf(times));
  }

  public void printObservableOnCreate(long times) {
    eventsObservableOnCreate++;
    Log.d("ObservableOnCreate", String.valueOf(times));
  }

  public void printSingleOnResume(int times) {
    eventsSingleOnResume++;
    Log.d("SingleOnResume", String.valueOf(times));
  }

  public void printSingleOnCreate(long times) {
    eventsSingleOnCreate++;
    Log.d("SingleOnCreate", String.valueOf(times));
  }

  public void printFlowableOnResume(long times) {
    eventsFlowableOnResume++;
    Log.d("FlowableOnResume", String.valueOf(times));
  }

  public void printFlowableOnCreate(long times) {
    eventsFlowableOnCreate++;
    Log.d("FlowableOnCreate", String.valueOf(times));
  }

  public void assertObservableNoValuesOnResume() {
    assertThat(eventsObservableOnResume, is(0));
  }

  public void assertObservableNoValuesOnCreate() {
    assertThat(eventsObservableOnCreate, is(0));
  }

  public void assertObservableValuesOnResume() {
    assertThat(eventsObservableOnResume, is(not(0)));
  }

  public void assertObservableValuesOnCreate() {
    assertThat(eventsObservableOnCreate, is(not(0)));
  }

  public void assertSingleNoValuesOnResume() {
    assertThat(eventsSingleOnResume, is(0));
  }

  public void assertSingleNoValuesOnCreate() {
    assertThat(eventsSingleOnCreate, is(0));
  }

  public void assertSingleValuesOnResume() {
    assertThat(eventsSingleOnResume, is(not(0)));
  }

  public void assertSingleValuesOnCreate() {
    assertThat(eventsSingleOnCreate, is(not(0)));
  }

  public void assertFlowableNoValuesOnResume() {
    assertThat(eventsFlowableOnResume, is(0));
  }

  public void assertFlowableNoValuesOnCreate() {
    assertThat(eventsFlowableOnCreate, is(0));
  }

  public void assertFlowableValuesOnResume() {
    assertThat(eventsFlowableOnResume, is(not(0)));
  }

  public void assertFlowableValuesOnCreate() {
    assertThat(eventsFlowableOnCreate, is(not(0)));
  }

  public void clearEvents() {
    eventsObservableOnCreate = 0;
    eventsObservableOnResume = 0;
    eventsSingleOnCreate = 0;
    eventsSingleOnResume = 0;
    eventsFlowableOnCreate = 0;
    eventsFlowableOnResume = 0;
  }

}
