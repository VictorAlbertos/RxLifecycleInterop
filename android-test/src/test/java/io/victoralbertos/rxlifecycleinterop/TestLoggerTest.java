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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public final class TestLoggerTest {

  @Before public void before() {
    TestLogger.Instance.clearEvents();
  }

  @After public void after() {
    TestLogger.Instance.clearEvents();
  }

  @Test public void Verify_On_Resume_Observable() {
    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertObservableNoValuesOnResume();

    TestLogger.Instance.printObservableOnResume(1);

    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertObservableValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertObservableNoValuesOnResume();
  }

  @Test public void Verify_On_Create_Observable() {
    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertObservableNoValuesOnResume();

    TestLogger.Instance.printObservableOnCreate(1);

    TestLogger.Instance.assertObservableValuesOnCreate();
    TestLogger.Instance.assertObservableNoValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertObservableNoValuesOnResume();
  }

  @Test public void Verify_On_Resume_Single() {
    TestLogger.Instance.assertSingleNoValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();

    TestLogger.Instance.printSingleOnResume(1);

    TestLogger.Instance.assertSingleNoValuesOnCreate();
    TestLogger.Instance.assertSingleValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertSingleNoValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();
  }

  @Test public void Verify_On_Create_Single() {
    TestLogger.Instance.assertSingleNoValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();

    TestLogger.Instance.printSingleOnCreate(1);

    TestLogger.Instance.assertSingleValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertSingleNoValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();
  }

  @Test public void Verify_On_Resume_Flowable() {
    TestLogger.Instance.assertFlowableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();

    TestLogger.Instance.printFlowableOnResume(1);

    TestLogger.Instance.assertFlowableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertFlowableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();
  }

  @Test public void Verify_On_Create_Flowable() {
    TestLogger.Instance.assertFlowableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();

    TestLogger.Instance.printFlowableOnCreate(1);

    TestLogger.Instance.assertFlowableValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertFlowableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();
  }

  @Test public void Verify_All() {
    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertObservableNoValuesOnResume();
    TestLogger.Instance.assertSingleNoValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();
    TestLogger.Instance.assertFlowableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();

    TestLogger.Instance.printObservableOnResume(1);
    TestLogger.Instance.printObservableOnCreate(1);
    TestLogger.Instance.printSingleOnResume(1);
    TestLogger.Instance.printSingleOnCreate(1);
    TestLogger.Instance.printFlowableOnResume(1);
    TestLogger.Instance.printFlowableOnCreate(1);

    TestLogger.Instance.assertObservableValuesOnCreate();
    TestLogger.Instance.assertObservableValuesOnResume();
    TestLogger.Instance.assertSingleValuesOnCreate();
    TestLogger.Instance.assertSingleValuesOnResume();
    TestLogger.Instance.assertFlowableValuesOnCreate();
    TestLogger.Instance.assertFlowableValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertObservableNoValuesOnResume();
    TestLogger.Instance.assertSingleNoValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();
    TestLogger.Instance.assertFlowableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();
  }

}
