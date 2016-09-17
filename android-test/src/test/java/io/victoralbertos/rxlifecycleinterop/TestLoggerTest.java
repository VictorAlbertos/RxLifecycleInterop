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

  @Test public void Verify_On_Resume() {
    TestLogger.Instance.assertNoValuesOnCreate();
    TestLogger.Instance.assertNoValuesOnResume();

    TestLogger.Instance.show(TestLogger.Event.OnResume, "");

    TestLogger.Instance.assertNoValuesOnCreate();
    TestLogger.Instance.assertValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertNoValuesOnCreate();
    TestLogger.Instance.assertNoValuesOnResume();
  }

  @Test public void Verify_On_Create() {
    TestLogger.Instance.assertNoValuesOnCreate();
    TestLogger.Instance.assertNoValuesOnResume();

    TestLogger.Instance.show(TestLogger.Event.OnCreate, "");

    TestLogger.Instance.assertValuesOnCreate();
    TestLogger.Instance.assertNoValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertNoValuesOnCreate();
    TestLogger.Instance.assertNoValuesOnResume();
  }

  @Test public void Verify_Both() {
    TestLogger.Instance.assertNoValuesOnCreate();
    TestLogger.Instance.assertNoValuesOnResume();

    TestLogger.Instance.show(TestLogger.Event.OnCreate, "");
    TestLogger.Instance.show(TestLogger.Event.OnResume, "");

    TestLogger.Instance.assertValuesOnCreate();
    TestLogger.Instance.assertValuesOnResume();

    TestLogger.Instance.clearEvents();

    TestLogger.Instance.assertNoValuesOnCreate();
    TestLogger.Instance.assertNoValuesOnResume();
  }

}
