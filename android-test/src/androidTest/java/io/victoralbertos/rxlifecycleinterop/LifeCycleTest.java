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

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class LifeCycleTest {

  private UiDevice uiDevice;

  @Before public void before() {
    TestLogger.Instance.clearEvents();
    uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
  }

  @After public void after() {
    TestLogger.Instance.clearEvents();
  }

  @Test public void Verify_Idle() {
    waitTime();
    TestLogger.Instance.assertObservableValuesOnResume();
    TestLogger.Instance.assertObservableValuesOnCreate();
    TestLogger.Instance.assertSingleValuesOnResume();
    TestLogger.Instance.assertSingleValuesOnCreate();
    TestLogger.Instance.assertFlowableValuesOnResume();
    TestLogger.Instance.assertFlowableValuesOnCreate();
  }

  @Test public void Verify_On_Resume_Home() {
    uiDevice.pressHome();
    waitTime();
    TestLogger.Instance.assertObservableNoValuesOnResume();
    TestLogger.Instance.assertObservableValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();
    TestLogger.Instance.assertSingleValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();
    TestLogger.Instance.assertFlowableValuesOnCreate();
  }

  @Test public void Verify_On_Resume_Sleep() throws RemoteException {
    uiDevice.sleep();
    waitTime();
    TestLogger.Instance.assertObservableNoValuesOnResume();
    TestLogger.Instance.assertObservableValuesOnCreate();
    TestLogger.Instance.assertSingleNoValuesOnResume();
    TestLogger.Instance.assertSingleValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();
    TestLogger.Instance.assertFlowableValuesOnCreate();
    uiDevice.wakeUp();
  }

  @Test public void Verify_On_Destroy_Back() {
    uiDevice.pressBack();
    //Dismiss dialog if present
    uiDevice.pressBack();
    waitTime();
    TestLogger.Instance.assertObservableNoValuesOnResume();
    TestLogger.Instance.assertObservableNoValuesOnCreate();
    TestLogger.Instance.assertFlowableNoValuesOnResume();
    TestLogger.Instance.assertFlowableNoValuesOnCreate();
  }

  private void waitTime() {
    try {Thread.sleep((Presenter.WAIT_TIME + 1) * 1000);}
    catch (InterruptedException e) { e.printStackTrace();}
  }
}
