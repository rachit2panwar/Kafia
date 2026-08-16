package com.coffeeshop.feature.home.di;

import com.coffeeshop.feature.home.data.repository.HomeRepositoryImpl;
import com.coffeeshop.feature.home.domain.repository.HomeRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class HomeModule_ProvideHomeRepositoryFactory implements Factory<HomeRepository> {
  private final Provider<HomeRepositoryImpl> implProvider;

  public HomeModule_ProvideHomeRepositoryFactory(Provider<HomeRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public HomeRepository get() {
    return provideHomeRepository(implProvider.get());
  }

  public static HomeModule_ProvideHomeRepositoryFactory create(
      Provider<HomeRepositoryImpl> implProvider) {
    return new HomeModule_ProvideHomeRepositoryFactory(implProvider);
  }

  public static HomeRepository provideHomeRepository(HomeRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(HomeModule.INSTANCE.provideHomeRepository(impl));
  }
}
