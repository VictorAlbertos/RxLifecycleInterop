package io.victoralbertos.rxlifecycle_interop;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

public interface LifecycleTransformer2x<T> extends ObservableTransformer<T, T> {
  <U> SingleTransformer<U, U> forSingle();
  <U> FlowableTransformer<U, U> forFlowable();
}
