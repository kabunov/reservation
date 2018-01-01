package com.kabunov.reservation.di;

import com.kabunov.reservation.data.datasource.local.LocalStorage;
import com.kabunov.reservation.data.datasource.local.preferences.PreferencesLocalStorage;
import com.kabunov.reservation.data.datasource.remote.api.RestApi;
import com.kabunov.reservation.data.datasource.remote.api.RestApiCreator;
import com.kabunov.reservation.data.repository.CustomerRepositoryImpl;
import com.kabunov.reservation.data.repository.TableRepositoryImpl;
import com.kabunov.reservation.domain.repository.CustomerRepository;
import com.kabunov.reservation.domain.repository.TableRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private static final String API_BASE_URL = "https://s3-eu-west-1.amazonaws.com/quandoo-assessment/";

    @Provides
    @Singleton
    CustomerRepository provideCustomerRepository(CustomerRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    TableRepository provideTableRepository(TableRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    RestApi provideRestApi() {
        return RestApiCreator.create(API_BASE_URL);
    }

    @Provides
    @Singleton
    LocalStorage provideLocalStorage(PreferencesLocalStorage localStorage) {
        return localStorage;
    }
}
