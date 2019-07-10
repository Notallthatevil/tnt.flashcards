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
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.41")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    //Data binding
    kapt("com.android.databinding:compiler:3.1.4")
    //Easy permissions
    implementation("pub.devrel:easypermissions:3.0.0")
    //Nav graph
    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0")
    implementation("android.arch.navigation:navigation-ui-ktx:1.0.0")
    //Cardview
    implementation("com.google.android.material:material:1.1.0-alpha07")
    //Timber
    implementation("com.jakewharton.timber:timber:4.7.1")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
    //Dagger 2
    implementation("com.google.dagger:dagger:2.23.2")
    kapt("com.google.dagger:dagger-compiler:2.23.2")
    //Viewmodel
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0-alpha02")
    //ViewModelModule
    implementation("com.vikingsen.inject:viewmodel-inject:0.2.0-alpha01")
    kapt("com.vikingsen.inject:viewmodel-inject-processor:0.2.0-alpha01")
    //Room
    implementation("androidx.room:room-runtime:2.1.0")
    kapt("androidx.room:room-compiler:2.1.0")
    implementation("androidx.room:room-ktx:2.1.0")
    //Room dbtools
    implementation("org.dbtools:dbtools-room:4.9.4")

}
