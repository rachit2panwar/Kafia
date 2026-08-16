package com.coffeeshop.core.di;

import com.coffeeshop.core.data.db.AppDatabase;
import com.coffeeshop.core.data.db.CartDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideCartDaoFactory implements Factory<CartDao> {
  private final Provider<AppDatabase> appDatabaseProvider;

  public DataModule_ProvideCartDaoFactory(Provider<AppDatabase> appDatabaseProvider) {
    this.appDatabaseProvider = appDatabaseProvider;
  }

  @Override
  public CartDao get() {
    return provideCartDao(appDatabaseProvider.get());
  }

  public static DataModule_ProvideCartDaoFactory create(Provider<AppDatabase> appDatabaseProvider) {
    return new DataModule_ProvideCartDaoFactory(appDatabaseProvider);
  }

  public static CartDao provideCartDao(AppDatabase appDatabase) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideCartDao(appDatabase));
  }
}
