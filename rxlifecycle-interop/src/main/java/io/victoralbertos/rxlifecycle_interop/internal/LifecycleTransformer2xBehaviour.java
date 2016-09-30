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

package io.victoralbertos.rxlifecycle_interop.internal;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.victoralbertos.rxlifecycle_interop.LifecycleTransformer2x;
import org.reactivestreams.Publisher;
import rx.Single;

public final class LifecycleTransformer2xBehaviour<T> implements LifecycleTransformer2x<T> {
  private final rx.Observable.Transformer<?, ?> rxTransformerBound;
  private final rx.Single.Transformer<?, ?> rxSingleTransformer;
  private final BackpressureStrategy strategy;

  public LifecycleTransformer2xBehaviour(rx.Observable.Transformer<?, ?> rxTransformerBound,
      Single.Transformer<?, ?> rxSingleTransformer, BackpressureStrategy strategy) {
    this.rxTransformerBound = rxTransformerBound;
    this.rxSingleTransformer = rxSingleTransformer;
    this.strategy = strategy;
  }

  @Override public <U> FlowableTransformer<U, U> forFlowable() {
    return new FlowableTransformer<U, U>() {
      @Override public Publisher<? extends U> apply(Flowable<U> source) throws Exception {
        rx.Observable<U> rxObservable = RxJavaInterop.toV1Observable(source.toObservable(), strategy);
        rx.Observable<T> observableBound = rxObservable.compose(
            (rx.Observable.Transformer<? super U, ? extends T>) rxTransformerBound);
        return (Publisher<? extends U>) RxJavaInterop.toV2Observable(observableBound).toFlowable(strategy);
      }
    };
  }

  @Override public <U> SingleTransformer<U, U> forSingle() {
    return new SingleTransformer<U, U>() {
      @Override public SingleSource<U> apply(io.reactivex.Single<U> source) throws Exception {
        rx.Single<U> rxSourceSingle =
            RxJavaInterop.toV1Single(source);

        rx.Single<T> rxBoundSingle = rxSourceSingle
            .compose((Single.Transformer<? super U, ? extends T>) rxSingleTransformer);

        return (io.reactivex.Single<U>) RxJavaInterop.toV2Single(rxBoundSingle);
      }
    };
  }

  @Override public ObservableSource<T> apply(Observable<T> source) throws Exception {
    rx.Observable<T> rxObservable = RxJavaInterop.toV1Observable(source, strategy);
    rx.Observable<T> observableBound = rxObservable
        .compose((rx.Observable.Transformer<? super T, ? extends T>) rxTransformerBound);
    return RxJavaInterop.toV2Observable(observableBound);
  }
}
