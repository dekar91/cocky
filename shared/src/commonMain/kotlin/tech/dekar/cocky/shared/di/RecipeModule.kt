package tech.dekar.cocky.shared.di

import app.cash.sqldelight.EnumColumnAdapter
import org.koin.dsl.module
import tech.dekar.cocky.shared.db.Ingredients
import tech.dekar.cocky.shared.domain.repository.RecipeLocalDataSource
import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.CreateRecipeUseCase
import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.GetAllRecipesUseCase
import tech.dekar.cocky.shared.domain.usecases.recipes.viewmodel.RecipesViewModel

val recipeModule = module {
    // Use cases
    single { GetAllRecipesUseCase(get()) }
    single { CreateRecipeUseCase(get()) }
    single { RecipeLocalDataSource(get()) }

    single {  Ingredients.Adapter( unitAdapter = EnumColumnAdapter())  }


    
    // ViewModels
    factory { RecipesViewModel(get(), get(), get()) }
}
