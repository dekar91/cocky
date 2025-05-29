# --- Удалить отладочную информацию ---
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
    public static *** wtf(...);
}

# --- Сохраняем entry points (например, Activities, Services, ViewModel, DI) ---
-keep class * extends android.app.Activity
-keep class * extends android.app.Service
-keep class androidx.lifecycle.ViewModel { *; }

# --- Сохраняем DI аннотации (если используешь Hilt/Dagger) ---
-keepattributes *Annotation*
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class kotlin.Metadata { *; }

# --- Jetpack Compose (если используешь) ---
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.foundation.** { *; }

# --- Sentry (метаданные и номера строк) ---
-keep class io.sentry.** { *; }
-keepattributes *Annotation*, SourceFile, LineNumberTable, Signature

# --- Сохраняем номера строк для вашего кода ---
-keepattributes SourceFile,LineNumberTable
-keep class tech.dekar.cocky.** { *; }

# --- Если используешь reflection / JNI ---
-keepclasseswithmembers class * {
    native <methods>;
}
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
