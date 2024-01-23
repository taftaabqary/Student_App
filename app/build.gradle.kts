plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.althaaf.studentapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.althaaf.studentapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // glide
    implementation ("com.github.bumptech.glide:glide:4.15.1")

    // RxJava
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("com.jakewharton.rxbinding2:rxbinding:2.2.0")

    // paging3
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    // datastore
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // lottie
    implementation("com.airbnb.android:lottie:6.2.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // lifecycle components
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.8.2")

    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}