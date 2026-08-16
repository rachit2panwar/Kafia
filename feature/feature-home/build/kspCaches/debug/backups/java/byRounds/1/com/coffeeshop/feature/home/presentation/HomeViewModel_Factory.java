package com.coffeeshop.feature.home.presentation;

import com.coffeeshop.feature.home.domain.usecase.GetBannersUseCase;
import com.coffeeshop.feature.home.domain.usecase.GetProductsUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetProductsUseCase> getProductsUseCaseProvider;

  private final Provider<GetBannersUseCase> getBannersUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetProductsUseCase> getProductsUseCaseProvider,
      Provider<GetBannersUseCase> getBannersUseCaseProvider) {
    this.getProductsUseCaseProvider = getProductsUseCaseProvider;
    this.getBannersUseCaseProvider = getBannersUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getProductsUseCaseProvider.get(), getBannersUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetProductsUseCase> getProductsUseCaseProvider,
      Provider<GetBannersUseCase> getBannersUseCaseProvider) {
    return new HomeViewModel_Factory(getProductsUseCaseProvider, getBannersUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetProductsUseCase getProductsUseCase,
      GetBannersUseCase getBannersUseCase) {
    return new HomeViewModel(getProductsUseCase, getBannersUseCase);
  }
}
