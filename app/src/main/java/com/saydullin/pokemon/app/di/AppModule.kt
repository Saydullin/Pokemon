package com.saydullin.pokemon.app.di

import android.content.Context
import androidx.room.Room
import com.saydullin.pokemon.api.services.GetPokemonsService
import com.saydullin.pokemon.app.viewmodels.PokemonViewModelFactory
import com.saydullin.pokemon.data.db.AppDatabase
import com.saydullin.pokemon.data.db.dao.PokemonDao
import com.saydullin.pokemon.data.repositories.PokemonRepositoryImpl
import com.saydullin.pokemon.domain.repositories.PokemonRepository
import com.saydullin.pokemon.domain.usecases.GetPokemonUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val mApplicationContext: Context) {

    private var appDatabase: AppDatabase = Room.databaseBuilder(
        mApplicationContext,
        AppDatabase::class.java,
        "AppDB"
    )
        .build()

    @Provides
    fun providePokemonViewModel(
        getPokemonUseCase: GetPokemonUseCase,
    ) : PokemonViewModelFactory {
        return PokemonViewModelFactory(getPokemonUseCase = getPokemonUseCase)
    }

    @Provides
    fun provideApplicationContext(): Context {
        return mApplicationContext
    }

    @Provides
    fun providesPokemonUseCase(
        pokemonRepository: PokemonRepository,
    ): GetPokemonUseCase {
        return GetPokemonUseCase(pokemonRepository)
    }

    @Provides
    fun provideAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    fun providesPokemonDao(appDatabase: AppDatabase): PokemonDao {
        return appDatabase.pokemonDao()
    }

    @Provides
    fun providePokemonRepositoryImpl(
        pokemonsService: GetPokemonsService,
        pokemonDao: PokemonDao,
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonsService, pokemonDao)
    }

}


