package com.coffeeshop.core.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AuthInterceptor_Factory implements Factory<AuthInterceptor> {
  @Override
  public AuthInterceptor get() {
    return newInstance();
  }

  public static AuthInterceptor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthInterceptor newInstance() {
    return new AuthInterceptor();
  }

  private static final class InstanceHolder {
    private static final AuthInterceptor_Factory INSTANCE = new AuthInterceptor_Factory();
  }
}
