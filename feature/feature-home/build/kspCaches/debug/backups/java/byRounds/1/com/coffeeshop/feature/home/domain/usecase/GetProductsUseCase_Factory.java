package com.coffeeshop.feature.home.domain.usecase;

import com.coffeeshop.feature.home.domain.repository.HomeRepository;
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
public final class GetProductsUseCase_Factory implements Factory<GetProductsUseCase> {
  private final Provider<HomeRepository> repositoryProvider;

  public GetProductsUseCase_Factory(Provider<HomeRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetProductsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetProductsUseCase_Factory create(Provider<HomeRepository> repositoryProvider) {
    return new GetProductsUseCase_Factory(repositoryProvider);
  }

  public static GetProductsUseCase newInstance(HomeRepository repository) {
    return new GetProductsUseCase(repository);
  }
}
