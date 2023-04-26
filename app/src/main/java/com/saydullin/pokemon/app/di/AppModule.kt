package com.saydullin.pokemon.app.di

import android.content.Context
import androidx.room.Room
import com.saydullin.pokemon.app.viewmodels.PokemonViewModelFactory
import com.saydullin.pokemon.data.db.AppDatabase
import com.saydullin.pokemon.data.db.converters.PokemonListConverter
import com.saydullin.pokemon.data.db.converters.PokemonTypeListConverter
import com.saydullin.pokemon.data.db.dao.PokemonDao
import com.saydullin.pokemon.data.db.dao.PokemonInfoDao
import com.saydullin.pokemon.domain.usecases.GetPokemonInfoUseCase
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
        .addTypeConverter(PokemonListConverter())
        .addTypeConverter(PokemonTypeListConverter())
        .build()

    @Provides
    fun providePokemonViewModel(
        getPokemonUseCase: GetPokemonUseCase,
        getPokemonInfoUseCase: GetPokemonInfoUseCase,
    ) : PokemonViewModelFactory {
        return PokemonViewModelFactory(
            getPokemonUseCase = getPokemonUseCase,
            getPokemonInfoUseCase = getPokemonInfoUseCase,
        )
    }

    @Provides
    fun provideApplicationContext(): Context {
        return mApplicationContext
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
    fun providesPokemonInfoDao(appDatabase: AppDatabase): PokemonInfoDao {
        return appDatabase.pokemonInfoDao()
    }

}


