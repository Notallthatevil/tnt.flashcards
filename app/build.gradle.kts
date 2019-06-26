
plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.0")
    defaultConfig {
        applicationId = "com.trippntechnology.tnt.flashcards"
        minSdkVersion(16)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.40")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    //Timber
    implementation("com.jakewharton.timber:timber:4.7.1")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
    //Dagger 2
    implementation("com.google.dagger:dagger:2.23.2")
    kapt("com.google.dagger:dagger-compiler:2.23.1")
    //ViewModelModule
    implementation("com.vikingsen.inject:viewmodel-inject:0.1.1")
    kapt("com.vikingsen.inject:viewmodel-inject-processor:0.1.1")

}
