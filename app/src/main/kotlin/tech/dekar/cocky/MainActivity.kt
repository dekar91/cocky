package tech.dekar.cocky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.ext.android.inject
import tech.dekar.cocky.shared.db.CockyDatabase
import tech.dekar.cocky.shared.ui.control.ActivityActionBus
import tech.dekar.cocky.shared.ui.control.NavigationBus
import tech.dekar.cocky.ui.controls.Navigation

class MainActivity : ComponentActivity() {
    val activityActionBus: ActivityActionBus by inject()

    val navigationBus: NavigationBus by inject()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(navigationBus)
        }

        val driver: SqlDriver = AndroidSqliteDriver(CockyDatabase.Schema, applicationContext, "test.db")

//        val db = CockyDatabase(
//            driver,
//            ingredientsAdapter =
//        )
//        val r =    RecipeLocalDataSource(db)
//
//
//        GlobalScope.launch {
//            val rs = listOf(
//                Recipe(
//                    id = "04",
//                    createdAt = Instant.now().toString(),
//                    updatedAt =  Instant.now(),
//                    imageUrl = null,
//                    steps = listOf("Step 1", "Step 2"),
//                    ingredients = listOf("Ingredient 1", "Ingredient 2"),
//                    title = "Recipe Title2",
//                    description ="re",
//                ),
//            Recipe(
//                id = "23",
//                imageUrl = null,
//                steps = listOf("Step 1", "Step 2"),
//                ingredients = listOf("Ingredient 1", "Ingredient 2"),
//                title = "Recipe Titl22",
//                description ="re",
//            )
//            )
//
// //            rs.forEach { recipe ->
// //                r.insert(recipe)
// //            }
// //
//
//            r.getAll().collect { recipe ->
//                println("Recipe: $recipe")
//            }
    }
}
