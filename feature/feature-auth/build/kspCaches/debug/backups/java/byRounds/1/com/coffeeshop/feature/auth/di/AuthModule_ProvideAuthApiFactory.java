package com.coffeeshop.feature.auth.di;

import com.coffeeshop.feature.auth.data.remote.AuthApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class AuthModule_ProvideAuthApiFactory implements Factory<AuthApi> {
  private final Provider<Retrofit> retrofitProvider;

  public AuthModule_ProvideAuthApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AuthApi get() {
    return provideAuthApi(retrofitProvider.get());
  }

  public static AuthModule_ProvideAuthApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new AuthModule_ProvideAuthApiFactory(retrofitProvider);
  }

  public static AuthApi provideAuthApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthApi(retrofit));
  }
}
