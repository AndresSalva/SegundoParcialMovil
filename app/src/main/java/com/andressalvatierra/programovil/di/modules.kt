package com.andressalvatierra.programovil.di

import com.andressalvatierra.programovil.R
import com.andressalvatierra.programovil.features.dollar.data.database.AppRoomDatabase
import com.andressalvatierra.programovil.features.dollar.data.datasource.DollarLocalDataSource
import com.andressalvatierra.programovil.features.dollar.data.repository.DollarRepository
import com.andressalvatierra.programovil.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.andressalvatierra.programovil.features.dollar.domain.repository.IDollarRepository
import com.andressalvatierra.programovil.features.dollar.domain.usecase.FetchDollarUseCase
import com.andressalvatierra.programovil.features.dollar.presentation.DollarViewModel
import com.andressalvatierra.programovil.features.github.data.api.GithubService
import com.andressalvatierra.programovil.features.github.data.datasource.GithubRemoteDataSource
import com.andressalvatierra.programovil.features.github.data.repository.GithubRepository
import com.andressalvatierra.programovil.features.github.domain.repository.IGithubRepository
import com.andressalvatierra.programovil.features.github.domain.usecase.FindByNickNameUseCase
import com.andressalvatierra.programovil.features.github.presentation.GithubViewModel
import com.andressalvatierra.programovil.features.movie.data.api.MovieService
import com.andressalvatierra.programovil.features.movie.data.datasource.MovieLocalDataSource
import com.andressalvatierra.programovil.features.movie.data.datasource.MovieRemoteDataSource
import com.andressalvatierra.programovil.features.movie.data.repository.MovieRepository
import com.andressalvatierra.programovil.features.movie.domain.repository.IMoviesRepository
import com.andressalvatierra.programovil.features.movie.domain.usecase.FetchPopularMoviesUseCase
import com.andressalvatierra.programovil.features.movie.presentation.PopularMoviesViewModel
import com.andressalvatierra.programovil.features.profile.application.ProfileViewModel
import com.andressalvatierra.programovil.features.profile.data.repository.ProfileRepository
import com.andressalvatierra.programovil.features.profile.domain.repository.IProfileRepository
import com.andressalvatierra.programovil.features.profile.domain.usecase.GetProfileUseCase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConstants {
    const val RETROFIT_GITHUB = "RetrofitGithub"
    const val GITHUB_BASE_URL = "https://api.github.com/"
    const val RETROFIT_MOVIE = "RetrofitMovie"
    const val MOVIE_BASE_URL = "https://api.themoviedb.org/"
}

val appModule = module {


    // OkHttpClient
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single(named(NetworkConstants.RETROFIT_GITHUB)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.GITHUB_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit
    single(named(NetworkConstants.RETROFIT_MOVIE)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.MOVIE_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // GithubService
    single<GithubService> {
        get<Retrofit>( named(NetworkConstants.RETROFIT_GITHUB)).create(GithubService::class.java)
    }
    single{ GithubRemoteDataSource(get()) }
    single<IGithubRepository>{ GithubRepository(get()) }

    factory { FindByNickNameUseCase(get()) }
    viewModel { GithubViewModel(get(), get()) }

    single<IProfileRepository> { ProfileRepository() }
    factory { GetProfileUseCase(get()) }
    viewModel { ProfileViewModel(get()) }

    single { AppRoomDatabase.getDatabase(get()) }
    single { get<AppRoomDatabase>().dollarDao() }
    single { RealTimeRemoteDataSource() }
    single { DollarLocalDataSource(get()) }
    single<IDollarRepository> { DollarRepository(get(), get()) }
    factory { FetchDollarUseCase(get()) }
    viewModel{ DollarViewModel(get()) }


    single(named("apiKey")) {
        androidApplication().getString(R.string.api_key)
    }

    single<MovieService> {
        get<Retrofit>(named(NetworkConstants.RETROFIT_MOVIE)).create(MovieService::class.java)
    }
    single { MovieRemoteDataSource(get(), get(named("apiKey"))) }
    single { MovieLocalDataSource(get()) }
    single { get<AppRoomDatabase>().movieDao() }
    single<IMoviesRepository> { MovieRepository(get(), get()) }
    factory { FetchPopularMoviesUseCase(get()) }
    viewModel{ PopularMoviesViewModel(get()) }
}