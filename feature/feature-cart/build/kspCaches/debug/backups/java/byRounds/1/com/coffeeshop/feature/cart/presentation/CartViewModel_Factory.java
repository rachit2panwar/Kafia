package com.coffeeshop.feature.cart.presentation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  @Override
  public CartViewModel get() {
    return newInstance();
  }

  public static CartViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CartViewModel newInstance() {
    return new CartViewModel();
  }

  private static final class InstanceHolder {
    private static final CartViewModel_Factory INSTANCE = new CartViewModel_Factory();
  }
}
