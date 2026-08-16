package com.coffeeshop.feature.auth.data.repository;

import com.coffeeshop.core.datastore.UserPrefsDataStore;
import com.coffeeshop.core.network.AuthInterceptor;
import com.coffeeshop.feature.auth.data.remote.AuthApi;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<AuthApi> apiProvider;

  private final Provider<UserPrefsDataStore> dataStoreProvider;

  private final Provider<AuthInterceptor> authInterceptorProvider;

  public AuthRepositoryImpl_Factory(Provider<AuthApi> apiProvider,
      Provider<UserPrefsDataStore> dataStoreProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    this.apiProvider = apiProvider;
    this.dataStoreProvider = dataStoreProvider;
    this.authInterceptorProvider = authInterceptorProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(apiProvider.get(), dataStoreProvider.get(), authInterceptorProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<AuthApi> apiProvider,
      Provider<UserPrefsDataStore> dataStoreProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    return new AuthRepositoryImpl_Factory(apiProvider, dataStoreProvider, authInterceptorProvider);
  }

  public static AuthRepositoryImpl newInstance(AuthApi api, UserPrefsDataStore dataStore,
      AuthInterceptor authInterceptor) {
    return new AuthRepositoryImpl(api, dataStore, authInterceptor);
  }
}
