package com.coffeeshop.feature.auth.domain.usecase;

import com.coffeeshop.core.domain.repository.AuthRepository;
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
public final class LoginUseCase_Factory implements Factory<LoginUseCase> {
  private final Provider<AuthRepository> repositoryProvider;

  public LoginUseCase_Factory(Provider<AuthRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LoginUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static LoginUseCase_Factory create(Provider<AuthRepository> repositoryProvider) {
    return new LoginUseCase_Factory(repositoryProvider);
  }

  public static LoginUseCase newInstance(AuthRepository repository) {
    return new LoginUseCase(repository);
  }
}
