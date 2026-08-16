package com.coffeeshop.feature.home.data.repository;

import com.coffeeshop.feature.home.data.remote.HomeApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class HomeRepositoryImpl_Factory implements Factory<HomeRepositoryImpl> {
  private final Provider<HomeApi> apiProvider;

  public HomeRepositoryImpl_Factory(Provider<HomeApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public HomeRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static HomeRepositoryImpl_Factory create(Provider<HomeApi> apiProvider) {
    return new HomeRepositoryImpl_Factory(apiProvider);
  }

  public static HomeRepositoryImpl newInstance(HomeApi api) {
    return new HomeRepositoryImpl(api);
  }
}
