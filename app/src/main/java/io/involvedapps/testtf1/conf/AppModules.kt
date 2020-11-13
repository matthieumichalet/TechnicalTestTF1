package io.involvedapps.testtf1.conf

import androidx.room.Room
import io.involvedapps.data.api.ArticlesApi
import io.involvedapps.data.db.AppDatabase
import io.involvedapps.data.repositories.ArticlesLocalService
import io.involvedapps.data.repositories.ArticlesRemoteService
import io.involvedapps.data.repositories.ArticlesRepositoryImpl
import io.involvedapps.domain.repositories.ArticlesRepository
import io.involvedapps.domain.usecases.ArticlesUseCases
import io.involvedapps.testtf1.viewmodels.ArticleDetailViewModel
import io.involvedapps.testtf1.viewmodels.ArticlesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit

private const val GET_ARTICLES_USE_CASE = "get_article_use_case"

private const val ARTICLE_REMOTE = "article_remote"
private const val ARTICLE_LOCAL = "article_local"
private const val ARTICLE_REPOSITORY = "article_repository"
private const val API_INSTANCE = "api"
private const val DATABASE_INSTANCE = "database"
private const val DATABASE_APP_NAME = "app_database"
private const val RETROFIT_INSTANCE = "retrofit"
private const val BASE_URL = "https://content.guardianapis.com/"

/**
 * ViewModel for each fragment
 */
val viewModels : Module = module {
    viewModel {
        ArticlesViewModel(
            articlesUseCases = get(StringQualifier(GET_ARTICLES_USE_CASE))
        )
    }
    viewModel {
        ArticleDetailViewModel(
            articlesUseCases = get(StringQualifier(GET_ARTICLES_USE_CASE))
        )
    }
}

/**
 * UseCases from Domain module
 */
val useCaseModules = module {
    factory(qualifier = StringQualifier(GET_ARTICLES_USE_CASE)) {
        ArticlesUseCases(repository = get(StringQualifier(ARTICLE_REPOSITORY)))
    }
}

/**
 * Repositories from Data module
 */
val repositories: Module = module {
    single(qualifier = StringQualifier(ARTICLE_LOCAL)) { ArticlesLocalService(get(
        StringQualifier(DATABASE_INSTANCE)
    )) }
    single(qualifier = StringQualifier(ARTICLE_REMOTE)) { ArticlesRemoteService(get(
        StringQualifier(API_INSTANCE)
    )) }
    single(qualifier = StringQualifier(ARTICLE_REPOSITORY)) { ArticlesRepositoryImpl(
        get(StringQualifier(ARTICLE_REMOTE)),
        get(StringQualifier(ARTICLE_LOCAL))) as ArticlesRepository
    }
}

/**
 * Network module using retrofit
 */
val networkModules = module {
    single(qualifier = StringQualifier(RETROFIT_INSTANCE)) {
        createNetworkClient(
            BASE_URL
        )
    }
    single(qualifier = StringQualifier(API_INSTANCE)) {
        (get(StringQualifier(RETROFIT_INSTANCE)) as Retrofit).create(ArticlesApi::class.java)
    }
}

/**
 * Local modules using room
 */
val localModules = module {
    single(qualifier = StringQualifier(DATABASE_INSTANCE)) {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_APP_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
