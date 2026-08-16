package com.coffeeshop.feature.profile.presentation;

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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public ProfileViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new ProfileViewModel_Factory(authRepositoryProvider);
  }

  public static ProfileViewModel newInstance(AuthRepository authRepository) {
    return new ProfileViewModel(authRepository);
  }
}
