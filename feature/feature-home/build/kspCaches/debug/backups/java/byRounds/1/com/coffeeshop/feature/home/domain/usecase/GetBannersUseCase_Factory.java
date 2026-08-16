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
public final class GetBannersUseCase_Factory implements Factory<GetBannersUseCase> {
  private final Provider<HomeRepository> repositoryProvider;

  public GetBannersUseCase_Factory(Provider<HomeRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetBannersUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetBannersUseCase_Factory create(Provider<HomeRepository> repositoryProvider) {
    return new GetBannersUseCase_Factory(repositoryProvider);
  }

  public static GetBannersUseCase newInstance(HomeRepository repository) {
    return new GetBannersUseCase(repository);
  }
}
