package tech.dekar.cocky.shared.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Test

/**
 * Тест проверяет, что при вызове CockyDatabase.Schema.create(driver)
 * в in-memory-базе действительно появляется таблица recipes.
 */
class DatabaseSchemaTest {

    // In-memory-драйвер, он хранит БД только в памяти, без файлов на диске.
    private lateinit var driver: JdbcSqliteDriver

    @Before
    fun setup() {
        // Создаём in-memory SQLite-драйвер. Версия схемы из Schema.version никак не влияет на create(),
        // но для migrate() понадобятся миграции (если используются).
        driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

        // Вызываем метод создания схемы. Если вы используете deriveSchemaFromMigrations = false,
        // SQLDelight найдёт .sq-файлы и прогоним DDL из них.
        // Если deriveSchemaFromMigrations = true и у вас есть миграции, SQLDelight прокинет DDL из миграций.
        CockyDatabase.Schema.create(driver)
    }

    @Test
    fun `recipes table exists after schema creation`() {
        // Проверяем, что в sqlite_master появилась таблица recipes
        val query = "SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'recipes';"
        val cursor = driver.executeQuery<String?>(
            null,
            query,
            { cursor -> QueryResult.Value(cursor.getString(0)) },
            0
        )

        cursor.value shouldBe "recipes"

    }

    @Test
    fun `ingredients table exists after schema creation`() {
        val query = "SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'ingredients';"
        val cursor = driver.executeQuery<String?>(
            null,
            query,
            { cursor -> QueryResult.Value(cursor.getString(0)) },
            0
        )

        cursor.value shouldBe "ingredients"
    }

    @Test
    fun `recipe_steps table exists after schema creation`() {
        val query = "SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'recipe_steps';"
        val cursor = driver.executeQuery<String?>(
            null,
            query,
            { cursor -> QueryResult.Value(cursor.getString(0)) },
            0
        )

        cursor.value shouldBe "recipe_steps"
    }
}
