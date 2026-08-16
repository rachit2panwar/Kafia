package com.coffeeshop.feature.auth.di;

import com.coffeeshop.core.domain.repository.AuthRepository;
import com.coffeeshop.feature.auth.data.repository.AuthRepositoryImpl;
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
public final class AuthModule_ProvideAuthRepositoryFactory implements Factory<AuthRepository> {
  private final Provider<AuthRepositoryImpl> implProvider;

  public AuthModule_ProvideAuthRepositoryFactory(Provider<AuthRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepository(implProvider.get());
  }

  public static AuthModule_ProvideAuthRepositoryFactory create(
      Provider<AuthRepositoryImpl> implProvider) {
    return new AuthModule_ProvideAuthRepositoryFactory(implProvider);
  }

  public static AuthRepository provideAuthRepository(AuthRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthRepository(impl));
  }
}
